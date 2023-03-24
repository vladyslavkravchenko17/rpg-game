package main.java.game.item.potion;

import main.java.game.item.Item;
import main.java.game.item.ItemType;
import main.java.game.main.GamePanel;

public class Potion extends Item {
    public Potion(GamePanel gamePanel) {
        super(gamePanel);
        itemType = ItemType.USABLE;
    }
}
