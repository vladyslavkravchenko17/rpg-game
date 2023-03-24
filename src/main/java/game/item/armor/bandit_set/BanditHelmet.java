package main.java.game.item.armor.bandit_set;

import main.java.game.item.armor.Helmet;
import main.java.game.main.GamePanel;

public class BanditHelmet extends Helmet {

    public BanditHelmet(GamePanel gamePanel) {
        super(gamePanel);
//        setImages("src/main.resources/armor/bandit_armor/helmet/");
        icon = setImage("icon", "src/main/resources/armor/bandit_armor/helmet/");
        protection = 3;
        name = "Bandit Helmet";
        description = "Light bandit helmet";
        equipped = false;
    }
}
