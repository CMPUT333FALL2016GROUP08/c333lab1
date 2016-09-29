import java.util.ArrayList;

/**
 * Created by michaelximac on 2016-09-29.
 */
public class KeyFinder {

    public KeyFinder(){

    }

    public static void main (String[] args) {
        FileReader fileReader= new FileReader();
        KeyFinder keyFinder=new KeyFinder();
        ArrayList<Integer> ciphertext=fileReader.read("ciphertext1");
        ArrayList<Integer> displacedCipher=new ArrayList<>();
        // Copy all the content from cipertext to displacedCipher
        for (Integer t: ciphertext ){
            displacedCipher.add(t);
        }

        int cipherLength=keyFinder.cipherLength(ciphertext);
        //System.out.print(ciphertext+"\n");
        keyFinder.displace(displacedCipher);
        //System.out.print(ciphertext+"\n");
        //System.out.print(displacedCipher+"\n");
        System.out.print(keyFinder.compareArray(ciphertext,displacedCipher)+"\n");
    }

    private static int cipherLength(ArrayList<Integer> cipher){
        return cipher.size();
    }
    private static ArrayList<Integer> displace(ArrayList<Integer> cipherInt){
        cipherInt.remove(cipherInt.size()-1);
        cipherInt.add(0,0);
        return cipherInt;
    }
    private static int compareArray(ArrayList<Integer> cipher, ArrayList<Integer> displaced){
        int count=0;
        for (int x=0; x<cipher.size(); x++){
            if (cipher.get(x)==displaced.get(x)){
                System.out.print(cipher.get(x)+";"+displaced.get(x)+"\n");
                count+=1;
            }
        }
        return count;
    }
}
