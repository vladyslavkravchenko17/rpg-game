package rpg.game.item.armor.bandit_set;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Breastplate;

public class BanditBreastplate extends Breastplate {
    public BanditBreastplate() {
        setImages("src/resources/armor/bandit_armor/breastplate/");
        protection = 2;
        name = "Bandit Breastplate";
        description = "Light bandit armor";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}

