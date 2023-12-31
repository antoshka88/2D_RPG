package entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

//    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
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

        up1 = setup("src/main/resources/player/up1");
        up2 = setup("src/main/resources/player/up2");
        down1 = setup("src/main/resources/player/down1");
        down2 = setup("src/main/resources/player/down2");
        left1 = setup("src/main/resources/player/left1");
        left2 = setup("src/main/resources/player/left2");
        right1 = setup("src/main/resources/player/right1");
        right2 = setup("src/main/resources/player/right2");
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

            // Проверка коллизии тайла
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Проверка коллизии с обектом
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Проверка коллизии с нпс
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if (collisionOn == false) {
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
//            String objectName = gp.obj[i].name;
//
//            switch (objectName){
//                case "Key":
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.playSE(2);
//                    gp.ui.showMassage("Ты подобрал ключ");
//                    break;
//                case "Door":
//                    if (hasKey > 0){
//                        hasKey--;
//                        gp.obj[i] = null;
//                        gp.playSE(1);
//                        gp.ui.showMassage("Дверь открыта");
//                    } else {
//                        gp.ui.showMassage("Нужен ключ");
//                    }
//                    break;
//                case "Boots":
//                    gp.playSE(1);
//                    speed += 1;
//                    gp.obj[i]=null;
//                    gp.ui.showMassage("Ускорение!!!!");
//                    break;
//                case "Chest":
//                    gp.playSE(1);
//                    gp.obj[i] = null;
//                    gp.ui.gameFinished = true;
//                    gp.stopMusic();
//                    break;
//
//            }
        }

    }

    public void interactNPC(int i){
        if (i != 999){
            System.out.println(" !ПЕРЕСЕЧЕНИЕ! ");
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
        g2.drawImage(image, screenX, screenY, null);
    }
}