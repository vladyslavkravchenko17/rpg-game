package main.java.game.item.weapon;

import main.java.game.main.GamePanel;

public class Hand extends Weapon{

    public Hand(GamePanel gamePanel) {
        super(gamePanel);
        setAttackImages("src/main/resources/weapon/hand/");
        icon = setImage("icon", "src/main/resources/weapon/bad_sword/");
        damage = 0;
        name = "Hand";
        description = "Hits on " + damage + " damage";
        solidArea.width = 18;
        solidArea.height = 18;
    }
}
