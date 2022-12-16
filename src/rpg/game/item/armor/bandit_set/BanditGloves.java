package rpg.game.item.armor.bandit_set;

import rpg.game.item.armor.Gloves;

public class BanditGloves extends Gloves  {

    public BanditGloves() {
//        setImages("src/resources/armor/bandit_armor/gloves/");
        icon = setImage("icon", "src/resources/armor/bandit_armor/gloves/");
        protection = 1;
        name = "Bandit gloves";
        description = "Light bandit gloves";
        equipped = false;
    }
}
