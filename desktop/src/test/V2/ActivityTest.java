package V2;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private Activity activity1;
    private Activity activity2;
    private Activity activity3;
    @BeforeEach
    public void initialise(){
        Point point1 = new Point(5, 15);
        Point point2 = new Point(10, 20);
        Time time = new Time(16, 55);
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";

        activity1 = Activity.builder().position(point1).time(time).type(type).mapName(mapName).build();
        activity2 = Activity.builder().position(point2).time(time).type(type).mapName(mapName).build();
        activity3 = Activity.builder().position(point1).time(time).type(type).mapName(mapName).build();
    }

    @Test
    public void testDirectConstructor(){
        Point point = new Point(5, 15);
        Time time = new Time(16, 55);
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";
        Activity activity = Activity.builder().position(point).time(time).type(type).mapName(mapName).build();

        assertEquals(activity.getLocation(), new Location(mapName, point));
        assertEquals(activity.getTime(), time);
        assertEquals(activity.getType(), type);
    }

    @Test
    public void testIndirectConstructor(){
        int x = 5;
        int y = 15;
        int hours = 6;
        int minutes = 40;
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";
        Activity activity = Activity.builder().x(x).y(y).hours(hours).minutes(minutes).type(type).mapName(mapName).build();

        assertEquals(activity.getLocation(), new Location(mapName, new Point(x, y)));
        assertEquals(activity.getTime(), new Time(hours, minutes));
        assertEquals(activity.getType(), type);
    }

    @Test
    public void testConstructorInvalid(){
        int x = 5;
        int y = 15;
        int hours = 6;
        int minutes = 40;
        ActivityType type = ActivityType.IDLING;
        String mapName = "world";

        Activity.Builder invalidPosition = Activity.builder().x(x).y(-1).hours(hours).minutes(minutes).type(type).mapName(mapName);
        Activity.Builder invalidTime = Activity.builder().x(x).y(y).hours(hours).minutes(-1).type(type).mapName(mapName);
        Activity.Builder invalidType = Activity.builder().x(x).y(y).hours(hours).minutes(minutes).type(null).mapName(mapName);
        Activity.Builder invalidMapName = Activity.builder().x(x).y(y).hours(hours).minutes(minutes).type(type).mapName(null);

        assertThrows(IllegalArgumentException.class, invalidPosition::build);
        assertThrows(IllegalArgumentException.class, invalidTime::build);
        assertThrows(NullPointerException.class, invalidType::build);
        assertThrows(NullPointerException.class, invalidMapName::build);

    }

    @Test
    public void testEquals(){
        assertEquals(activity1, activity3);
        assertNotEquals(activity1, activity2);
        assertNotEquals(activity1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(activity1.hashCode(), activity3.hashCode());
        assertNotEquals(activity1.hashCode(), activity2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Activity[Location[mapName=world, position=Point[x=5, y=15]], Time[hours=16, minutes=55], Type=IDLING]";
        assertEquals(activity1.toString(), expectedString);
    }
}
