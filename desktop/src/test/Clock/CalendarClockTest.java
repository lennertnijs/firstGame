package Clock;

import game.clock.CalendarClock;
import game.util.Day;
import game.npc.week_schedule.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarClockTest {

    private Day day;
    private Time time;
    private CalendarClock clock;

    @BeforeEach
    public void initialise(){
        day = Day.MONDAY;
        time = new Time(5, 10);

        clock = new CalendarClock(day, time);
    }

    @Test
    public void testConstructorWithNullDay(){
        assertThrows(NullPointerException.class,
                () -> new CalendarClock(null, time));
    }

    @Test
    public void testConstructorWithNullTime(){
        assertThrows(NullPointerException.class,
                () -> new CalendarClock(day, null));
    }

    @Test
    public void testGetDay(){
        assertEquals(day, clock.getDay());
    }

    @Test
    public void testGetTime(){
        assertEquals(time, clock.getTime());
    }

    @Test
    public void testIncreaseTime(){
        clock.increaseTime(60);
        assertEquals(day, clock.getDay());
        assertEquals(new Time(6, 10), clock.getTime());

        clock.increaseTime(22*60);
        assertEquals(Day.TUESDAY, clock.getDay());
        assertEquals(new Time(4, 10), clock.getTime());
    }

    @Test
    public void testIncreaseTimeWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> clock.increaseTime(-1));
    }

    @Test
    public void testIncreaseTimeWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> clock.increaseTime(0));
    }

    @Test
    public void testEquals(){
        CalendarClock clock1 = new CalendarClock(day, time);
        CalendarClock clock2 = new CalendarClock(day, time);
        CalendarClock clock3 = new CalendarClock(day, time);
        CalendarClock diffDay = new CalendarClock(Day.TUESDAY, time);
        CalendarClock diffTime = new CalendarClock(day, new Time(4, 15));
        // reflexive
        assertEquals(clock1, clock1);
        // symmetrical
        assertEquals(clock1, clock2);
        assertEquals(clock2, clock1);
        // transitive
        assertEquals(clock1, clock2);
        assertEquals(clock2, clock3);
        assertEquals(clock1, clock3);
        // not equals
        assertNotEquals(clock1, diffDay);
        assertNotEquals(clock1, diffTime);
        assertNotEquals(clock1, new Object());
        assertNotEquals(clock1, null);
    }

    @Test
    public void testHashCide(){
        CalendarClock clock1 = new CalendarClock(day, time);
        CalendarClock clock2 = new CalendarClock(day, time);
        CalendarClock clock3 = new CalendarClock(day, time);
        CalendarClock diffDay = new CalendarClock(Day.TUESDAY, time);
        CalendarClock diffTime = new CalendarClock(day, new Time(4, 15));
        // reflexive
        assertEquals(clock1.hashCode(), clock1.hashCode());
        // symmetrical
        assertEquals(clock1.hashCode(), clock2.hashCode());
        assertEquals(clock2.hashCode(), clock1.hashCode());
        // transitive
        assertEquals(clock1.hashCode(), clock2.hashCode());
        assertEquals(clock2.hashCode(), clock3.hashCode());
        assertEquals(clock1.hashCode(), clock3.hashCode());
        // not equals
        assertNotEquals(clock1.hashCode(), diffDay.hashCode());
        assertNotEquals(clock1.hashCode(), diffTime.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "CalendarClock[day=MONDAY, time=Time[hours=5, minutes=10]]";
        assertEquals(expected, clock.toString());
    }


    @Test
    public void testCopy(){
        CalendarClock copy = clock.copy();
        assertEquals(clock, copy);
        copy.increaseTime(12);
        assertNotEquals(clock, copy);
    }
}
