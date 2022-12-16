package rpg.game.item.armor.necklace;

import rpg.game.item.ItemType;
import rpg.game.item.armor.Necklace;

public class HealthNecklace extends Necklace {
    public HealthNecklace() {
        icon = setImage("icon", "src/resources/armor/necklace/health_necklace/");
        vitality = 5;
        name = "Necklace of health";
        description = "Holy necklace gives " + vitality + " hp";
        itemType = ItemType.ARMOR;
        equipped = false;
    }
}
