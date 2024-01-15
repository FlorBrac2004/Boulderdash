package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Stone extends SuperObject {

    public OBJ_Stone() {
        name = "Stone";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Kugel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
