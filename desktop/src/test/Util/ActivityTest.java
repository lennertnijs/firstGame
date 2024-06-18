package Util;

import com.mygdx.game.Util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private final String activityType = "run";
    private final String map = "main";
    private final Point position = new Point(5, 10);
    private final Time time = new Time(5, 10);
    private final Activity activity = new Activity(activityType, map, position, time);

    @Test
    public void testConstructorWithNullType(){
        assertThrows(NullPointerException.class,
                () -> new Activity(null, map, position, time));
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class,
                () -> new Activity(activityType, null, position, time));
    }

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class,
                () -> new Activity(activityType, map, null, time));
    }

    @Test
    public void testConstructorWithNullTime(){
        assertThrows(NullPointerException.class,
                () -> new Activity(activityType, map, position, null));
    }

    @Test
    public void testGetActivityType(){
        assertEquals(activityType, activity.type());
    }

    @Test
    public void testGetMap(){
        assertEquals(map, activity.map());
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, activity.position());
    }


    @Test
    public void testGetTime(){
        assertEquals(time, activity.time());
    }

    @Test
    public void testEquals(){
        Activity activity1 = new Activity(activityType, map, position, time);
        Activity activity2 = new Activity(activityType, map, position, time);
        Activity activity3 = new Activity(activityType, map, position, time);
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
        Activity diffActivityType = new Activity("other string", map, position, time);
        Activity diffGameMap = new Activity(activityType, "mine", position, time);
        Activity diffPosition = new Activity(activityType, map, new Point(6, 7), time);
        Activity diffTime = new Activity(activityType, map, position, new Time(6, 57));
        assertNotEquals(activity1, diffActivityType);
        assertNotEquals(activity1, diffGameMap);
        assertNotEquals(activity1, diffPosition);
        assertNotEquals(activity1, diffTime);
        assertNotEquals(activity1, new Object());
        assertNotEquals(activity1, null);
    }

    @Test
    public void testHashCode(){
        Activity activity1 = new Activity(activityType, map, position, time);
        Activity activity2 = new Activity(activityType, map, position, time);
        Activity activity3 = new Activity(activityType, map, position, time);
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
        Activity diffActivityType = new Activity("other string", map, position, time);
        Activity diffGameMap = new Activity(activityType, "mine", position, time);
        Activity diffPosition = new Activity(activityType, map, new Point(6, 7), time);
        Activity diffTime = new Activity(activityType, map, position, new Time(6, 57));
        assertNotEquals(activity1.hashCode(), diffActivityType.hashCode());
        assertNotEquals(activity1.hashCode(), diffGameMap.hashCode());
        assertNotEquals(activity1.hashCode(), diffPosition.hashCode());
        assertNotEquals(activity1.hashCode(), diffTime.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Activity[type=run, map=main, position=Point[x=5, y=10], Time[hours=5, minutes=10]]";
        assertEquals(expected, activity.toString());
    }
}
