package WeekSchedule;

import com.mygdx.game.Keys.NPCActivityType;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mygdx.game.Keys.NPCActivityType.RUNNING;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private final Location location = new Location("Map", new Point(5, 10));
    private final Time time = new Time(5, 10);
    private final NPCActivityType NPCActivityType = RUNNING;
    private Activity activity;

    @BeforeEach
    public void initialise(){
        activity = new Activity(location, time, NPCActivityType);
    }

    @Test
    public void testConstructorWithNullLocation(){
        assertThrows(NullPointerException.class,
                () -> new Activity(null, time, NPCActivityType));
    }

    @Test
    public void testConstructorWithNullTime(){
        assertThrows(NullPointerException.class,
                () -> new Activity(location, null, NPCActivityType));
    }

    @Test
    public void testConstructorWithNullActivityType(){
        assertThrows(NullPointerException.class,
                () -> new Activity(location, time, null));
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
        assertEquals(NPCActivityType, activity.type());
    }

    @Test
    public void testEquals(){
        Activity activity1 = new Activity(location, time, NPCActivityType);
        Activity activity2 = new Activity(location, time, NPCActivityType);
        Activity activity3 = new Activity(location, time, NPCActivityType);
        Activity diffLocation = new Activity(new Location("Diff", new Point(5, 10)), time, NPCActivityType);
        Activity diffTime = new Activity(location, new Time(0, 15), NPCActivityType);
        Activity diffType = new Activity(location, time, NPCActivityType.MINING);
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
        assertNotEquals(activity1, diffLocation);
        assertNotEquals(activity1, diffTime);
        assertNotEquals(activity1, diffType);
        assertNotEquals(activity1, new Object());
        assertNotEquals(activity1, null);
    }

    @Test
    public void testHashCode(){
        Activity activity1 = new Activity(location, time, NPCActivityType);
        Activity activity2 = new Activity(location, time, NPCActivityType);
        Activity activity3 = new Activity(location, time, NPCActivityType);
        Activity diffLocation = new Activity(new Location("Diff", new Point(5, 10)), time, NPCActivityType);
        Activity diffTime = new Activity(location, new Time(0, 15), NPCActivityType);
        Activity diffType = new Activity(location, time, NPCActivityType.MINING);
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
        assertNotEquals(activity1.hashCode(), diffLocation.hashCode());
        assertNotEquals(activity1.hashCode(), diffTime.hashCode());
        assertNotEquals(activity1.hashCode(), diffType.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Activity[" +
                "Location[mapName=Map, position=Point[x=5, y=10]], " +
                "Time[hours=5, minutes=10], " +
                "Type=RUNNING" +
                "]";
        assertEquals(expected, activity.toString());
    }
}
