package rpg.game.item.armor.bandit_set;

import rpg.game.item.armor.Helmet;

public class BanditHelmet extends Helmet {

    public BanditHelmet() {
//        setImages("src/resources/armor/bandit_armor/helmet/");
        icon = setImage("icon", "src/resources/armor/bandit_armor/helmet/");
        protection = 3;
        name = "Bandit Helmet";
        description = "Light bandit helmet";
        equipped = false;
    }
}
