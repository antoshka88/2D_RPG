package tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    String mapPath = "src/main/resources/maps/world01.txt";

    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap(mapPath);
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(); //Создаем объект Tale в массиве и записываем в его поле image картинку через ImageIO
            tile[0].image = ImageIO.read(new File("src/main/resources/tiles/Grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/main/resources/tiles/Wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/main/resources/tiles/Water.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/main/resources/tiles/Sand.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/main/resources/tiles/Earth.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/main/resources/tiles/Tree.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try{
            FileInputStream is = new FileInputStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    System.out.println("Col: " + col + " Row: " + row + " TN: " + num);
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }
            }
            br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            //////////////
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }
    }


}
