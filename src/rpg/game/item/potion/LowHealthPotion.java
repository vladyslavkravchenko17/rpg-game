package rpg.game.item.potion;

import rpg.game.main.GamePanel;

public class LowHealthPotion extends Potion {
    final int hpHeal = 20;

    public LowHealthPotion() {
        name = "Low health potion";
        description = "Heals " + hpHeal + " HP";
        icon = setImage("icon", "src/resources/item/potions/low_health_potion/");
    }

    @Override
    public void use (GamePanel gamePanel) {
        if (gamePanel.player.hp + hpHeal >= gamePanel.player.maxHp) {
            gamePanel.player.hp = gamePanel.player.maxHp;
        } else {
            gamePanel.player.hp += hpHeal;
        }
    }
}
