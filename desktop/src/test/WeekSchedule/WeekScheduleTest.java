package WeekSchedule;

import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class WeekScheduleTest {

    private final Time time1 = new Time(0, 15);
    private final Time time2 = new Time(1, 15);
    private final Time time3 = new Time(2, 15);
    private final Location location = new Location("Map", new Point(0, 10));
    private final Activity activity1 = new Activity(location, time1, ActivityType.RUNNING);
    private final Activity activity2 = new Activity(location, time2, ActivityType.RUNNING);
    private final Activity activity3 = new Activity(location, time3, ActivityType.RUNNING);
    private final Day day1 = Day.MONDAY;
    private final Day day2 = Day.TUESDAY;
    private final Schedule schedule1  = new Schedule(Arrays.asList(activity1, activity2));
    private final Schedule schedule2  = new Schedule(Arrays.asList(activity2, activity3));
    private final Map<Day, Schedule> map = new HashMap<>();
    private IWeekSchedule weekSchedule;

    @BeforeEach
    public void initialise(){
        map.put(day1, schedule1);
        map.put(day2, schedule2);
        weekSchedule = new WeekSchedule(map);
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class,
                () -> new WeekSchedule(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        map.put(null, schedule2);
        assertThrows(NullPointerException.class,
                () -> new WeekSchedule(map));
    }

    @Test
    public void testConstructorWithNullValue(){
        map.put(Day.WEDNESDAY, null);
        assertThrows(NullPointerException.class,
                () -> new WeekSchedule(map));
    }

    @Test
    public void testHasActivity(){
        assertTrue(weekSchedule.hasActivity(day1, time1));
        assertTrue(weekSchedule.hasActivity(day1, time2));
        assertFalse(weekSchedule.hasActivity(day1, time3));

        assertFalse(weekSchedule.hasActivity(day2, time1));
        assertTrue(weekSchedule.hasActivity(day2, time2));
        assertTrue(weekSchedule.hasActivity(day2, time3));
    }

    @Test
    public void testHasActivityWithNullDay(){
        assertThrows(NullPointerException.class,
                () -> weekSchedule.hasActivity(null, time1));
    }

    @Test
    public void testHasActivityWithNullTime(){
        assertThrows(NullPointerException.class,
                () -> weekSchedule.hasActivity(day1, null));
    }

    @Test
    public void testHasActivityUnmappedDay(){
        assertThrows(NoSuchElementException.class,
                () -> weekSchedule.hasActivity(Day.WEDNESDAY, time1));
    }

    @Test
    public void testGetActivity(){
        assertEquals(activity1, weekSchedule.getActivity(day1, time1));
        assertEquals(activity2, weekSchedule.getActivity(day1, time2));
        assertEquals(activity2, weekSchedule.getActivity(day2, time2));
        assertEquals(activity3, weekSchedule.getActivity(day2, time3));
    }

    @Test
    public void testGetActivityNoneFound(){
        assertThrows(NoSuchElementException.class,
                () -> weekSchedule.getActivity(day1, time3));
    }

    @Test
    public void testGetActivityWithNullDay(){
        assertThrows(NullPointerException.class,
                () -> weekSchedule.getActivity(null, time1));
    }

    @Test
    public void testGetActivityWithNullTime(){
        assertThrows(NullPointerException.class,
                () -> weekSchedule.getActivity(day1, null));
    }

    @Test
    public void testGetActivityUnmappedDay(){
        assertThrows(NoSuchElementException.class,
                () -> weekSchedule.getActivity(Day.WEDNESDAY, time1));
    }

    @Test
    public void testEquals(){
        WeekSchedule weekSchedule1 = new WeekSchedule(map);
        WeekSchedule weekSchedule2 = new WeekSchedule(map);
        WeekSchedule weekSchedule3 = new WeekSchedule(map);
        map.put(Day.WEDNESDAY, schedule1);
        WeekSchedule diffWeekSchedule = new WeekSchedule(map);
        // reflexive
        assertEquals(weekSchedule1, weekSchedule1);
        // symmetrical
        assertEquals(weekSchedule1, weekSchedule2);
        assertEquals(weekSchedule2, weekSchedule1);
        // transitive
        assertEquals(weekSchedule1, weekSchedule2);
        assertEquals(weekSchedule2, weekSchedule3);
        assertEquals(weekSchedule1, weekSchedule3);
        // not equals
        assertNotEquals(weekSchedule1, diffWeekSchedule);
        assertNotEquals(weekSchedule1, new Object());
        assertNotEquals(weekSchedule1, null);
    }

    @Test
    public void testHashCode(){
        WeekSchedule weekSchedule1 = new WeekSchedule(map);
        WeekSchedule weekSchedule2 = new WeekSchedule(map);
        WeekSchedule weekSchedule3 = new WeekSchedule(map);
        map.put(Day.WEDNESDAY, schedule1);
        WeekSchedule diffWeekSchedule = new WeekSchedule(map);
        // reflexive
        assertEquals(weekSchedule1.hashCode(), weekSchedule1.hashCode());
        // symmetrical
        assertEquals(weekSchedule1.hashCode(), weekSchedule2.hashCode());
        assertEquals(weekSchedule2.hashCode(), weekSchedule1.hashCode());
        // transitive
        assertEquals(weekSchedule1.hashCode(), weekSchedule2.hashCode());
        assertEquals(weekSchedule2.hashCode(), weekSchedule3.hashCode());
        assertEquals(weekSchedule1.hashCode(), weekSchedule3.hashCode());
        // not equals
        assertNotEquals(weekSchedule1.hashCode(), diffWeekSchedule.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(weekSchedule.toString());
    }
}
