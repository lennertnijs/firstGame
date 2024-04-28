package V2;

import com.mygdx.game.V2.Navigation.Route;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    private Location l1;
    private Location l2;
    private Location l3;
    private Route emptyRoute;
    private Route route;

    @BeforeEach
    public void initialise(){
        l1 = new Location("map1", new Point(0, 0));
        l2 = new Location("map1", new Point(10, 0));
        l3 = new Location("map1", new Point(20, 0));

        emptyRoute = new Route();
        route = new Route(Arrays.asList(l1, l2, l3));
    }

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class, () -> new Route(null));
    }

    @Test
    public void testConstructorListContainsNull(){
        assertThrows(NullPointerException.class, () -> new Route(Arrays.asList(l1, null)));
    }

    @Test
    public void testGetLocations(){
        assertEquals(new ArrayList<>(), emptyRoute.getLocations());
        assertEquals(Arrays.asList(l1, l2, l3), route.getLocations());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(emptyRoute.isEmpty());
        assertFalse(route.isEmpty());
    }

    @Test
    public void testAdd(){
        emptyRoute.add(Arrays.asList(l2, l3));
        assertEquals(Arrays.asList(l2, l3), emptyRoute.getLocations());
        emptyRoute.add(Collections.singletonList(l1));
        assertEquals(Arrays.asList(l2, l3, l1), emptyRoute.getLocations());
    }

    @Test
    public void testAddNullList(){
        assertThrows(NullPointerException.class, () -> route.add(null));
    }

    @Test
    public void testAddListContainsNull(){
        assertThrows(NullPointerException.class, () -> emptyRoute.add(Arrays.asList(l1, null)));
    }

    @Test
    public void testPeek(){
        assertEquals(l1, route.peek());
        assertEquals(l1, route.peek());
    }

    @Test
    public void testPeekRouteIsEmpty(){
        assertThrows(NoSuchElementException.class, () -> emptyRoute.peek());
    }

    @Test
    public void testPoll(){
        assertEquals(Arrays.asList(l1, l2, l3), route.getLocations());
        assertEquals(l1, route.poll());
        assertEquals(Arrays.asList(l2, l3), route.getLocations());
        assertEquals(l2, route.poll());
        assertEquals(Collections.singletonList(l3), route.getLocations());
        assertEquals(l3, route.poll());
        assertTrue(route.isEmpty());
    }

    @Test
    public void testPollRouteIsEmpty(){
        assertThrows(NoSuchElementException.class, () -> emptyRoute.poll());
    }

    @Test
    public void testWipe(){
        route.wipe();
        assertTrue(route.isEmpty());
        route.wipe();
        assertTrue(route.isEmpty());
    }

    @Test
    public void testEquals(){
        Route route1 = new Route(Arrays.asList(l1, l2, l3));
        Route route2 = new Route(Arrays.asList(l1, l2, l3));
        Route route3 = new Route(Arrays.asList(l1, l2, l3));
        Route diffRoute = new Route(Arrays.asList(l1, l2));
        // reflexive
        assertEquals(route1, route1);
        // symmetrical
        assertEquals(route1, route2);
        assertEquals(route2, route1);
        // transitive
        assertEquals(route1, route2);
        assertEquals(route2, route3);
        assertEquals(route1, route3);
        // not equals
        assertNotEquals(route1, diffRoute);
        assertNotEquals(route1, new Object());
        assertNotEquals(route1, null);
    }

    @Test
    public void testHashCode(){
        Route route1 = new Route(Arrays.asList(l1, l2, l3));
        Route route2 = new Route(Arrays.asList(l1, l2, l3));
        Route route3 = new Route(Arrays.asList(l1, l2, l3));
        Route diffRoute = new Route(Arrays.asList(l1, l2));
        // reflexive
        assertEquals(route1.hashCode(), route1.hashCode());
        // symmetrical
        assertEquals(route1.hashCode(), route2.hashCode());
        assertEquals(route2.hashCode(), route1.hashCode());
        // transitive
        assertEquals(route1.hashCode(), route2.hashCode());
        assertEquals(route2.hashCode(), route3.hashCode());
        assertEquals(route1.hashCode(), route3.hashCode());
        // not equals
        assertNotEquals(route1.hashCode(), diffRoute.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Route{locations=[" +
                "Location[mapName=map1, position=Point[x=0, y=0]], " +
                "Location[mapName=map1, position=Point[x=10, y=0]], " +
                "Location[mapName=map1, position=Point[x=20, y=0]]" +
                "]}";
        assertEquals(expected, route.toString());
    }


}
