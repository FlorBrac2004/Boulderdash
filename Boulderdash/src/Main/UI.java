package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Crystal;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	BufferedImage crystalImage;
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		OBJ_Crystal  crystal = new OBJ_Crystal();
		crystalImage = crystal.image;
		
	}
	
	public void draw(Graphics2D g2)	{
		
		g2.setFont(arial_40);
		g2.setColor(Color.RED);
		g2.drawImage(crystalImage, 1600, gp.tileSize/2, 1600 + gp.tileSize, 35, null);
		g2.drawString("x " + gp.player.hasCrystal, 1700, 35);
		
	}

}
