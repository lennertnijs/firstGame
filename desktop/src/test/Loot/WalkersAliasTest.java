package Loot;

import com.mygdx.game.Loot.Bucket;
import com.mygdx.game.Loot.WalkersAlias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalkersAliasTest {

    private final String option1 = "option 1";
    private final double prob1 = 0.25d;
    private final String option2 = "option 2";
    private final double prob2 = 0.5d;
    private final String option3 = "option 3";
    private final double prob3 = 0.2d;
    private final String option4 = "option 4";
    private final double prob4 = 0.05d;
    private final List<String> objects = Arrays.asList(option1, option2, option3, option4);
    private final List<Double> probabilities = Arrays.asList(prob1, prob2, prob3, prob4);
    private final WalkersAlias<String> walkersAlias = new WalkersAlias<>();
    private List<Bucket<String>> buckets;

    @BeforeEach
    public void initialise(){
        buckets = walkersAlias.generateBuckets(objects, probabilities);
    }

    @Test
    public void testGenerateBucketsWithNullListOfObjects(){
        assertThrows(NullPointerException.class,
                () -> walkersAlias.generateBuckets(null, probabilities));
    }

    @Test
    public void testGenerateBucketsWithNullInObjectsList(){
        assertThrows(NullPointerException.class,
                () -> walkersAlias.generateBuckets(Arrays.asList(option1, null), probabilities));
    }

    @Test
    public void testGenerateBucketsWithNullListOfProbabilities(){
        assertThrows(NullPointerException.class,
                () -> walkersAlias.generateBuckets(objects, null));
    }

    @Test
    public void testGenerateBucketsWithNullInProbabilitiesList(){
        assertThrows(NullPointerException.class,
                () -> walkersAlias.generateBuckets(objects, Arrays.asList(prob1, null)));
    }

    @Test
    public void testGenerateBucketsWithDifferentListSizes(){
        assertThrows(IllegalArgumentException.class,
                () -> walkersAlias.generateBuckets(Arrays.asList(option1, option2), probabilities));
    }

    @Test
    public void testGenerateBucketsWithProbabilityCountSmallerThan1(){
        assertThrows(IllegalArgumentException.class,
                () -> walkersAlias.generateBuckets(objects, Arrays.asList(prob1, prob3, prob3, prob3)));
    }

    @Test
    public void testGenerateBucketsWithProbabilityCountBiggerThan1(){
        assertThrows(IllegalArgumentException.class,
            () -> walkersAlias.generateBuckets(objects, Arrays.asList(prob1, prob2, prob2, prob3)));
    }


    @Test
    public void testGeneratedBuckets1(){
        int option1Count = 0;
        int option2Count = 0;
        int option3Count = 0;
        int option4Count = 0;
        for(int i = 0; i < 100000; i++){
            int randomIndex = (int) (Math.random() * 4);
            String pick = buckets.get(randomIndex).getRandomOption();
            if(pick.equals(option1)) {
                option1Count++;
            }
            if(pick.equals(option2)){
                option2Count++;
            }
            if(pick.equals(option3)){
                option3Count++;
            }
            if(pick.equals(option4)){
                option4Count++;
            }
        }
        // should generally work
        assertTrue(24500 < option1Count && option1Count < 25500);
        assertTrue(49500 < option2Count && option2Count < 50500);
        assertTrue(19500 < option3Count && option3Count < 20500);
        assertTrue(4500 < option4Count && option4Count < 5500);
    }

    /**
     * avg = 0.333
     * underfull = 0,1 option1
     * full = {}
     * overfull = 0.5 option2, 0.4 option3
     */

    /**
     * underfull = {0.27 option2}
     * full = {0,1 option1, 0,23 option2}
     * overfull = {0.4 option3}
     */

    /**
     * underfull =
     */
    @Test
    public void testGeneratedBuckets2(){
        List<String> objects = Arrays.asList("Option 1", "Option 2", "Option 3");
        List<Double> probabilities = Arrays.asList(0.1d, 0.5d, 0.4d);
        buckets = walkersAlias.generateBuckets(objects, probabilities);

        int option1Count = 0;
        int option2Count = 0;
        int option3Count = 0;
        for(int i = 0; i < 100000; i++){
            int randomIndex = (int) (Math.random() * 3);
            String pick = buckets.get(randomIndex).getRandomOption();
            if(pick.equals("Option 1")) {
                option1Count++;
            }
            if(pick.equals("Option 2")){
                option2Count++;
            }
            if(pick.equals("Option 3")){
                option3Count++;
            }
        }
        // should generally work
        assertTrue(9500 < option1Count && option1Count < 10500);
        assertTrue(49500 < option2Count && option2Count < 50500);
        assertTrue(39500 < option3Count && option3Count < 40500);
    }
}
