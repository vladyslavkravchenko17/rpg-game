package main.java.game.UI;

import main.java.game.item.Item;
import main.java.game.main.GamePanel;

public class InventoryManager {
    GamePanel gamePanel;

    public InventoryManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void addItemToInventory(Item item) {
        for (int i = 0; i < gamePanel.player.inventory.length; i++) {
            if (gamePanel.player.inventory[i] == null) {
                gamePanel.player.inventory[i] = item;
                break;
            }
        }
    }
}