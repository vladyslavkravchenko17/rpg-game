package rpg.game.item.armor.ring;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Ring;

public class RingOfStrength extends Ring {
    public RingOfStrength() {
        icon = setImage("icon", "src/resources/armor/ring/ring_of_strength/");
        strength = 2;
        name = "Ring of strength";
        description = "Holy ring gives " + strength + " strength";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
