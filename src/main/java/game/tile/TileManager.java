package main.java.game.tile;

import main.java.game.main.GamePanel;
import main.java.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileManager {
    private final GamePanel gamePanel;
    public Tile[] tiles = new Tile[50];
    public int[][] tilesMap;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tilesMap = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        setTileImage();
        loadMap("/main/resources/maps/world01.txt");
    }

    public void setTileImage() {
        addTilePattern(0, "floor", false);
        addTilePattern(1, "grass1", false);
        addTilePattern(2, "grass2", false);
        addTilePattern(3, "grass3", false);
        addTilePattern(4, "grass4", false);
        addTilePattern(5, "path", false);
        addTilePattern(6, "rock", true);
        addTilePattern(7, "wall", true);
        addTilePattern(8, "water", true);
        addTilePattern(9, "water_c_d_l", true);
        addTilePattern(10, "water_c_d_r", true);
        addTilePattern(11, "water_c_u_l", true);
        addTilePattern(12, "water_c_u_r", true);
        addTilePattern(13, "water_d", true);
        addTilePattern(14, "water_d_l", true);
        addTilePattern(15, "water_d_r", true);
        addTilePattern(16, "water_l", true);
        addTilePattern(17, "water_r", true);
        addTilePattern(18, "water_u", true);
        addTilePattern(19, "water_u_l", true);
        addTilePattern(20, "water_u_r", true);


    }
    private void addTilePattern(int tileNum, String fileName, boolean collision) {
        try {
            String dir = "src/main/resources/background/";
            tiles[tileNum] = new Tile();
            tiles[tileNum].setCollision(collision);
            tiles[tileNum].setBufferedImage(ImageIO.read(new File(dir + fileName + ".png")));
            tiles[tileNum].setBufferedImage(UtilityTool.scaleImage(tiles[tileNum].getBufferedImage(), gamePanel.tileSize, gamePanel.tileSize));
        } catch (IOException e) {
            System.err.println("Error loading image file: " + fileName + ".png");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream iS = getClass().getResourceAsStream(filePath);
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
            int col = 0;
            int row = 0;

            while (row < gamePanel.maxWorldRow) {
                String line = bR.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    tilesMap[col][row] = num;
                    col++;
                }
                col = 0;
                row++;
            }
            bR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int tileNum = tilesMap[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                g2D.drawImage(tiles[tileNum].getBufferedImage(), screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
