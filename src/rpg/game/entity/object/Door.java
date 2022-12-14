package rpg.game.entity.object;

import rpg.game.entity.Entity;
import rpg.game.main.GamePanel;

public class Door extends Entity {
    public Door(GamePanel gamePanel) {
        super(gamePanel);
        name = "Door";
        collision = true;
        down0 = setImage("door", "src/resources/objects/");
    }
}
