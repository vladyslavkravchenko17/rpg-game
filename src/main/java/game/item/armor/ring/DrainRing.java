package main.java.game.item.armor.ring;

import main.java.game.item.ItemType;
import main.java.game.item.armor.Ring;
import main.java.game.main.GamePanel;

public class DrainRing extends Ring {

    public DrainRing(GamePanel gamePanel) {
        super(gamePanel);
        icon = setImage("icon", "src/main/resources/armor/ring/drain_ring/");
        strength = 10;
        name = "Drain колечки увув";
        description = "Holy ring gives " + strength + " strength уву";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
