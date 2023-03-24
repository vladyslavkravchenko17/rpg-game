package main.java.game.entity.mob;

import main.java.game.entity.Entity;
import main.java.game.entity.EntityType;
import main.java.game.main.GamePanel;

public class Mob extends Entity {
    public Mob(GamePanel gamePanel, int lvl) {
        super(gamePanel);
        this.lvl = lvl;
        type = EntityType.MONSTER;
        exp = lvl;
    }
}
