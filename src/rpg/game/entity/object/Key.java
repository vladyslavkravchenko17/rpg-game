package rpg.game.entity.object;

import rpg.game.entity.Entity;
import rpg.game.main.GamePanel;

public class Key extends Entity {

    public Key(GamePanel gamePanel){
        super(gamePanel);
        down0 = setImage("key", "src/resources/objects/");
        name = "Key";

    }
}
