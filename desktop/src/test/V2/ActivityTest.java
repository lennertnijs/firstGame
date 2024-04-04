package V2;

import com.mygdx.game.V2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private Activity activity1;
    private Activity activity2;
    private Activity activity3;
    @BeforeEach
    public void initialise(){
        Position position1 = Position.create(5, 15);
        Position position2 = Position.create(10, 20);
        Time time = Time.create(16, 55);
        NPCActivityType type = NPCActivityType.IDLING;
        String mapName = "world";

        activity1 = Activity.builder().position(position1).time(time).type(type).mapName(mapName).build();
        activity2 = Activity.builder().position(position2).time(time).type(type).mapName(mapName).build();
        activity3 = Activity.builder().position(position1).time(time).type(type).mapName(mapName).build();
    }

    @Test
    public void testDirectConstructor(){
        Position position = Position.create(5, 15);
        Time time = Time.create(16, 55);
        NPCActivityType type = NPCActivityType.IDLING;
        String mapName = "world";
        Activity activity = Activity.builder().position(position).time(time).type(type).mapName(mapName).build();

        assertEquals(activity.getLocation(), Location.create(mapName, position));
        assertEquals(activity.getTime(), time);
        assertEquals(activity.getType(), type);
    }

    @Test
    public void testIndirectConstructor(){
        int x = 5;
        int y = 15;
        int hours = 6;
        int minutes = 40;
        NPCActivityType type = NPCActivityType.IDLING;
        String mapName = "world";
        Activity activity = Activity.builder().x(x).y(y).hours(hours).minutes(minutes).type(type).mapName(mapName).build();

        assertEquals(activity.getLocation(), Location.create(mapName, Position.create(x, y)));
        assertEquals(activity.getTime(), Time.create(hours, minutes));
        assertEquals(activity.getType(), type);
    }

    @Test
    public void testConstructorInvalid(){
        int x = 5;
        int y = 15;
        int hours = 6;
        int minutes = 40;
        NPCActivityType type = NPCActivityType.IDLING;
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
        String expectedString = "Activity[MapPosition[mapName=world, Position[x=5, y=15]], Time[hours=16, minutes=55], Type=IDLING]";
        assertEquals(activity1.toString(), expectedString);
    }
}
