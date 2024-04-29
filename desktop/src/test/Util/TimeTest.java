package Util;

import com.mygdx.game.V2.Util.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class TimeTest {

    private Time time;

    @BeforeEach
    public void initialise(){
        time = new Time(5, 10);
    }

    @Test
    public void testConstructorInvalidHours(){
        assertThrows(IllegalArgumentException.class, () -> new Time(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Time(Time.HOURS_PER_DAY, 0));
    }

    @Test
    public void testConstructorInvalidMinutes(){
        assertThrows(IllegalArgumentException.class, () -> new Time(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Time(0, Time.MINUTES_PER_HOUR));
    }

    @Test
    public void testGetHours(){
        assertEquals(5, time.hours());
    }

    @Test
    public void testMinutes(){
        assertEquals(10, time.minutes());
    }

    @Test
    public void testBefore(){
        Time time1 = new Time(5, 9);
        Time time2 = new Time(5, 11);
        assertTrue(time1.before(time));
        assertFalse(time.before(time));
        assertFalse(time2.before(time));
    }

    @Test
    public void testBeforeNull(){
        assertThrows(NullPointerException.class, () -> time.before(null));
    }

    @Test
    public void testAfter(){
        Time time1 = new Time(5, 9);
        Time time2 = new Time(5, 11);
        assertTrue(time2.after(time));
        assertFalse(time.after(time));
        assertFalse(time1.after(time));
    }

    @Test
    public void testAfterNull(){
        assertThrows(NullPointerException.class, () -> time.after(null));
    }

    @Test
    public void testEquals(){
        Time time1 = new Time(5, 10);
        Time time2 = new Time(5, 10);
        Time time3 = new Time(5, 10);
        Time diffTime = new Time(10, 10);
        // reflexive
        assertEquals(time1, time1);
        // symmetrical
        assertEquals(time1, time2);
        assertEquals(time2, time1);
        // transitive
        assertEquals(time1, time2);
        assertEquals(time2, time3);
        assertEquals(time1, time3);
        // not equals
        assertNotEquals(time1, diffTime);
        assertNotEquals(time1, new Object());
        assertNotEquals(time1, null);
    }

    @Test
    public void testHashCode(){
        Time time1 = new Time(5, 10);
        Time time2 = new Time(5, 10);
        Time time3 = new Time(5, 10);
        Time diffTime = new Time(10, 10);
        // reflexive
        assertEquals(time1.hashCode(), time1.hashCode());
        // symmetrical
        assertEquals(time1.hashCode(), time2.hashCode());
        assertEquals(time2.hashCode(), time1.hashCode());
        // transitive
        assertEquals(time1.hashCode(), time2.hashCode());
        assertEquals(time2.hashCode(), time3.hashCode());
        assertEquals(time1.hashCode(), time3.hashCode());
        // not equals
        assertNotEquals(time1.hashCode(), diffTime.hours());
    }

    @Test
    public void testToString(){
        String expected = "Time[hours=5, minutes=10]";
        assertEquals(expected, time.toString());
    }

    @Test
    public void testCompareTo(){
        Time time1 = new Time(5, 9);
        Time time2 = new Time(5, 10);
        assertEquals(-1, time1.compareTo(time2));
        assertEquals(0, time2.compareTo(time2));
        assertEquals(1, time2.compareTo(time1));
    }

    @Test
    public void testCompareToWithNull(){
        assertThrows(NullPointerException.class, () -> time.compareTo(null));
    }
}
