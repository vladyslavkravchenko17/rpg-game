package main.java.game.item.armor.drain_set;

import main.java.game.item.armor.Gloves;
import main.java.game.main.GamePanel;

public class DrainGloves extends Gloves {

    public DrainGloves(GamePanel gamePanel) {
        super(gamePanel);
//        setImages("src/main.resources/armor/bandit_armor/gloves/");
        icon = setImage("icon", "src/main/resources/armor/drain_armor/gloves/");
        protection = 1;
        name = "Drain gloves neko cit cot cat)!@(!()()";
        description = "Neko gloves ^^^^^^^";
        equipped = false;
    }
}
