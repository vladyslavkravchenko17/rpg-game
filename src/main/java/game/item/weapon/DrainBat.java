package main.java.game.item.weapon;

import main.java.game.item.ItemType;
import main.java.game.main.GamePanel;

public class DrainBat extends Weapon {

    public DrainBat(GamePanel gamePanel) {
        super(gamePanel);
//        setAttackImages("src/main.resources/weapon/drain_bat/");
        icon = setImage("icon", "src/main/resources/weapon/drain_bat/");
        damage = 0;
        name = "Drain bat AAAAAA";
        itemType = ItemType.WEAPON;
        description = "Hits on " + damage + " damage";
        solidArea.width = 100;
        solidArea.height = 100;
    }
}
