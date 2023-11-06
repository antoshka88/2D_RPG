package entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        worldX = gp.tileSize * 25 - gp.tileSize; //25
        worldY = gp.tileSize * 25; //25
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(new File("src/main/resources/player/up1.png"));
            up2 = ImageIO.read(new File("src/main/resources/player/up2.png"));
            down1 = ImageIO.read(new File("src/main/resources/player/down1.png"));
            down2 = ImageIO.read(new File("src/main/resources/player/down2.png"));
            left1 = ImageIO.read(new File("src/main/resources/player/left1.png"));
            left2 = ImageIO.read(new File("src/main/resources/player/left2.png"));
            right1 = ImageIO.read(new File("src/main/resources/player/right1.png"));
            right2 = ImageIO.read(new File("src/main/resources/player/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if (keyH.isWalking) {
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }

            // Проверка коллизии
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Проверка коллизии с обектом
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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

    public void pickUpObject(int i){

        if (i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println(" KEY : " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0){
                        hasKey--;
                        gp.obj[i] = null;
                        System.out.println("Door open");
                        System.out.println(" KEY : " + hasKey);
                    }
                    break;
                case "Boots":
                    speed += 1;
                    gp.obj[i]=null;
                    break;
            }
        }

    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
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
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}