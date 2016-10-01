import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by michaelxi on 2016-09-30.
 */
public class fileTypeFinder {
    public static void main(String[] argss){
        //this.loadFileType(types, );
        FileReader reader= new FileReader();
        byte[] bytes=reader.readByte("ciphertext1");
        System.out.print(bytes);
    }

    private ArrayList<fileType> types=new ArrayList<fileType>();

    private String examineSignature(){
        String resultType="None";

        return resultType;
    }

    private void loadFileType(ArrayList<fileType> types,String name, Integer headerLength, String header){
        fileType newType=new fileType();
        newType.setHeader(header);
        newType.setHeaderLength(headerLength);
        newType.setName(name);
        types.add(newType);
    }

}
