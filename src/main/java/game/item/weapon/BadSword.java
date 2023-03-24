package main.java.game.item.weapon;

import main.java.game.item.ItemType;
import main.java.game.main.GamePanel;

public class BadSword extends Weapon {

    public BadSword(GamePanel gamePanel) {
        super(gamePanel);
//        setAttackImages("src/main.resources/weapon/bad_sword/");
        icon = setImage("icon", "src/main/resources/weapon/bad_sword/");
        damage = 5;
        name = "Bad sword";
        itemType = ItemType.WEAPON;
        description = "Hits on " + damage + " damage";
        attackArea.width = 24;
        attackArea.height = 24;
    }
}
