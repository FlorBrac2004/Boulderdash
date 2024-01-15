package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public boolean isDirt = false;

    public Tile(boolean isDirt) {
        this.isDirt = isDirt;
    }

}
