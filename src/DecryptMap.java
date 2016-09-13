import java.util.*;

public class DecryptMap {
    private int[][] map;
    private Map<Integer,ArrayList<Integer>> decryptmap;


    // Create a decryption map
    public DecryptMap(int[][] map) {
        this.map = map;
        decryptmap = new HashMap<>();
        for (int i = 0; i < 16; i++){
            decryptmap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++){
                decryptmap.get(map[i][j]).add((i<<4)+j);
            }
        }
    }

    // Return all the possible combination of [p][k] under the same cipher byte
    public ArrayList<Integer> getCombi(int cipher) {
        /*
        System.out.print("{");
        for(int i: decryptmap.get(cipherbyte)) {
            System.out.print(Integer.toHexString(i)+",");
        }
        System.out.print("}");
        */
        return decryptmap.get(cipher);
    }
}


