import java.util.*;

public class Permutation{

    // Reference: http://stackoverflow.com/questions/35323412/generating-all-permutations-of-a-certain-length

    private ArrayList<String> result = new ArrayList<>();
    public Permutation(){

    }

    public void generate(String str){
        result.clear();
        permutation("",str);
    }

    public ArrayList<String> getResult() {
        return result;
    }

    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0){
            //System.out.println(prefix+"\n");
            result.add(prefix);
            return;
        }
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
        return;
    }
}
