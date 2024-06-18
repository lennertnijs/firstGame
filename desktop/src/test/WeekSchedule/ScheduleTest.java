package WeekSchedule;

import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.Schedule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    private final Time time1 = new Time(0, 10);
    private final Time time2 = new Time(0, 15);
    private final Activity activity1 = new Activity("walk", "main", new Point(5, 15), time1);
    private final Activity activity2 = new Activity("walk", "main", new Point(15, 25), time2);
    private final Schedule schedule = new Schedule(Arrays.asList(activity2, activity1));

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class,
                () -> new Schedule(null));
    }

    @Test
    public void testConstructorWithListContainingNull(){
        assertThrows(NullPointerException.class,
                () -> new Schedule(Arrays.asList(activity1, null)));
    }

    @Test
    public void testConstructorWithTwoActivitiesAtSameTime(){
        assertThrows(IllegalArgumentException.class,
                () -> new Schedule(Arrays.asList(activity1, activity2, activity1)));
    }

    @Test
    public void testGetActivityAt(){
        assertEquals(activity1, schedule.getActivityAt(time1));
        assertEquals(activity2, schedule.getActivityAt(time2));
        Time unmappedTime = new Time(6, 7);
        assertNull(schedule.getActivityAt(unmappedTime));
    }

    @Test
    public void testGetActivityAtWithNull(){
        assertThrows(NullPointerException.class,
                () -> schedule.getActivityAt(null));
    }

    @Test
    public void testEquals(){
        Schedule schedule1 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule schedule2 = new Schedule(Arrays.asList(activity2, activity1)); // equal, because of sorting
        Schedule schedule3 = new Schedule(Arrays.asList(activity1, activity2));
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
        Schedule diffActivities = new Schedule(Collections.singletonList(activity1));
        assertNotEquals(schedule1, diffActivities);
        assertNotEquals(schedule1, new Object());
        assertNotEquals(schedule1, null);
    }

    @Test
    public void testHashCode(){
        Schedule schedule1 = new Schedule(Arrays.asList(activity1, activity2));
        Schedule schedule2 = new Schedule(Arrays.asList(activity2, activity1)); // equal, because of sorting
        Schedule schedule3 = new Schedule(Arrays.asList(activity1, activity2));
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
        Schedule diffActivities = new Schedule(Collections.singletonList(activity1));
        assertNotEquals(schedule1.hashCode(), diffActivities.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Schedule[Activities=[" +
                "Activity[type=walk, map=main, position=Point[x=5, y=15], Time[hours=0, minutes=10]], " +
                "Activity[type=walk, map=main, position=Point[x=15, y=25], Time[hours=0, minutes=15]]" +
                "]]";
        assertEquals(expected, schedule.toString());
    }
}
