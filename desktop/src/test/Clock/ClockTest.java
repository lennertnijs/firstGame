package Clock;

import com.mygdx.game.clock.CalendarClock;
import com.mygdx.game.clock.Clock;
import com.mygdx.game.clock.MockTimeProvider;
import com.mygdx.game.clock.TimeProvider;
import com.mygdx.game.npc.week_schedule.Day;
import com.mygdx.game.npc.week_schedule.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClockTest {

    private CalendarClock calendarClock;
    private TimeProvider timeProvider;
    private Clock clock;

    @BeforeEach
    public void initialise(){
        calendarClock = new CalendarClock(Day.MONDAY, new Time(5, 10));
        timeProvider = new MockTimeProvider();

        clock = new Clock(calendarClock, timeProvider);
    }

    @Test
    public void testConstructorWithNullCalendarClock(){
        assertThrows(NullPointerException.class,
                () -> new Clock(null, timeProvider));
    }

    @Test
    public void testConstructorWithNullTimeProvider(){
        assertThrows(NullPointerException.class,
                () -> new Clock(calendarClock, null));
    }

    @Test
    public void testGetDay(){
        assertEquals(Day.MONDAY, clock.getDay());
    }

    @Test
    public void testGetTime(){
        assertEquals(new Time(5, 10), clock.getTime());
    }

    @Test
    public void testIsActive(){
        assertTrue(clock.isActive());
    }

    @Test
    public void testPause(){
        clock.pause();
        assertFalse(clock.isActive());
    }

    @Test
    public void testStart(){
        clock.pause();
        assertFalse(clock.isActive());
        clock.start();
        assertTrue(clock.isActive());
    }

    @Test
    public void testUpdate(){
        double delta = clock.update();
        assertEquals(delta, 500);
        clock.update();
        assertEquals(new Time(5, 11), clock.getTime());
    }

    @Test
    public void testUpdateWithInactiveClock(){
        clock.update();
        clock.pause();
        double delta = clock.update();
        assertEquals(0, delta);
        assertEquals(new Time(5, 10), clock.getTime());
    }

    @Test
    public void testReset(){
        clock.update();
        clock.reset();
        clock.update();
        assertEquals(new Time(5, 10), clock.getTime());
    }
}
