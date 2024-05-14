package Util;

import com.mygdx.game.Util.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TimeTest {

    private final int hours = 5;
    private final int minutes = 10;
    private Time time;

    @BeforeEach
    public void initialise(){
        time = new Time(hours, minutes);
    }

    @Test
    public void testConstructorWithNegativeHours(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(-1, minutes));
    }

    @Test
    public void testConstructorWithHoursTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(Time.HOURS_PER_DAY, minutes));
    }

    @Test
    public void testConstructorWithNegativeMinutes(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(hours, -1));
    }

    @Test
    public void testConstructorWithMinutesTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(hours, Time.MINUTES_PER_HOUR));
    }

    @Test
    public void testConstructorWithOnlyMinutesNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(-1));
    }

    @Test
    public void testConstructorWithOnlyMinutesTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Time(Time.MINUTES_PER_DAY));
    }

    @Test
    public void testHours(){
        assertEquals(5, time.hours());
    }

    @Test
    public void testMinutes(){
        assertEquals(10, time.minutes());
    }

    @Test
    public void testAsMinutes(){
        assertEquals(5 * Time.MINUTES_PER_HOUR + 10, time.asMinutes());
    }

    @Test
    public void testIncrease(){
        Time increase1 = time.increase(Time.MINUTES_PER_HOUR);
        assertEquals(new Time(6, 10), increase1);
        Time increase2 = time.increase(23 * Time.MINUTES_PER_HOUR);
        assertEquals(new Time(4, 10), increase2);
        Time increase3 = time.increase(Time.MINUTES_PER_DAY);
        assertEquals(new Time(5, 10), increase3);
    }

    @Test
    public void testIncreaseWithNegativeIncrease(){
        assertThrows(IllegalArgumentException.class,
                () -> time.increase(-1));
    }

    @Test
    public void testIncreaseWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> time.increase(0));
    }

    @Test
    public void testCompareTo(){
        Time time1 = new Time(5, 9);
        Time time2 = new Time(5, 10);
        assertEquals(-1, time1.compareTo(time2));
        assertEquals(0, time1.compareTo(time1));
        assertEquals(1, time2.compareTo(time1));
    }

    @Test
    public void testCompareToWithNull(){
        assertThrows(NullPointerException.class,
                () -> time.compareTo(null));
    }

    @Test
    public void testEquals(){
        Time time1 = new Time(hours, minutes);
        Time time2 = new Time(hours, minutes);
        Time time3 = new Time(hours, minutes);
        Time diffHours = new Time(10, minutes);
        Time diffMinutes = new Time(hours, 20);
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
        assertNotEquals(time1, diffHours);
        assertNotEquals(time1, diffMinutes);
        assertNotEquals(time1, new Object());
        assertNotEquals(time1, null);
    }

    @Test
    public void testHashCode(){
        Time time1 = new Time(hours, minutes);
        Time time2 = new Time(hours, minutes);
        Time time3 = new Time(hours, minutes);
        Time diffHours = new Time(10, minutes);
        Time diffMinutes = new Time(hours, 20);
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
        assertNotEquals(time1.hashCode(), diffHours.hashCode());
        assertNotEquals(time1.hashCode(), diffMinutes.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Time[hours=5, minutes=10]";
        assertEquals(expected, time.toString());
    }
}
