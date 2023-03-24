package main.java.game.entity.object;

import main.java.game.entity.Entity;
import main.java.game.main.GamePanel;

import java.awt.*;


public class Chest extends Entity {
    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        collision = true;
        down0 = setImage("chest", "src/main/resources/objects/");
        setSolidArea();
    }

    protected void setSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = gamePanel.tileSize / 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gamePanel.tileSize;
        solidArea.height = gamePanel.tileSize / 3 * 2;
    }
}
