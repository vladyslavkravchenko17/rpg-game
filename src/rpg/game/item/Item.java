package rpg.game.item;

import rpg.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    public String name;
    public String description;
    public BufferedImage icon;
    public ItemType itemType;

    protected BufferedImage setImage(String fileName, String dir) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(dir + fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return image;
    }

    public void use(GamePanel gamePanel) {

    }
}
