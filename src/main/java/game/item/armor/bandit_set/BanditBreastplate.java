package main.java.game.item.armor.bandit_set;

import main.java.game.item.armor.Breastplate;
import main.java.game.main.GamePanel;

public class BanditBreastplate extends Breastplate {
    public BanditBreastplate(GamePanel gamePanel) {
        super(gamePanel);
        setImages("src/main/resources/armor/bandit_armor/breastplate/");
        protection = 4;
        name = "Bandit Breastplate";
        description = "Light bandit armor";
        equipped = false;
    }
}

