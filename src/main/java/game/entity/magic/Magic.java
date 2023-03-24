package main.java.game.entity.magic;

import main.java.game.entity.Direction;
import main.java.game.entity.Entity;
import main.java.game.main.GamePanel;

public abstract class Magic extends Entity {

    public Magic(GamePanel gamePanel) {
        super(gamePanel);
    }

    public abstract void set(int worldX, int worldY, Direction direction, Entity user);
}
