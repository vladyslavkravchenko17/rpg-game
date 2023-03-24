package main.java.game.event;

import main.java.game.entity.Direction;
import main.java.game.main.GamePanel;


public class EventHandler {
    GamePanel gamePanel;
    EventRect[][] eventRect;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = gamePanel.tileSize / 2 - 1;
            eventRect[col][row].y = gamePanel.tileSize / 2 - 1;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        if (hit(12, 42, Direction.ANY)) {
            teleport(12, 42, 12, 40);
            System.out.println();
        }
    }

    private void teleport(int col, int row, int col1, int row1) {
        gamePanel.player.worldX = col1 * gamePanel.tileSize;
        gamePanel.player.worldY = row1 * gamePanel.tileSize;
        gamePanel.player.hp -= 10;
        eventRect[col][row].eventDone = true;
    }

    private void damagePit(int col, int row) {
        eventRect[col][row].eventDone = true;
    }

    private boolean hit(int col, int row, Direction reqDirection) {
        boolean hit = false;
        gamePanel.player.solidArea.x += gamePanel.player.worldX;
        gamePanel.player.solidArea.y += gamePanel.player.worldY;
        eventRect[col][row].x = col * gamePanel.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gamePanel.tileSize + eventRect[col][row].y;

        if (gamePanel.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gamePanel.player.direction == reqDirection || reqDirection == Direction.ANY) {
                hit = true;
            }
        }
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;


        return hit;
    }


}
