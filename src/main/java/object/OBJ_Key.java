package object;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        this.gp = gp;

        name = "Key";
        try{
            image = ImageIO.read(new File("src/main/resources/objects/Key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException ex){
         ex.printStackTrace();
        }
    }
}
