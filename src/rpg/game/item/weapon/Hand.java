package rpg.game.item.weapon;

import rpg.game.main.GamePanel;

public class Hand extends Weapon{

    public Hand(GamePanel gamePanel) {
        super(gamePanel);
        setAttackImages("src/resources/weapon/hand/");
        damage = 0;
        name = "Hand";
        description = "Hits on " + damage + " damage";
        attackArea.width = 18;
        attackArea.height = 18;
    }
}