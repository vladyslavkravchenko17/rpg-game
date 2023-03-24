package main.java.game.item.armor.bandit_set;

import main.java.game.item.armor.Gloves;
import main.java.game.main.GamePanel;

public class BanditGloves extends Gloves  {

    public BanditGloves(GamePanel gamePanel) {
        super(gamePanel);
//        setImages("src/main.resources/armor/bandit_armor/gloves/");
        icon = setImage("icon", "src/main/resources/armor/bandit_armor/gloves/");
        protection = 1;
        name = "Bandit gloves";
        description = "Light bandit gloves";
        equipped = false;
    }
}
