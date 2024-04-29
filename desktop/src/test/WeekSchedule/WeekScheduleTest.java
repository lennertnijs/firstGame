package WeekSchedule;

import com.mygdx.game.V2.Util.*;
import com.mygdx.game.V2.WeekSchedule.Activity;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;
import com.mygdx.game.V2.WeekSchedule.Schedule;
import com.mygdx.game.V2.WeekSchedule.WeekSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class WeekScheduleTest {

    private Time time1;
    private Time time2;
    private Time time3;
    private Activity activity1;
    private Activity activity2;
    private Activity activity3;
    private Day day1;
    private Day day2;
    private Schedule schedule1;
    private Schedule schedule2;
    private Map<Day, Schedule> map;
    private IWeekSchedule weekSchedule;

    @BeforeEach
    public void initialise(){
        time1 = new Time(0, 15);
        time2 = new Time(1, 15);
        time3 = new Time(2, 15);
        Location location = new Location("Map", new Point(0, 10));
        activity1 = new Activity(location, time1, ActivityType.RUNNING);
        activity2 = new Activity(location, time2, ActivityType.RUNNING);
        activity3 = new Activity(location, time3, ActivityType.RUNNING);
        day1 = Day.MONDAY;
        day2 = Day.TUESDAY;
        schedule1 = new Schedule(Arrays.asList(activity1, activity2));
        schedule2 = new Schedule(Arrays.asList(activity2, activity3));

        map = new HashMap<>();
        map.put(day1, schedule1);
        map.put(day2, schedule2);
        weekSchedule = new WeekSchedule(map);
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class, () -> new WeekSchedule(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        map.put(null, schedule2);
        assertThrows(NullPointerException.class, () -> new WeekSchedule(map));
    }

    @Test
    public void testConstructorWithNullValue(){
        map.put(Day.WEDNESDAY, null);
        assertThrows(NullPointerException.class, () -> new WeekSchedule(map));
    }

    @Test
    public void testSchedules(){
        assertEquals(map, weekSchedule.schedules());
    }

    @Test
    public void testGetDaySchedule(){
        assertEquals(schedule1, weekSchedule.getDaySchedule(day1));
        assertEquals(schedule2, weekSchedule.getDaySchedule(day2));
    }

    @Test
    public void testGetDayScheduleWithNull(){
        assertThrows(NullPointerException.class, () -> weekSchedule.getDaySchedule(null));
    }

    @Test
    public void testGetDayScheduleUnmappedDay(){
        assertThrows(NoSuchElementException.class, () -> weekSchedule.getDaySchedule(Day.WEDNESDAY));
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
        assertThrows(NullPointerException.class, () -> weekSchedule.hasActivity(null, time1));
    }

    @Test
    public void testHasActivityWithNullTime(){
        assertThrows(NullPointerException.class, () -> weekSchedule.hasActivity(day1, null));
    }

    @Test
    public void testHasActivityUnmappedDay(){
        assertThrows(NoSuchElementException.class, () -> weekSchedule.hasActivity(Day.WEDNESDAY, time1));
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
        assertThrows(NoSuchElementException.class, () -> weekSchedule.getActivity(day1, time3));
    }

    @Test
    public void testGetActivityWithNullDay(){
        assertThrows(NullPointerException.class, () -> weekSchedule.getActivity(null, time1));
    }

    @Test
    public void testGetActivityWithNullTime(){
        assertThrows(NullPointerException.class, () -> weekSchedule.getActivity(day1, null));
    }

    @Test
    public void testGetActivityUnmappedDay(){
        assertThrows(NoSuchElementException.class, () -> weekSchedule.getActivity(Day.WEDNESDAY, time1));
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
