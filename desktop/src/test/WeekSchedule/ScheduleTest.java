package WeekSchedule;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    private Time time1;
    private Time time2;
    private Activity activity1;
    private Activity activity2;
    private Schedule schedule;
    @BeforeEach
    public void initialise(){
        time1 = new Time(0, 10);
        time2 = new Time(0, 15);
        Location location = new Location("Map", new Point(5, 15));
        activity1 = new Activity(location, time1, ActivityType.RUNNING);
        activity2 = new Activity(location, time2, ActivityType.RUNNING);
        schedule = new Schedule(Arrays.asList(activity2, activity1));
    }

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class, () -> new Schedule(null));
    }

    @Test
    public void testConstructorWithListContainingNull(){
        assertThrows(NullPointerException.class, () -> new Schedule(Arrays.asList(activity1, null)));
    }

    @Test
    public void testConstructorWithTwoActivitiesAtSameTime(){
        assertThrows(IllegalArgumentException.class, () -> new Schedule(Arrays.asList(activity1, activity1)));
    }

    @Test
    public void testActivities(){
        assertEquals(Arrays.asList(activity1, activity2), schedule.activities());
    }

    @Test
    public void testHasActivityAt(){
        assertFalse(schedule.hasActivityAt(new Time(0, 9)));
        assertTrue(schedule.hasActivityAt(time1));
        assertFalse(schedule.hasActivityAt(new Time(0, 11)));
        assertTrue(schedule.hasActivityAt(time2));
        assertFalse(schedule.hasActivityAt(new Time(0, 16)));
    }

    @Test
    public void testHasActivityAtWithNull(){
        assertThrows(NullPointerException.class, () -> schedule.hasActivityAt(null));
    }

    @Test
    public void testGetActivityAt(){
        assertEquals(activity1, schedule.getActivityAt(time1));
        assertEquals(activity2, schedule.getActivityAt(time2));
    }

    @Test
    public void testGetActivityAtNullTime(){
        assertThrows(NullPointerException.class, () -> schedule.getActivityAt(null));
    }

    @Test
    public void testGetActivityAtNotExists(){
        assertThrows(NoSuchElementException.class, () -> schedule.getActivityAt(new Time(0, 9)));
    }

    @Test
    public void testEquals(){
        Schedule schedule1 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule schedule2 = new Schedule(Arrays.asList(activity2, activity1));
        Schedule schedule3 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule diffSchedule = new Schedule(Collections.singletonList(activity1));
        // reflexive
        assertEquals(schedule1, schedule1);
        // symmetrical
        assertEquals(schedule1, schedule2);
        assertEquals(schedule2, schedule1);
        // transitive
        assertEquals(schedule1, schedule2);
        assertEquals(schedule2, schedule3);
        assertEquals(schedule1, schedule3);
        // not equals
        assertNotEquals(schedule1, diffSchedule);
        assertNotEquals(schedule1, new Object());
        assertNotEquals(schedule1, null);
    }

    @Test
    public void testHashCode(){
        Schedule schedule1 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule schedule2 = new Schedule(Arrays.asList(activity2, activity1));
        Schedule schedule3 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule diffSchedule = new Schedule(Collections.singletonList(activity1));
        // reflexive
        assertEquals(schedule1.hashCode(), schedule1.hashCode());
        // symmetrical
        assertEquals(schedule1.hashCode(), schedule2.hashCode());
        assertEquals(schedule2.hashCode(), schedule1.hashCode());
        // transitive
        assertEquals(schedule1.hashCode(), schedule2.hashCode());
        assertEquals(schedule2.hashCode(), schedule3.hashCode());
        assertEquals(schedule1.hashCode(), schedule3.hashCode());
        // not equals
        assertNotEquals(schedule1.hashCode(), diffSchedule.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Schedule[Activities=[" +
                "Activity[Location[mapName=Map, position=Point[x=5, y=15]], Time[hours=0, minutes=10], Type=RUNNING], " +
                "Activity[Location[mapName=Map, position=Point[x=5, y=15]], Time[hours=0, minutes=15], Type=RUNNING]]" +
                "]";
        assertEquals(expected, schedule.toString());
    }

}
