package object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        this.gp = gp;
        name = "Boots";
        try{
            image = ImageIO.read(new File("src/main/resources/objects/Boot.png"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
