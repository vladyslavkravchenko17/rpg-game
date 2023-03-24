package main.java.game.entity.mob;

import main.java.game.entity.Direction;
import main.java.game.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class PinkSlime extends Mob {
    Random random = new Random();

    public PinkSlime(GamePanel gamePanel, int lvl) {
        super(gamePanel, lvl);
        name = "Pink Slime";
        speed = 1;
        hp = 10;
        maxHp = 10;
        setImages("src/main/resources/enemy/slime/");
        setSolidArea();
    }

    protected void setImages(String dir) {
        up0 = setImage("d0", dir);
        up1 = setImage("d0", dir);
        up2 = setImage("d0", dir);
        down0 = setImage("d0", dir);
        down1 = setImage("d0", dir);
        down2 = setImage("d0", dir);
        left0 = setImage("d0", dir);
        left1 = setImage("d0", dir);
        left2 = setImage("d0", dir);
        right0 = setImage("d0", dir);
        right1 = setImage("d0", dir);
        right2 = setImage("d0", dir);
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


    protected void setSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 38;
        solidArea.height = 33;
    }
}
