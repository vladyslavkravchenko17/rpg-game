package rpg.game.item.armor.bandit_set;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Breastplate;

public class BanditBreastplate extends Breastplate {
    public BanditBreastplate() {
        setImages("src/resources/armor/bandit_armor/breastplate/");
        protection = 4;
        name = "Bandit Breastplate";
        description = "Light bandit armor";
        equipped = false;
    }
}

