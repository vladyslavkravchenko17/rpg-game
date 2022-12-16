package rpg.game.entity;

import rpg.game.Direction;
import rpg.game.entity.magic.Magic;
import rpg.game.item.Item;
import rpg.game.item.armor.*;
import rpg.game.main.GamePanel;
import rpg.game.item.weapon.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Entity {
    protected GamePanel gamePanel;
    public EntityType type;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public BufferedImage aUp0, aUp1, aUp2, aDown0, aDown1, aDown2, aLeft0, aLeft1, aLeft2, aRight0, aRight1, aRight2;
    public BufferedImage image, hairImage, eyesImage, breastplateImage, helmetImage, glovesImage, bootsImage;
    public Rectangle solidArea;

    public int worldX, worldY;
    public Direction direction = Direction.DOWN;
    protected int dialogueIndex = 0;
    public int spriteNumber = 0;
    public int spriteCounter = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean casting = false;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public String[] dialogues = new String[10];

    //OBJECT
    public BufferedImage weaponImage;
    public String name;
    public  boolean collision = false;
    public boolean alive = true;

    //EQUIPMENT
//    public ArrayList<Item> inventory = new ArrayList<>();
    public Item[] inventory = new Item[20];
    public final int maxInventorySize = 20;
    public Weapon weapon;
    public Magic magicSpell;
    public Helmet helmet;
    public Breastplate breastplate;
    public Boots boots;
    public Gloves gloves;
    public Necklace necklace;
    public Ring ring;

    //CHARACTERISTICS
    public int lvl;
    public int exp;
    public int nextLvlExp;
    public int agility;
    public int speed;
    public int strength;
    public int damage;
    public int body;
    public int defense;
    public int maxHp;
    public int hp;
    public int vitality;
    public int mana;
    public int maxMana;
    public int intelligence;
    public int money;
    int animationSpeed = 10;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        solidArea = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {}
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gamePanel.player.direction) {
            case UP -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.UP;
            case RIGHT, UP_RIGHT, DOWN_RIGHT -> direction = Direction.LEFT;
            case LEFT, UP_LEFT, DOWN_LEFT -> direction = Direction.RIGHT;
        }
    }
    public void update() {
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        if (contactPlayer && type == EntityType.MONSTER) {
            if (!gamePanel.player.invincible && !gamePanel.keyHandler.godMode) {
                gamePanel.player.hp -= 15;
                gamePanel.player.invincible = true;
            }
        }
            if (!collisionOn && direction != Direction.STANDS) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }

            spriteCounter++;
            if (spriteCounter > animationSpeed) {
                switch (spriteNumber) {
                    case 0 -> spriteNumber = 1;
                    case 1 -> spriteNumber = 2;
                    case 2 -> spriteNumber = 3;
                    case 3 -> spriteNumber = 0;
                }
                spriteCounter = 0;
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                hpBarCounter = 0;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2D) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            BufferedImage image = down0;
            switch (direction) {
                case UP:
                    switch (spriteNumber) {
                        case 0, 2 -> image = up0;
                        case 1 -> image = up1;
                        case 3 -> image = up2;
                    }
                    break;
                case DOWN:
                    switch (spriteNumber) {
                        case 0, 2 -> image = down0;
                        case 1 -> image = down1;
                        case 3 -> image = down2;
                    }
                    break;
                case LEFT, UP_LEFT, DOWN_LEFT:
                    switch (spriteNumber) {
                        case 0, 2 -> image = left0;
                        case 1 -> image = left1;
                        case 3 -> image = left2;
                    }
                    break;
                case RIGHT, UP_RIGHT, DOWN_RIGHT:
                    switch (spriteNumber) {
                        case 0, 2 -> image = right0;
                        case 1 -> image = right1;
                        case 3 -> image = right2;
                    }
            }

            if (type == EntityType.MONSTER) {
                g2D.setColor(Color.WHITE);
                g2D.setFont(gamePanel.ui.pixelFont.deriveFont(Font.PLAIN, 14F));
                String text = (lvl + " LVL");
                int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth() / 2;
                g2D.drawString(text, screenX + 5, screenY - 15);
                if (hpBarOn) {
                    double scale = (double) gamePanel.tileSize / maxHp;
                    double hpBarValue = scale * hp;

                    g2D.setColor(new Color(35, 0, 0));
                    g2D.fillRect(screenX - 1, screenY - 16, gamePanel.tileSize + 2, 7);

                    g2D.setColor(new Color(255, 0, 30));
                    g2D.fillRect(screenX, screenY - 15, (int) hpBarValue, 5);

                    hpBarCounter++;
                    if (hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }
            }

            if (invincible) {
                hpBarOn = true;
                g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
            }
            g2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        }
    }

    protected BufferedImage setImage(String fileName, String dir) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(dir + fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return image;
    }

    protected void setImages(String dir) {
        up0 = setImage("u0", dir);
        up1 = setImage("u1", dir);
        up2 = setImage("u2", dir);
        down0 = setImage("d0", dir);
        down1 = setImage("d1", dir);
        down2 = setImage("d2", dir);
        left0 = setImage("l0", dir);
        left1 = setImage("l1", dir);
        left2 = setImage("l2", dir);
        right0 = setImage("r0", dir);
        right1 = setImage("r1", dir);
        right2 = setImage("r2", dir);
    }

    protected void setAttackImages(String dir) {
        aUp0 = setImage("u0", dir);
        aUp1 = setImage("u1", dir);
        aUp2 = setImage("u2", dir);
        aDown0 = setImage("d0", dir);
        aDown1 = setImage("d1", dir);
        aDown2 = setImage("d2", dir);
        aLeft0 = setImage("l0", dir);
        aLeft1 = setImage("l1", dir);
        aLeft2 = setImage("l2", dir);
        aRight0 = setImage("r0", dir);
        aRight1 = setImage("r1", dir);
        aRight2 = setImage("r2", dir);
    }

    protected void setSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = gamePanel.tileSize / 3;
        solidArea.y = gamePanel.tileSize / 3 * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gamePanel.tileSize / 3;
        solidArea.height = gamePanel.tileSize / 3;
    }

    public void calculateStats() {
        speed = agility;
        defense = body;
        damage = strength;
        maxHp = vitality;
        if (weapon != null) {
            damage += weapon.damage;
        }
        if (breastplate != null) {
            defense += breastplate.protection;
        }
        if (helmet != null) {
            defense += helmet.protection;
        }
        if (boots != null) {
            defense += boots.protection;
            speed += boots.agility;
        }
        if (necklace != null) {
            damage += necklace.strength;
            maxHp += necklace.vitality;
        }
        if (ring != null) {
            damage += ring.strength;
            maxHp += ring.vitality;
        }
    }
}
