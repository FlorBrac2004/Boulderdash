package Main;

import java.awt.Rectangle;

import entity.Entity;
import object.SuperObject;

public class Gravitation {

    GamePanel gp;

    public Gravitation(GamePanel gp) {
        this.gp = gp;
    }

    private boolean isEntityBelowStone(SuperObject stone, Entity entity) {
        Rectangle stoneRect = new Rectangle(stone.worldX, stone.worldY, gp.tileSize, gp.tileSize);
        Rectangle entityRect = new Rectangle(
                entity.worldX + entity.solidArea.x, 
                entity.worldY + entity.solidArea.y + entity.solidArea.height, entity.solidArea.width,  entity.solidArea.height);

        // Allow a gap between the stone and the player (e.g., 5 pixels)
        return stoneRect.intersects(entityRect) && (stone.worldY + gp.tileSize - entity.worldY > 5);
    }


    private boolean isAnyObjectBelowStone(SuperObject stone) {
        Rectangle stoneRect = new Rectangle(stone.worldX, stone.worldY, gp.tileSize, gp.tileSize);

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.obj[i] != stone) {
                Rectangle objRect = new Rectangle(gp.obj[i].worldX, gp.obj[i].worldY, gp.tileSize, gp.tileSize);
                if (stoneRect.intersects(objRect)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void stoneGravitation() {
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null && gp.obj[i].name.equals("Stone") && gp.obj[i].gravitation) {
                int stoneCol = gp.obj[i].worldX / gp.tileSize;
                int stoneRow = gp.obj[i].worldY / gp.tileSize;

                // Check if there is no object below the stone except tile with index 2 (Background)
                if (stoneRow + 1 < gp.maxWorldRow && gp.tileM.mapTileNum[stoneCol][stoneRow + 1] == 2
                        && !isAnyObjectBelowStone(gp.obj[i]) && !isEntityBelowStone(gp.obj[i], gp.player)) {
                    gp.obj[i].worldY += gp.tileSize; // Move the stone down
                }
            }
        }
    }





    public void crystalGravitation() {
        // Implement crystal gravitation logic if needed
    }
}
