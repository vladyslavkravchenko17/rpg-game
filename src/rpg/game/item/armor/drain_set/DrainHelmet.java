package rpg.game.item.armor.drain_set;

import rpg.game.item.armor.Helmet;

public class DrainHelmet extends Helmet {
    public DrainHelmet() {
//        setImages("src/resources/armor/bandit_armor/helmet/");
        icon = setImage("icon", "src/resources/armor/drain_armor/helmet/");
        protection = 3;
        name = "DrainDRAINdrAin Mask";
        description = "Hello kitty mask";
        equipped = false;
    }
}
