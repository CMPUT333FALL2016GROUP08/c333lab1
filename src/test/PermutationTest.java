import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by michaelximac on 2016-10-14.
 */
public class PermutationTest {
    @Test
    public void perTest(){
        Permutation permutation= new Permutation();

        // "a"-->"[a]"
        permutation.generate("a");
        assertEquals("[a]",permutation.getResult().toString());

        // "abc"-->"[abc, acb, bac, bca, cab, cba]"
        permutation.generate("abc");
        assertEquals("[abc, acb, bac, bca, cab, cba]",permutation.getResult().toString());

    }

}