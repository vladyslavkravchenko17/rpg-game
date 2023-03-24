package main.java.game.item.armor.drain_set;

import main.java.game.item.armor.Boots;
import main.java.game.main.GamePanel;

public class DrainBoots extends Boots {

    public DrainBoots(GamePanel gamePanel) {
        super(gamePanel);
        setImages("src/main/resources/armor/drain_armor/boots/");
        protection = 2;
        agility = 1;
        name = "Drain Boots XXX-/2@!";
        description = "DRAIN CONVERSE AAAA";
        equipped = false;
    }
}
