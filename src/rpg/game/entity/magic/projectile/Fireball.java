package rpg.game.entity.magic.projectile;

import rpg.game.main.GamePanel;

public class Fireball extends Projectile {

    public Fireball(GamePanel gamePanel) {
        super(gamePanel);
        name = "Fireball";
        damage = 5;
        speed = 10;
        maxHp = 30;
        hp = maxHp;
        manaCost = 1;
        alive = false;
        setImages("src/resources/magic/projectiles/fireball/");
    }

}
