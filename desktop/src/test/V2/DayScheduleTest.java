package V2;

import com.mygdx.game.V2.WeekSchedule.Activity;
import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Position;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.DaySchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DayScheduleTest {


    private Activity activity1;
    private Activity activity2;
    private Activity activity3;
    private Activity activity4;
    private DaySchedule schedule1;
    private DaySchedule schedule2;
    private DaySchedule schedule3;

    @BeforeEach
    public void initialise(){
        Position position1 = Position.create(5, 15);
        Position position2 = Position.create(10, 20);
        Time time1 = Time.create(0, 55);
        Time time2 = Time.create(5, 55);
        Time time3 = Time.create(10, 55);
        Time time4 = Time.create(15,55);
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";

        activity1 = Activity.builder().position(position1).time(time1).type(type).mapName(mapName).build();
        activity2 = Activity.builder().position(position2).time(time2).type(type).mapName(mapName).build();
        activity3 = Activity.builder().position(position1).time(time3).type(type).mapName(mapName).build();
        activity4 = Activity.builder().position(position1).time(time4).type(type).mapName(mapName).build();

        schedule1 = DaySchedule.create(Arrays.asList(activity1, activity2, activity3));
        schedule2 = DaySchedule.create(Arrays.asList(activity2, activity3, activity4));
        schedule3 = DaySchedule.create(Arrays.asList(activity1, activity2, activity3));
    }

    @Test
    public void testConstructor(){
        assertEquals(schedule1.getActivities(), Arrays.asList(activity1, activity2, activity3));
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> DaySchedule.create(null));
        List<Activity> activitiesWithNull = new ArrayList<>();
        activitiesWithNull.add(null);
        assertThrows(NullPointerException.class, () -> DaySchedule.create(activitiesWithNull));
    }

    @Test
    public void testIsLastActivity(){
        assertTrue(schedule1.isLastActivity(activity3));
        assertFalse(schedule1.isLastActivity(activity2));
        assertFalse(schedule1.isLastActivity(activity4));
    }

    @Test
    public void testIsLastActivityWithNull(){
        assertThrows(NullPointerException.class, () -> schedule1.isLastActivity(null));
    }

    @Test
    public void testNextActivityByActivity(){
        assertEquals(schedule1.getNextActivity(activity1), activity2);
        assertEquals(schedule1.getNextActivity(activity2), activity3);
        assertThrows(NoSuchElementException.class, () -> schedule1.getNextActivity(activity3));
        assertThrows(NoSuchElementException.class, () -> schedule1.getNextActivity(activity4));
    }

    @Test
    public void testNextActivityByActivityWithNull(){
        assertThrows(NullPointerException.class, () -> schedule1.getNextActivity(null));
    }

    @Test
    public void testGetFirstActivityAfter(){
        Time time1 = Time.create(5, 0);
        Time time2 = Time.create(7, 0);
        Time time3 = Time.create(10, 59);
        assertEquals(schedule1.getFirstActivityAfter(time1), activity2);
        assertEquals(schedule1.getFirstActivityAfter(time2), activity3);
        assertThrows(NoSuchElementException.class, () -> schedule1.getFirstActivityAfter(time3));
    }

    @Test
    public void testNextActivityByTimeWithNull(){
        assertThrows(NullPointerException.class, () -> schedule1.getFirstActivityAfter(null));
    }

    @Test
    public void testEquals(){
        assertEquals(schedule1, schedule3);
        assertNotEquals(schedule1, schedule2);
        assertNotEquals(schedule1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(schedule1.hashCode(), schedule3.hashCode());
        assertNotEquals(schedule1.hashCode(), schedule2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "DaySchedule[Activities=[Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=0, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=10, y=20]], Time[hours=5, minutes=55], Type=IDLING], Activity[Location[mapName=world, Position[x=5, y=15]], Time[hours=10, minutes=55], Type=IDLING]]]";
        assertEquals(schedule1.toString(), expectedString);
    }
}
