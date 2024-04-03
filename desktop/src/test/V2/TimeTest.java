package V2;

import com.mygdx.game.V2.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {


    private Time time1;
    private Time time2;
    private Time time3;
    @BeforeEach
    public void initialise(){
        time1 = Time.create(20, 15);
        time2 = Time.create(23, 59);
        time3 = Time.create(20, 15);
    }

    @Test
    public void testConstructor(){
        Time time = Time.create(6, 30);
        assertEquals(time.getMinutes(), 30);
        assertEquals(time.getHours(), 6);
    }

    @Test
    public void testIsBefore(){
        Assertions.assertTrue(time2.before(time1));
        Assertions.assertFalse(time1.before(time2));
        Assertions.assertFalse(time2.before(time3));
    }
}
