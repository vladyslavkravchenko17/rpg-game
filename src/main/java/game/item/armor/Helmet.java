package main.java.game.item.armor;

import main.java.game.main.GamePanel;

public class Helmet extends Armor {
    public Helmet(GamePanel gamePanel) {
        super(gamePanel);
        armorType = ArmorType.HELMET;
    }
}
