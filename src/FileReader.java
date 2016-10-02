import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.nio.file.*;
import java.lang.ClassLoader;


public class FileReader {
    public FileReader() {
    }

    public ArrayList<Integer> read(String filename) {
        ArrayList<Integer> ciphers = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(getClass().getResource(filename).getPath());
            int cipher = inputStream.read();
            while (cipher!=-1) {
                ciphers.add(cipher);
                cipher = inputStream.read();
            }
        } catch (Exception e) {
            System.out.print("Exception");
        }
        return ciphers;
    }

    // function modified from https://www.mkyong.com/java/how-to-convert-file-into-an-array-of-bytes/
    public byte[] readByte(String filename) {
        byte[] bFile=new byte[(int) filename.length()];
        try {
            //convert file into array of bytes
            URL resource = ClassLoader.getSystemResource(filename);
            File file = new File(resource.toURI());
            //InputStream inputStream = new FileInputStream(getClass().getResource(filename).getPath());
            Path path=file.toPath();
            bFile = Files.readAllBytes(path);
        } catch (Exception e){
            System.out.print("Exception");
        }

        System.out.print(bFile.length);
        return bFile;
    }
}
