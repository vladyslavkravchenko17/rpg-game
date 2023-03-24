package main.java.game.entity.object;

import main.java.game.entity.Entity;
import main.java.game.main.GamePanel;

public class Key extends Entity {

    public Key(GamePanel gamePanel){
        super(gamePanel);
        down0 = setImage("key", "src/main.resources/objects/");
        name = "Key";

    }
}
