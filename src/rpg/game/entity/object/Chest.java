package rpg.game.entity.object;

import rpg.game.entity.Entity;
import rpg.game.main.GamePanel;

import java.awt.*;

public class Chest extends Entity {
    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        collision = true;
        down0 = setImage("chest", "src/resources/objects/");
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
