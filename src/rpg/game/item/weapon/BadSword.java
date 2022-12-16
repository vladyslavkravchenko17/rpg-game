package rpg.game.item.weapon;

import rpg.game.item.ItemType;
import rpg.game.main.GamePanel;

public class BadSword extends Weapon {

    public BadSword(GamePanel gamePanel) {
        super(gamePanel);
//        setAttackImages("src/resources/weapon/bad_sword/");
        icon = setImage("icon", "src/resources/weapon/bad_sword/");
        damage = 5;
        name = "Bad sword";
        itemType = ItemType.WEAPON;
        description = "Hits on " + damage + " damage";
        attackArea.width = 24;
        attackArea.height = 24;
    }
}
