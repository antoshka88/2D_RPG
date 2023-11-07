package entity;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
    }
    public void getImage() {

        up1 = setup("src/main/resources/npc/oldman_up_1");
        up2 = setup("src/main/resources/npc/oldman_up_2");
        down1 = setup("src/main/resources/npc/oldman_down_1");
        down2 = setup("src/main/resources/npc/oldman_down_2");
        left1 = setup("src/main/resources/npc/oldman_left_1");
        left2 = setup("src/main/resources/npc/oldman_left_2");
        right1 = setup("src/main/resources/npc/oldman_right_1");
        right2 = setup("src/main/resources/npc/oldman_right_2");
    }

    public void setAction(){
        Random random = new Random();
        actionLockCounter++;
        if(actionLockCounter == 120){

            int i = random.nextInt(100) +1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 25 && i <= 50){
                direction = "left";
            }
            if(i > 70 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }


}
