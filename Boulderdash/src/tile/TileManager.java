package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage() {

        try {

            tile[2] = new Tile(false);
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background.png"));

            tile[0] = new Tile(true);
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));

            tile[3] = new Tile(false);
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wand.png"));
            tile[3].collision = true;

            tile[1] = new Tile(false);
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Rand.png"));
            tile[1].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            while (worldCol < gp.maxWorldCol) {

                int tileNum = mapTileNum[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;

                g2.drawImage(tile[tileNum].image, worldX, worldY, gp.tileSize, gp.tileSize, null);
                worldCol++;
            }

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
