package Util;

import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private final String mapName = "Main";
    private final Point position = new Point(0, 0);
    private Location location;

    @BeforeEach
    public void initialise(){
        location = new Location(mapName, position);
    }

    @Test
    public void testConstructorWithNullMapName(){
        assertThrows(NullPointerException.class,
                () -> new Location(null, position));
    }

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class,
                () -> new Location(mapName, null));
    }

    @Test
    public void testMapName(){
        assertEquals(mapName, location.mapName());
    }

    @Test
    public void testPosition(){
        assertEquals(position, location.position());
    }

    @Test
    public void testEquals(){
        Location location1 = new Location(mapName, position);
        Location location2 = new Location(mapName, position);
        Location location3 = new Location(mapName, position);
        Location diffName = new Location("diff", position);
        Location diffPosition = new Location(mapName, new Point(15, 15));
        // reflexive
        assertEquals(location1, location1);
        // symmetrical
        assertEquals(location1, location2);
        assertEquals(location2, location1);
        // transitive
        assertEquals(location1, location2);
        assertEquals(location2, location3);
        assertEquals(location1, location3);
        // not equals
        assertNotEquals(location1, diffName);
        assertNotEquals(location1, diffPosition);
        assertNotEquals(location1, new Object());
        assertNotEquals(location1, null);
    }

    @Test
    public void testHashCode(){
        Location location1 = new Location(mapName, position);
        Location location2 = new Location(mapName, position);
        Location location3 = new Location(mapName, position);
        Location diffName = new Location("diff", position);
        Location diffPosition = new Location(mapName, new Point(15, 15));
        // reflexive
        assertEquals(location1.hashCode(), location1.hashCode());
        // symmetrical
        assertEquals(location1.hashCode(), location2.hashCode());
        assertEquals(location2.hashCode(), location1.hashCode());
        // transitive
        assertEquals(location1.hashCode(), location2.hashCode());
        assertEquals(location2.hashCode(), location3.hashCode());
        assertEquals(location1.hashCode(), location3.hashCode());
        // not equals
        assertNotEquals(location1.hashCode(), diffName.hashCode());
        assertNotEquals(location1.hashCode(), diffPosition.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Location[mapName=Main, position=(0, 0)]";
        assertEquals(expected, location.toString());
    }
}
