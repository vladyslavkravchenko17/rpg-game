package rpg.game.entity.magic;

import rpg.game.Direction;
import rpg.game.entity.Entity;
import rpg.game.entity.Player;
import rpg.game.main.GamePanel;

public abstract class Magic extends Entity {

    public Magic(GamePanel gamePanel) {
        super(gamePanel);
    }

    public abstract void set(int worldX, int worldY, Direction direction, Entity user);
}
