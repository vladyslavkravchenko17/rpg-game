package main.java.game.item.armor;


import main.java.game.main.GamePanel;

public class Necklace extends Armor {
    public int vitality = 0;
    public int strength = 0;
    public Necklace(GamePanel gamePanel) {
        super(gamePanel);
        armorType = ArmorType.NECKLACE;
    }
}
