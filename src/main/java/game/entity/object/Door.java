package main.java.game.entity.object;

import main.java.game.entity.Entity;
import main.java.game.main.GamePanel;

public class Door extends Entity {
    public Door(GamePanel gamePanel) {
        super(gamePanel);
        name = "Door";
        collision = true;
        down0 = setImage("door", "src/main.resources/objects/");
    }
}
