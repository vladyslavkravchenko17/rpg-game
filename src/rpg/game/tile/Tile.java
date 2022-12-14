package rpg.game.tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage bufferedImage;
    private boolean collision;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
