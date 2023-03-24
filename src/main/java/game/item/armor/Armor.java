package main.java.game.item.armor;

import main.java.game.item.Item;
import main.java.game.item.ItemType;
import main.java.game.main.GamePanel;

import java.awt.image.BufferedImage;

public class Armor extends Item {
    public int protection;
    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    public ArmorType armorType;
    public boolean equipped;

    public Armor(GamePanel gamePanel) {
        super(gamePanel);
        itemType = ItemType.ARMOR;
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
        icon = setImage("icon", dir);
    }
}
