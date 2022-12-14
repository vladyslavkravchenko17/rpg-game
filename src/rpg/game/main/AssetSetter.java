package rpg.game.main;

import rpg.game.entity.Entity;
import rpg.game.entity.enemy.PinkSlime;
import rpg.game.entity.npc.Lizard;
import rpg.game.entity.object.Chest;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects() {
        Entity chest = new Chest(gamePanel);
        setCoordinates(chest, 14, 42);
        gamePanel.objects.add(chest);
    }

    public void setNPC() {
        Entity lizard = new Lizard(gamePanel);
        setCoordinates(lizard, 14, 30);
        gamePanel.npc.add(lizard);
    }

    public void setMonsters() {
        setMonster(new PinkSlime(gamePanel), 40, 40);
        setMonster(new PinkSlime(gamePanel), 37, 37);
        setMonster(new PinkSlime(gamePanel), 37, 38);
        setMonster(new PinkSlime(gamePanel), 37, 39);
        setMonster(new PinkSlime(gamePanel), 37, 40);
        setMonster(new PinkSlime(gamePanel), 38, 37);
        setMonster(new PinkSlime(gamePanel), 38, 40);


    }

    private void setMonster(Entity mob, int col, int row) {
        setCoordinates(mob, col, row);
        gamePanel.monsters.add(mob);
    }

    private void setCoordinates(Entity entity, int col, int row) {
        entity.worldX = col * gamePanel.tileSize;
        entity.worldY = row * gamePanel.tileSize;
    }
}