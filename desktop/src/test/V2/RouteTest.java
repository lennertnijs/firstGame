package V2;

import com.mygdx.game.V2.Location;
import com.mygdx.game.V2.Position;
import com.mygdx.game.V2.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    private Location pos1;
    private Location pos2;
    private Location pos3;
    private Route route1;
    private Route route2;
    private Route route3;
    private Route emptyRoute;

    @BeforeEach
    public void initialise(){
        pos1 = Location.create("Map1", Position.create(75, 75));
        pos2 = Location.create("Map1", Position.create(200, 200));
        pos3 = Location.create("Map1", Position.create(0, 0));
        route1 = Route.create(new ArrayList<>(Arrays.asList(pos1, pos2, pos3)));
        route2 = Route.create(new ArrayList<>(Arrays.asList(pos3, pos2)));
        route3 = Route.create(new ArrayList<>(Arrays.asList(pos1, pos2, pos3)));
        emptyRoute = Route.create(new ArrayList<>());
    }

    @Test
    public void testConstructor(){
        assertEquals(route1.getMapPositions(), new ArrayList<>(Arrays.asList(pos1, pos2, pos3)));
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> Route.create(null));
        assertThrows(NullPointerException.class, () -> Route.create(new ArrayList<>(Arrays.asList(pos1, null))));
    }

    @Test
    public void testGetLength(){
        assertEquals(route1.getLength(), 3);
        assertEquals(route2.getLength(), 2);
        assertEquals(emptyRoute.getLength(), 0);
    }

    @Test
    public void testIsEmpty(){
        assertTrue(emptyRoute.isEmpty());
        assertFalse(route1.isEmpty());
    }

    @Test
    public void testGetNext(){
        assertEquals(route1.getNext(), pos1);
        assertEquals(route2.getNext(), pos3);
        assertThrows(NoSuchElementException.class, () -> emptyRoute.getNext());
    }

    @Test
    public void testGetAndRemoveNext(){
        assertEquals(route1.getAndRemoveNext(), pos1);
        assertEquals(route1.getLength(), 2);
        assertEquals(route1.getAndRemoveNext(), pos2);
        assertEquals(route1.getLength(), 1);
        assertEquals(route1.getAndRemoveNext(), pos3);
        assertEquals(route1.getLength(), 0);
        assertThrows(NoSuchElementException.class, () -> route1.getAndRemoveNext());
    }

    @Test
    public void testEquals(){
        assertEquals(route1, route3);
        assertNotEquals(route1, route2);
        assertNotEquals(route1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(route1.hashCode(), route3.hashCode());
        assertNotEquals(route1.hashCode(), route2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Route[positions=[MapPosition[mapName=Map1, Position[x=75, y=75]], MapPosition[mapName=Map1, Position[x=200, y=200]], MapPosition[mapName=Map1, Position[x=0, y=0]]]]";
        assertEquals(route1.toString(), expectedString);
    }
}
