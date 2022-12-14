package rpg.game.weapon;

import rpg.game.main.GamePanel;

public class Hand extends Weapon{

    public Hand(GamePanel gamePanel) {
        super(gamePanel);
        setAttackImages("src/resources/weapon/hand/");
        attackArea.width = 18;
        attackArea.height = 18;
    }
}
