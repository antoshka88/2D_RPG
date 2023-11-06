package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        name = "Door";
        try{
            image = ImageIO.read(new File("src/main/resources/objects/Door.png"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        collision = true;
    }
}
