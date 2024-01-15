package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public boolean collectable = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean gravitation = true;
    
    public void draw(Graphics2D g2, GamePanel gp)    {
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
