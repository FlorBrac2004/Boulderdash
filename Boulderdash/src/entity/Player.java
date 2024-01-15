package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.Gravitation;
import Main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    
    public int hasCrystal = 0; 

    private int movementDelay = 4; // Set the delay in update cycles
    private int movementCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle(1, 1, 1, 1);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        speed = 48;
        direction = "normal";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Oben.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Oben2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Unten.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Unten2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Rechts.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Rechts2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Links.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Links2.png"));
            normal1 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Normal.png"));
            normal2 = ImageIO.read(getClass().getResourceAsStream("/player/Figur_Normal2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        movementCounter++;

        if (movementCounter >= movementDelay) {
            movementCounter = 0;

            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.rightPressed) {
                    direction = "right";
                } else if (keyH.leftPressed) {
                    direction = "left";
                }

                collisionOn = false;
                gp.cChecker.checkTile(this);

                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            gp.cChecker.dirtDeleter(this);
                            break;
                        case "down":
                            worldY += speed;
                            gp.cChecker.dirtDeleter(this);
                            break;
                        case "right":
                            worldX += speed;
                            gp.cChecker.dirtDeleter(this);
                            break;
                        case "left":
                            worldX -= speed;
                            gp.cChecker.dirtDeleter(this);
                            break;
                    }

                    spriteCounter++;
                    if (spriteCounter > 12) {
                        if (spriteNum == 1) {
                            spriteNum = 2;
                        } else if (spriteNum == 2) {
                            spriteNum = 1;
                        }
                        spriteCounter = 0;
                    }
                }
            }
        }
    }


    public void pickUpObject(int i) {
        if (i != 999 && gp.obj[i].collectable == true) {

            String objectName = gp.obj[i].name;

            if (objectName.equals("Crystal")) {

                hasCrystal++;
                gp.obj[i] = null;

            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "normal":
                if (spriteNum == 1) {
                    image = normal1;
                }
                if (spriteNum == 2) {
                    image = normal2;
                }
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
