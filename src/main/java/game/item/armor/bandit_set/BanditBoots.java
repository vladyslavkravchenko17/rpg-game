package main.java.game.item.armor.bandit_set;

import main.java.game.item.armor.Boots;
import main.java.game.main.GamePanel;

public class BanditBoots extends Boots {
    public BanditBoots(GamePanel gamePanel) {
        super(gamePanel);
        setImages("src/main/resources/armor/bandit_armor/boots/");
        protection = 2;
        agility = 1;
        name = "Bandit Boots";
        description = "Light bandit boots";
        equipped = false;
    }
}
