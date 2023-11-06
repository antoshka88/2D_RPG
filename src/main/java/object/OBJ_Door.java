package object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "Door";
        try{
            image = ImageIO.read(new File("src/main/resources/objects/Door.png"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        collision = true;
    }
}
