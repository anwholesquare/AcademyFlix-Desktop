
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import javafx.scene.image.Image;



public class ImageHelper {
    
    public static String imgToB64(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read= 0;
            while( (read = fis.read(buffer)) > -1){
                baos.write(buffer, 0, read);
            }
            fis.close();
            baos.close();
            byte pgnBytes [] = baos.toByteArray();
            Base64.Encoder base64_enc = Base64.getEncoder();
            String base64_image = base64_enc.encodeToString(pgnBytes);
            System.out.println(base64_image);
            return base64_image;
        } catch (IOException ex) {
            return "null";
        }
    }
    
    public static Image getImageFromBase64String(String newValue) throws IOException {
       Base64.Decoder base64Decoder = Base64.getDecoder();
       Image image = new Image(new ByteArrayInputStream(base64Decoder.decode(newValue)));
       return image;
    }
    
}
