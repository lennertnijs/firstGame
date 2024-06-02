package Navigation;

import com.mygdx.game.Navigation.*;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationDataTest {

    private Location l1;
    private Location l2;
    private Location l3;
    private IGraph<Location> graph;
    private PathFinderStrategy<Location> pathFinder;
    private NavigationData navigationData;

    @BeforeEach
    public void initialise() {
        l1 = new Location("map1", new Point(0, 0));
        l2 = new Location("map1", new Point(10, 0));
        l3 = new Location("map1", new Point(10, 10));

        graph = new Graph<>();
        graph.addVertices(Arrays.asList(l1, l2, l3));
        graph.connect(l1, l2);
        graph.connect(l2, l3);

        pathFinder = new DFSPathFinder<>(graph);

        navigationData = new NavigationData(pathFinder);
    }

    @Test
    public void testConstructorWithNullStrategy(){
        assertThrows(NullPointerException.class, () -> new NavigationData(null));
        assertThrows(NullPointerException.class, () -> new NavigationData(null, Arrays.asList(l1, l2)));
    }

    @Test
    public void testConstructorWithNullRoute(){
        assertThrows(NullPointerException.class, () -> new NavigationData(pathFinder, null));
    }

    @Test
    public void testConstructorWithNullInRoute(){
        assertThrows(NullPointerException.class, () -> new NavigationData(pathFinder, Arrays.asList(l1, null)));
    }

    @Test
    public void testConstructorWithRoute(){
        NavigationData data = new NavigationData(pathFinder, Arrays.asList(l1, l2));
    }


    @Test
    public void testCalculateAndStoreRoute(){
        navigationData.calculateAndStoreRoute(l1, l3);

        Location location1 = new Location("map1", new Point(1, 0));
        assertEquals(location1, navigationData.calculateNextLocation(l1, 1));

        Location location2 = new Location("map1", new Point(4, 0));
        assertEquals(location2, navigationData.calculateNextLocation(location1, 3));

        Location location3 = new Location("map1", new Point(10, 7));
        assertEquals(location3, navigationData.calculateNextLocation(location2, 13));

        Location location4 = new Location("map1", new Point(10, 8));
        assertEquals(location4, navigationData.calculateNextLocation(location3, 1));

        Location location5 = new Location("map1", new Point(10, 10));
        assertEquals(location5, navigationData.calculateNextLocation(location4, 5));
    }

    @Test
    public void testCalculateNextLocationWithNull(){
        assertThrows(NullPointerException.class, () -> navigationData.calculateNextLocation(null, 10));
    }

    @Test
    public void testCalculateNextLocationWithNegativeMovement(){
        assertThrows(IllegalArgumentException.class, () -> navigationData.calculateNextLocation(l1, -1));
    }

    @Test
    public void testToString(){
        String expected = "NavigationData[currentRoute=Route{locations=[]}, " +
                "strategy=class com.mygdx.game.Navigation.DFSPathFinder]";
        assertEquals(expected, navigationData.toString());
    }

}
