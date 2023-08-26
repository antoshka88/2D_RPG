package entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    /**
     * Метод для установки параметров по умолчанию
     */
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("src/main/resources/player/right2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed == true) {
            y -= speed;
        } else if (keyH.downPressed == true) {
            y += speed;
        } else if (keyH.leftPressed == true) {
            x -= speed;
        } else if (keyH.rightPressed == true) {
            x += speed;
        }

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}