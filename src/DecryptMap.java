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

    public char getPlain(int cipher, int key) {
        int ph = 0,pl = 0;
        ArrayList<Integer> chcombi = decryptmap.get(cipher>>4);
        ArrayList<Integer> clcombi = decryptmap.get(cipher&15);
        for (int chc: chcombi) {
            if ((chc&15)==(key&15)) {
                ph = chc>>4;
            }
        }
        for (int clc: clcombi) {
            if ((clc&15)==(key>>4)) {
                pl = clc>>4;
            }
        }
        return (char) ((ph<<4)|pl);
    }

    //returns 0 if key character is not printable
    public char getKey(int cipher, int plain) {
        int kh = 0,kl = 0;
        ArrayList<Integer> chcombi = decryptmap.get(cipher>>4);
        ArrayList<Integer> clcombi = decryptmap.get(cipher&15);
        for (int chc: chcombi) {
            if ((chc&15)==(plain>>4)){
                kh = chc>>4;
            }
        }
        for (int clc: clcombi) {
            if ((clc&15)==(plain&15))  {
                kl = clc>>4;
            }
        }
        char ret = (char) ((kh<<4)|kl);
        if (isPrintable(ret))return ret;
        return (char) 0;
    }

    // Return all the possible combination of the key and plain on the specific index
    public ArrayList<Integer>[] getAllCombi(int cipher) {
        ArrayList<Integer> plainlist = new ArrayList<>();
        ArrayList<Integer> keylist = new ArrayList<>();
        int p,k;
        //shift ch into the lower 4 bits of a new int
        int ch = cipher >> 4;
        //remove ch
        int cl = cipher & 15;

        for (int chc: getCombi(ch)) {
            for (int clc: getCombi(cl)) {
                p = chc&0xf0 | (clc>>4);
                k = (clc&0x0f)<<4 | (chc&0x0f);
                if (isPrintable(p)&& (isPrintable(k)||isNonPrintable(k))) {
                    plainlist.add(p);
                    keylist.add(k);
                }
            }
        }
        ArrayList<Integer>[] result = new ArrayList[2];
        result[0] = keylist;
        result[1] = plainlist;
        return result;
    }

    private boolean isPrintable(int text) {
        return (text > 31) && (text < 128);
    }

    private boolean isNonPrintable(int text) {
        return text < 32;
    }

    // Return all the possible combination of [p][k] under the same cipher byte
    private ArrayList<Integer> getCombi(int cipherbyte) {
        return decryptmap.get(cipherbyte);
    }


}


