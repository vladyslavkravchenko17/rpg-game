package main.java.game.item.armor.necklace;

import main.java.game.item.ItemType;
import main.java.game.item.armor.Necklace;
import main.java.game.main.GamePanel;

public class HelloKittyNecklace extends Necklace {

    public HelloKittyNecklace(GamePanel gamePanel) {
        super(gamePanel);
        icon = setImage("icon", "src/main/resources/armor/necklace/drain_necklace/");
        vitality = 50;
        name = "Hello kitty^^";
        description = "Drain kitty gives " + vitality + " hp^^";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
