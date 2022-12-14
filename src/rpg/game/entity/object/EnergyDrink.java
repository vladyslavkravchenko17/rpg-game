package rpg.game.entity.object;

import rpg.game.entity.Entity;
import rpg.game.main.GamePanel;

public class EnergyDrink extends Entity {
    public EnergyDrink(GamePanel gamePanel){
        super(gamePanel);
        name = "Energy Drink";
        down0 = setImage("energy_drink", "src/resources/objects/");
    }
}
