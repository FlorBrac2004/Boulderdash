package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Crystal extends SuperObject {
	
	

    public OBJ_Crystal() {
        name = "Crystal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Crystal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
        collectable = true;
    }
}
