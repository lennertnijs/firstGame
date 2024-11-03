package Loot;

import com.mygdx.game.loot.Bucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BucketTest {

    private final String option1 = "Option 1.";
    private final double probability1 = 0.125d;
    private final String option2 = "Option 2.";
    private final double probability2 = 0.375d;
    private Bucket<String> bucket;

    @BeforeEach
    public void initialise(){
        bucket = new Bucket<>(option1, probability1);
    }

    @Test
    public void testConstructorWithNullFirstOption(){
        assertThrows(NullPointerException.class,
                () -> new Bucket<>(null, probability1));
    }

    @Test
    public void testConstructorWithNegativeFirstProbability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Bucket<>(option1, -0.1d));
    }

    @Test
    public void testConstructorWithZeroFirstProbability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Bucket<>(option1, 0));
    }

    @Test
    public void testConstructorWith1FirstProbability(){
        new Bucket<>(option1, probability1);
    }

    @Test
    public void testConstructorWithFirstProbabilityBiggerThanOne(){
        assertThrows(IllegalArgumentException.class,
                () -> new Bucket<>(option1, 1.1d));
    }

    @Test
    public void testGetFirstOption(){
        assertEquals(option1, bucket.getFirstOption());
    }

    @Test
    public void testGetFirstProbability(){
        assertEquals(probability1, bucket.getFirstProbability());
    }

    @Test
    public void testGetSecondOption(){
        assertNull(bucket.getSecondOption());
    }

    @Test
    public void testGetSecondProbability(){
        assertEquals(0, bucket.getSecondProbability());
    }

    @Test
    public void setFirstOption(){
        bucket.setFirstOption("New option 1.");
        assertEquals("New option 1.", bucket.getFirstOption());
    }

    @Test
    public void setFirstOptionToNull(){
        assertThrows(NullPointerException.class,
                () -> bucket.setFirstOption(null));
    }

    @Test
    public void testSetFirstProbability(){
        bucket.setFirstProbability(0.5d);
        assertEquals(0.5d, bucket.getFirstProbability());
    }

    @Test
    public void testSetFirstProbabilityToNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setFirstProbability(-0.1d));
    }

    @Test
    public void testSetFirstProbabilityToZero(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setFirstProbability(0));
    }

    @Test
    public void testSetFirstProbabilityToOne(){
        bucket.setFirstProbability(1);
    }

    @Test
    public void testSetFirstProbabilityToBiggerThan1(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setFirstProbability(1.1d));
    }

    @Test
    public void setSecondOption(){
        bucket.setSecondOption(option2);
        assertEquals(option2, bucket.getSecondOption());
    }

    @Test
    public void setSecondOptionToNull(){
        assertThrows(NullPointerException.class,
                () -> bucket.setSecondOption(null));
    }

    @Test
    public void testSetSecondProbability(){
        bucket.setSecondProbability(probability2);
        assertEquals(probability2, bucket.getSecondProbability());
    }

    @Test
    public void testSetSecondProbabilityToNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setSecondProbability(-0.1d));
    }

    @Test
    public void testSetSecondProbabilityToZero(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setSecondProbability(0));
    }

    @Test
    public void testSetSecondProbabilityToOne(){
        bucket.setSecondProbability(1);
    }

    @Test
    public void testSetSecondProbabilityToBiggerThan1(){
        assertThrows(IllegalArgumentException.class,
                () -> bucket.setSecondProbability(1.1d));
    }

    @Test
    public void testGetRandomOption(){
        bucket.setSecondOption(option2);
        bucket.setSecondProbability(probability2);

        int firstCount = 0;
        int secondCount = 0;
        for(int i = 0; i < 100000; i++){
            String result = bucket.getRandomOption();
            if(result.equals(option1))
                firstCount++;
            else
                secondCount++;
        }
        // could technically fail. though it should pass in basically any case
        assertTrue(24500 < firstCount && firstCount < 25500);
        assertTrue(74500 < secondCount && secondCount < 75500);
    }
}
