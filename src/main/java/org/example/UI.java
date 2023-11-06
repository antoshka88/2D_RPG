package org.example;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_60;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";

    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_60 = new Font("Arial", Font.BOLD, 60);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMassage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished == true) {
            //вывод текста по центру экрана
            g2.setFont(arial_60);
            g2.setColor(Color.ORANGE);
            String text;
            int textLength;
            int x;
            int y;

            text = "Ты нашел сокровище";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //Нахождение длинны текста для центрирования
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text,x,y);
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 60);

            //MESSAGE
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }


}
