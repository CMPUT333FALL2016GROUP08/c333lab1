import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

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
}
