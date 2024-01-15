package Main;

import object.OBJ_Crystal;
import object.OBJ_Stone;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp)    {
        this.gp = gp;       
    }
    
    public void setObject()    {
        
        gp.obj[0] = new OBJ_Crystal();
        gp.obj[0].worldX = 5 * gp.tileSize;
        gp.obj[0].worldY = 5 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Stone();
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;
    }

}
