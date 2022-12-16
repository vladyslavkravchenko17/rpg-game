package rpg.game.item.armor.ring;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Ring;

public class DrainRing extends Ring {

    public DrainRing() {
        icon = setImage("icon", "src/resources/armor/ring/drain_ring/");
        strength = 10;
        name = "Drain колечки увув";
        description = "Holy ring gives " + strength + " strength уву";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
