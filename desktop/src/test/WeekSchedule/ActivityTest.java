package WeekSchedule;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private Location location;
    private Time time;
    private ActivityType activityType;
    private Activity activity;

    @BeforeEach
    public void initialise(){
        location = new Location("Map", new Point(5, 10));
        time = new Time(10, 15);
        activityType = ActivityType.RUNNING;

        activity = new Activity(location, time, activityType);
    }

    @Test
    public void testConstructorWithNullLocation(){
        assertThrows(NullPointerException.class, () -> new Activity(null, time, activityType));
    }

    @Test
    public void testConstructorWithNullTime(){
        assertThrows(NullPointerException.class, () -> new Activity(location, null, activityType));
    }

    @Test
    public void testConstructorWithNullActivityType(){
        assertThrows(NullPointerException.class, () -> new Activity(location, time, null));
    }

    @Test
    public void testLocation(){
        assertEquals(location, activity.location());
    }

    @Test
    public void testTime(){
        assertEquals(time, activity.time());
    }

    @Test
    public void testActivityType(){
        assertEquals(activityType, activity.type());
    }

    @Test
    public void testEquals(){
        Activity activity1 = new Activity(location, time, activityType);
        Activity activity2 = new Activity(location, time, activityType);
        Activity activity3 = new Activity(location, time, activityType);
        Activity diffActivity = new Activity(location, new Time(0, 15), activityType);
        // reflexive
        assertEquals(activity1, activity1);
        // symmetrical
        assertEquals(activity1, activity2);
        assertEquals(activity2, activity1);
        // transitive
        assertEquals(activity1, activity2);
        assertEquals(activity2, activity3);
        assertEquals(activity1, activity3);
        // not equals
        assertNotEquals(activity1, diffActivity);
        assertNotEquals(activity1, new Object());
        assertNotEquals(activity1, null);
    }

    @Test
    public void testHashCode(){
        Activity activity1 = new Activity(location, time, activityType);
        Activity activity2 = new Activity(location, time, activityType);
        Activity activity3 = new Activity(location, time, activityType);
        Activity diffActivity = new Activity(location, new Time(0, 15), activityType);
        // reflexive
        assertEquals(activity1.hashCode(), activity1.hashCode());
        // symmetrical
        assertEquals(activity1.hashCode(), activity2.hashCode());
        assertEquals(activity2.hashCode(), activity1.hashCode());
        // transitive
        assertEquals(activity1.hashCode(), activity2.hashCode());
        assertEquals(activity2.hashCode(), activity3.hashCode());
        assertEquals(activity1.hashCode(), activity3.hashCode());
        // not equals
        assertNotEquals(activity1.hashCode(), diffActivity.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Activity[Location[mapName=Map, position=Point[x=5, y=10]], Time[hours=10, minutes=15], Type=RUNNING]";
        assertEquals(expected, activity.toString());
    }
}
