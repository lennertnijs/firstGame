package V2;

import com.mygdx.game.V2.Settings;
import com.mygdx.game.V2.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
    public void testConstructorInvalid(){
        assertThrows(IllegalArgumentException.class, () -> Time.create(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> Time.create(Settings.HOURS_PER_DAY, 50));
        assertThrows(IllegalArgumentException.class, () -> Time.create(0, -1));
        assertThrows(IllegalArgumentException.class, () -> Time.create(0, Settings.MINUTES_PER_HOUR));
    }

    @Test
    public void testBefore(){
        assertTrue(time1.before(time2));
        assertFalse(time2.before(time1));
        assertFalse(time1.before(time3));
    }

    @Test
    public void testBeforeWithNull(){
        assertThrows(NullPointerException.class, () -> time1.before(null));
    }

    @Test
    public void testAfter(){
        assertTrue(time2.after(time1));
        assertFalse(time1.after(time2));
        assertFalse(time1.after(time3));
    }

    @Test
    public void testAfterWithNull(){
        assertThrows(NullPointerException.class, () -> time1.after(null));
    }

    @Test
    public void testEquals(){
        assertEquals(time1, time3);
        assertNotEquals(time1, time2);
        assertNotEquals(time1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(time1.hashCode(), time3.hashCode());
        assertNotEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Time[hours=20, minutes=15]";
        assertEquals(time1.toString(), expectedString);
    }
}
