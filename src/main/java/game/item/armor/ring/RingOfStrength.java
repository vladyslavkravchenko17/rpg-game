package main.java.game.item.armor.ring;

import main.java.game.item.ItemType;
import main.java.game.item.armor.Ring;
import main.java.game.main.GamePanel;

public class RingOfStrength extends Ring {
    public RingOfStrength(GamePanel gamePanel) {
        super(gamePanel);
        icon = setImage("icon", "src/main/resources/armor/ring/ring_of_strength/");
        strength = 2;
        name = "Ring of strength";
        description = "Holy ring gives " + strength + " strength";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
