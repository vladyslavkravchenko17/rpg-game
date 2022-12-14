package rpg.game.weapon;

import rpg.game.Direction;
import rpg.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Weapon {
    public GamePanel gamePanel;
    public Rectangle attackArea = new Rectangle();
    public int damage = 5;
    public Direction attackDirection;

    public BufferedImage aUp0, aUp1, aUp2, aDown0, aDown1, aDown2, aLeft0, aLeft1, aLeft2, aRight0, aRight1, aRight2;

    public Weapon(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void setDirectionByMouse() {
        int screenMidX = gamePanel.screenWidth / 2;
        int screenMidY = gamePanel.screenHeight / 2;
        int mouseX = gamePanel.mouseHandler.mouseX;
        int mouseY = gamePanel.mouseHandler.mouseY;

    }

    protected void setAttackImages(String dir) {
//        aUp0 = setImage("u0", dir);
//        aUp1 = setImage("u1", dir);
//        aUp2 = setImage("u2", dir);
//        aDown0 = setImage("d0", dir);
//        aDown1 = setImage("d1", dir);
//        aDown2 = setImage("d2", dir);
//        aLeft0 = setImage("l0", dir);
//        aLeft1 = setImage("l1", dir);
//        aLeft2 = setImage("l2", dir);
        aRight0 = setImage("r0", dir);
        aRight1 = setImage("r1", dir);
        aRight2 = setImage("r2", dir);
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
}
