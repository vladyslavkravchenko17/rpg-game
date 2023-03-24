package main.java.game.item.armor.drain_set;

import main.java.game.item.armor.Breastplate;
import main.java.game.main.GamePanel;

public class DrainBreastplate extends Breastplate {

    public DrainBreastplate(GamePanel gamePanel) {
        super(gamePanel);
        setImages("src/main/resources/armor/drain_armor/breastplate/");
        protection = 4;
        name = "Drain kyrtochka <3333";
        description = "Drain traxat";
        equipped = false;
    }
}
