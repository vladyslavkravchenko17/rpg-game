package main.java.game.item.armor.necklace;

import main.java.game.item.ItemType;
import main.java.game.item.armor.Necklace;
import main.java.game.main.GamePanel;

public class HealthNecklace extends Necklace {
    public HealthNecklace(GamePanel gamePanel) {
        super(gamePanel);
        icon = setImage("icon", "src/main/resources/armor/necklace/health_necklace/");
        vitality = 5;
        name = "Necklace of health";
        description = "Holy necklace gives " + vitality + " hp";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
