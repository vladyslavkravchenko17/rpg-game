package rpg.game.entity.mob;

import rpg.game.entity.Entity;
import rpg.game.entity.EntityType;
import rpg.game.main.GamePanel;

public class Mob extends Entity {
    public Mob(GamePanel gamePanel, int lvl) {
        super(gamePanel);
        this.lvl = lvl;
        type = EntityType.MONSTER;
        exp = lvl;
    }
}
