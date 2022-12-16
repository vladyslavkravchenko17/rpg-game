package rpg.game.item.armor.bandit_set;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Boots;

public class BanditBoots extends Boots {
    public BanditBoots() {
        setImages("src/resources/armor/bandit_armor/boots/");
        protection = 1;
        agility = 1;
        name = "Bandit Boots";
        description = "Light bandit boots";
        equipped = false;
    }
}
