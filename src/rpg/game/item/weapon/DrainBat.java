package rpg.game.item.weapon;

import rpg.game.item.ItemType;
import rpg.game.main.GamePanel;

public class DrainBat extends Weapon {

    public DrainBat(GamePanel gamePanel) {
        super(gamePanel);
//        setAttackImages("src/resources/weapon/drain_bat/");
        icon = setImage("icon", "src/resources/weapon/drain_bat/");
        damage = 50;
        name = "Drain bat AAAAAA";
        itemType = ItemType.WEAPON;
        description = "Hits on " + damage + " damage";
        attackArea.width = 24;
        attackArea.height = 24;
    }
}
