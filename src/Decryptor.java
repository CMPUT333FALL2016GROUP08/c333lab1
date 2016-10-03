import java.util.*;

public class Decryptor {
    private static int[][] map= {
            {0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe},
            {0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0},
            {0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7},
            {0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa},
            {0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4},
            {0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3},
            {0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1},
            {0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf},
            {0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2},
            {0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5},
            {0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb},
            {0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6},
            {0x9, 0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8},
            {0xd, 0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9},
            {0xc, 0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd},
            {0xe, 0xf, 0x7, 0x6, 0x4, 0x5, 0x1, 0x0, 0x2, 0x3, 0xb, 0xa, 0x8, 0x9, 0xd, 0xc}
    };
    private ArrayList<ArrayList<Integer>> keycombi;
    private ArrayList<ArrayList<Integer>> plaincombi;
    private int textlength;
    private DecryptMap decryptMap;


    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Decryptor decryptor = new Decryptor();

        if (args.length != 0) {
            ArrayList<Integer> cipher = fileReader.read(args[0]);
            decryptor.decrypt(cipher);
            if (args[1].equals("1")) {
                if (args.length == 4) {
                    decryptor.findWord(args[2], Integer.parseInt(args[3]));
                } else {
                    decryptor.findWord(args[2], 3);
                }
            }
            if (args[1].equals("2")) {
                decryptor.decipher(cipher, decryptor.toIntArray(args[2]));
            }
            if (args[1].equals("3")) {
                Scanner scanner = new Scanner(System.in);
                //ciphertext2's key is composed of printable characters

                System.out.println("Enter the file signature, q to quit");
                String s;
                while (true){
                    s = scanner.nextLine();
                    if (s.equals("q")){
                        System.out.println("quitting");
                        break;
                    }
                    decryptor.possibleKey(cipher, decryptor.hexToIntArray(s));
                }
                //System.out.println("args3 "+args[3]);
                //System.out.println("args2 "+args[2]);
            }
            if (args[1].equals("4")){

            }
        }


        int kl;
        for(int i = 12; i<3055616; i++){
            if (3055616%i==12) System.out.println(i);
        }

        /*
        ArrayList<Integer> cipher1 = fileReader.read("ciphertext1");
        decryptor.decrypt(cipher1);
        decryptor.findWord("and");

        int[] keyword = {80, 47, 8, 124, 95, 48,0};
        //decryptor.decipher(cipher1, keyword);
        */
    }


    private void decrypt(ArrayList<Integer> ciphers){
        decryptMap = new DecryptMap(map);
        keycombi = new ArrayList<>();
        plaincombi = new ArrayList<>();
        textlength = ciphers.size();

        for (int cipher: ciphers) {
            ArrayList<Integer>[] result = decryptMap.getAllCombi(cipher);
            keycombi.add(result[0]);
            plaincombi.add(result[1]);
        }
    }

    // try to find specific words
    private void findWord(String word, int times) {
        int wordlength = word.length();
        Map<ArrayList<Integer>,Integer> keyOccu = new HashMap<>();
        int find = 0;
        for (int textindex = 0; textindex < textlength; textindex++) {
            if (plaincombi.get(textindex).contains((int)word.charAt(0))) {
                find = 1;
                ArrayList<Integer> key = new ArrayList<>();

                key.add(keycombi.get(textindex).get(plaincombi.get(textindex).indexOf((int)word.charAt(0))));
                for (int wordindex = 1; wordindex < wordlength; wordindex++) {
                    if (textindex+wordindex >= textlength ||
                            !plaincombi.get(textindex+wordindex).contains((int)word.charAt(wordindex))) {
                        find = 0;
                        break;
                    }
                    key.add(keycombi.get(textindex+wordindex).get(plaincombi.get(textindex+wordindex).indexOf((int)word.charAt(wordindex))));
                }
                if (find==1) {
                    if (keyOccu.containsKey(key)) {
                        keyOccu.replace(key,keyOccu.get(key),keyOccu.get(key)+1);
                    } else {
                        keyOccu.put(key,1);
                    }
                    //System.out.print(key+"\n");
                }
            }
        }
        for (ArrayList<Integer> key: keyOccu.keySet()) {
            if (keyOccu.get(key) >= times) {
                System.out.println(key+" "+keyOccu.get(key));
            }
        }
    }

    private void decipher(ArrayList<Integer> ciphers, int[] keyword) {
        for (int i = 0; i < ciphers.size(); i++) {
            System.out.print(decryptMap.getPlain(ciphers.get(i), keyword[i%keyword.length]));
        }
    }

    //for testing for fileheaders
    private void possibleKey(ArrayList<Integer> ciphers, int[] plain) {
        boolean possible=true;
        int ph, pl, kh, kl, ch, cl;
        int res;

        for (int i = 0; i < plain.length; i++) {

            kh=kl=0;
            ph = plain[i]>>4;
            pl = plain[i]&0x0f;
            ch = ciphers.get(i)>>4;
            cl = ciphers.get(i)&0x0f;
            for (int j=0; j<16;++j){
                if(map[ph][j] == ch) kl = j;
                if(map[pl][j] == cl) kh = j;

            }
            System.out.print("Plain["+i+"]= "+plain[i]+", ciphers.get("+i+") = "+ciphers.get(i)+" results in ");
            System.out.print(""+(kh<<4)+"+"+kl+"= ");

            //cast converts to ascii
            res = ((kh<<4)+kl);
            System.out.print(""+res+" = ");
            System.out.println((char)res);
            if(!((res>31)&&(res<128))){
                possible = false;
                //System.out.println("              "+res+"               Is not actually possible");
            }

        }
        if (possible)System.out.println("    Is possible!!!!");
        else          System.out.println("    Is not possible");


    }

    private int[] toIntArray(String input) {
        ArrayList<Integer> al = new ArrayList<>();
        //return input as an array of Strings split on",(any number of whitespace charatcers)"
        //  parse each string into an int
        for (String s: input.substring(1,input.length()-1).split(",\\s*")) {
            al.add(Integer.parseInt(s));
        }
        //change ArrayList<Integer> to int[]
        int[] il = new int[al.size()];
        for (int i = 0; i<al.size(); i++) {
            il[i] = al.get(i);
        }
        return il;
    }

    private int[] hexToIntArray(String input) {
        ArrayList<Integer> al = new ArrayList<>();
        //return input as an array of Strings split on"(any number of whitespace charatcers)"
        //  parse each string into an int
        for (String s: input.substring(0,input.length()).split(" \\s*")) {
            try {
                al.add(Integer.parseInt(s,16));
            }catch (NumberFormatException e){
                System.out.println("Number format exception with: \""+s+"\"");
            }
        }
        //change ArrayList<Integer> to int[]
        int[] il = new int[al.size()];
        for (int i = 0; i<al.size(); i++) {
            il[i] = al.get(i);
        }
        return il;
    }
}
