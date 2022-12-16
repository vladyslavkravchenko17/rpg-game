package rpg.game.item.potion;

import rpg.game.main.GamePanel;

public class UltraMonsterWhite extends Potion {

    public UltraMonsterWhite() {
        name = "ULTRA MONSTER WHITE 0.o";
        description = "AAAAAAAAAAAAAAAAAAAAAA";
        icon = setImage("icon", "src/resources/item/potions/ultra_monster_white/");
    }

    @Override
    public void use (GamePanel gamePanel) {
        gamePanel.player.hp = gamePanel.player.maxHp;
        gamePanel.player.mana = gamePanel.player.maxMana;
        gamePanel.player.speed += 10;
    }
}
