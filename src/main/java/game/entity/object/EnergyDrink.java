package main.java.game.entity.object;

import main.java.game.entity.Entity;
import main.java.game.main.GamePanel;

public class EnergyDrink extends Entity {
    public EnergyDrink(GamePanel gamePanel){
        super(gamePanel);
        name = "Energy Drink";
        down0 = setImage("energy_drink", "src/main.resources/objects/");
    }
}
