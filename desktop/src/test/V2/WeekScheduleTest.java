package V2;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeekScheduleTest {

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
        Time time1 = new Time(0, 15);
        Time time2 = new Time(1, 15);
        Time time3 = new Time(2, 15);
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

}
