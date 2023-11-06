package object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        try{
            image = ImageIO.read(new File("src/main/resources/objects/Chest.png"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
