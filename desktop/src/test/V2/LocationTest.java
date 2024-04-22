package V2;

import com.mygdx.game.V2.WeekSchedule.Location;
import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private Location location1;
    private Location location2;
    private Location location3;

    @BeforeEach
    public void initialise(){
        location1 = Location.create("map1", new Point(0,0));
        location2 = Location.create("map2", new Point(0,0));
        location3 = Location.create("map1", new Point(0,0));
    }

    @Test
    public void testConstructor(){
        assertEquals(location1.getMapName(), "map1");
        assertEquals(location1.getPosition(), new Point(0,0));
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> Location.create(null, new Point(0,0)));
        assertThrows(NullPointerException.class, () -> Location.create("map1", null));
    }

    @Test
    public void testEquals(){
        assertEquals(location1, location3);
        assertNotEquals(location1, location2);
        assertNotEquals(location1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(location1.hashCode(), location3.hashCode());
        assertNotEquals(location1.hashCode(), location2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Location[mapName=map1, Point[x=0, y=0]]";
        assertEquals(location1.toString(), expectedString);
    }
}
