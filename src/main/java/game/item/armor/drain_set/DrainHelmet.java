package main.java.game.item.armor.drain_set;

import main.java.game.item.armor.Helmet;
import main.java.game.main.GamePanel;

public class DrainHelmet extends Helmet {
    public DrainHelmet(GamePanel gamePanel) {
        super(gamePanel);
//        setImages("src/main.resources/armor/bandit_armor/helmet/");
        icon = setImage("icon", "src/main/resources/armor/drain_armor/helmet/");
        protection = 3;
        name = "DrainDRAINdrAin Mask";
        description = "Hello kitty mask";
        equipped = false;
    }
}
