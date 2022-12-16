package rpg.game.main;

import rpg.game.UI.InventoryManager;
import rpg.game.UI.UI;
import rpg.game.controls.KeyHandler;
import rpg.game.controls.MouseHandler;
import rpg.game.entity.Entity;
import rpg.game.entity.Player;
import rpg.game.event.EventHandler;
import rpg.game.sound.Sound;
import rpg.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    //TILE SETTINGS
    private final int originalTileSize = 48;
    private final int scale = 1;
    public final int tileSize = originalTileSize * scale;

    //SCREEN SETTINGS
    public final int maxScreenCol = 20; //32
    public final int maxScreenRow = 12; //18
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public int screenWidth2 = screenWidth;
    public int screenHeight2 = screenHeight;
    public double screenRelationX = 1, screenRelationY = 1;
    BufferedImage tempScreen;
    public Graphics2D g2D;
    public boolean fullscreenOn = true;


    private static final int FPS = 60;


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;
    public final int characterState = 5;
    public final int optionsState = 6;
    public final int gameOverState = 7;


    //SYSTEM
    public final TileManager tileManager = new TileManager(this);
    public final KeyHandler keyHandler = new KeyHandler(this);
    public final MouseHandler mouseHandler = new MouseHandler(this);
    public final CollisionChecker collisionChecker = new CollisionChecker(this);
    public final AssetSetter assetSetter = new AssetSetter(this);
    public final Sound sound = new Sound();
    public final Sound music = new Sound();
    public final UI ui = new UI(this);
    public final EventHandler eventHandler = new EventHandler(this);
    public final Config config = new Config(this);
    public final InventoryManager inventoryManager = new InventoryManager(this);
    public Thread gameThread;

    public final Player player = new Player(this, keyHandler);
    public ArrayList<Entity> objects = new ArrayList<>();
    public ArrayList<Entity> npc = new ArrayList<>();
    public ArrayList<Entity> monsters = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectiles = new ArrayList<>();


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObjects();
        assetSetter.setNPC();
        assetSetter.setMonsters();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2D = (Graphics2D) tempScreen.getGraphics();

        if (fullscreenOn) {
            setFullScreen();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            drawToTempScreen();
            drawToScreen();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

    private void update() {
        if (gameState == playState) {
            player.update();
            for (Entity npc : npc) {
                npc.update();
            }
            for (Entity monster : monsters) {
                monster.update();
            }
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).update();
                if (!projectiles.get(i).alive) {
                    projectiles.remove(i);
                    i--;
                }
            }
        }
        mouseHandler.update();

    }

    public void retry() {
        player.setDefaultPosition();
        player.restoreHP();
        assetSetter.setMonsters();
    }

    public void restart() {
        player.setDefaultPosition();
        player.restoreHP();
        player.setDefaultValues();
        assetSetter.setObjects();
        assetSetter.setMonsters();
        music.stopMusic();
    }

    public void setFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
        screenRelationX = (double) screenWidth / screenWidth2;
        screenRelationY = (double) screenHeight / screenHeight2;
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void drawToTempScreen() {
        long drawStart = System.nanoTime();

        if (gameState == titleState) {
            ui.draw(g2D);
        } else {
            tileManager.draw(g2D);

            entityList.add(player);
            entityList.addAll(npc);
            entityList.addAll(objects);
            entityList.addAll(monsters);
            entityList.addAll(projectiles);
            entityList.sort(Comparator.comparingInt(o -> o.worldY));

            for (Entity entity : entityList) {
                entity.draw(g2D);
            }

            entityList.clear();

            ui.draw(g2D);

            long drawEnd = System.nanoTime();
            long timeLast = drawEnd - drawStart;
            if (keyHandler.showDrawTime) {
                ui.drawSystemParameters(timeLast);
            }
            if (keyHandler.godMode) {
                g2D.setColor(Color.white);
                g2D.setFont(g2D.getFont().deriveFont(32f));
                g2D.drawString("GodMod On", 10, 490);
            }
        }
    }
}
