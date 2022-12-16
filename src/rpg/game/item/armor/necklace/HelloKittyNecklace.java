package rpg.game.item.armor.necklace;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Necklace;

public class HelloKittyNecklace extends Necklace {

    public HelloKittyNecklace() {
        icon = setImage("icon", "src/resources/armor/necklace/drain_necklace/");
        vitality = 50;
        name = "Hello kitty^^";
        description = "Drain kitty gives " + vitality + " hp^^";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
