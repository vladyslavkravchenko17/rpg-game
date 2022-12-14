package rpg.game.entity.npc;

import rpg.game.Direction;
import rpg.game.entity.Entity;
import rpg.game.entity.EntityType;
import rpg.game.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Lizard extends Entity {
    Random random = new Random();

    public Lizard(GamePanel gamePanel) {
        super(gamePanel);
        name = "Lizard";
        type = EntityType.NPC;
        direction = Direction.DOWN;
        speed = 1;
        setImages("src/resources/NPC/lizard/");
        setDialogues();
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


    private void setDialogues() {
        dialogues[0] = "Приветствую тебя, странник! \nНо почему ты голый?..";
        dialogues[2] = "AAAAAAAAAAA";
        dialogues[1] = "Traxat????";
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            int i = random.nextInt(5) + 1; // 1-4
            if (i == 1) {
                direction = Direction.UP;
            }
            if (i == 2) {
                direction = Direction.DOWN;
            }
            if (i == 3) {
                direction = Direction.LEFT;
            }
            if (i == 4) {
                direction = Direction.RIGHT;
            }
            if (i == 5) {
                direction = Direction.STANDS;
            }
            actionLockCounter = 0;
        }
    }
}
