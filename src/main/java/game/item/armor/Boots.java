package main.java.game.item.armor;

import main.java.game.main.GamePanel;

public class Boots extends Armor{
    public int agility;

    public Boots(GamePanel gamePanel) {
        super(gamePanel);
        armorType = ArmorType.BOOTS;
    }
}
