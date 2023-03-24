package main.java.game.item.armor;

import main.java.game.main.GamePanel;

public class Ring extends Armor {
    public int vitality = 0;
    public int strength = 0;
    public Ring(GamePanel gamePanel) {
        super(gamePanel);
        armorType = ArmorType.RING;
    }
}
