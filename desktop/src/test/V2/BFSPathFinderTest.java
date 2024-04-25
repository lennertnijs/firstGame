package V2;

import com.mygdx.game.V2.Navigation.BFSPathFinder;
import com.mygdx.game.V2.Navigation.Graph;
import com.mygdx.game.V2.Navigation.IGraph;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BFSPathFinderTest {

    private Location mp1;
    private Location mp2;
    private Location mp3;
    private Location mp4;
    private Location mp5;
    private Location mp6;
    private Location mp7;
    private final IGraph<Location> graph = new Graph<>();
    private final BFSPathFinder<Location> bfsPathFinder = new BFSPathFinder<>();

    @BeforeEach
    public void initialise(){
        mp1 = new Location("map1", new Point(0,0));
        mp2 = new Location("map1", new Point(50,0));
        mp3 = new Location("map1", new Point(0,50));
        mp4 = new Location("map1", new Point(50,50));
        mp5 = new Location("map1", new Point(50,100));
        mp6 = new Location("map2", new Point(0,0));
        mp7 = new Location("map2", new Point(50,0));

        graph.addVertices(new ArrayList<>(Arrays.asList(mp1, mp2, mp3, mp4, mp5, mp6, mp7)));
        graph.addEdges(mp1, new ArrayList<>(Arrays.asList(mp2, mp3)));
        graph.addEdges(mp2, new ArrayList<>(Arrays.asList(mp1, mp4)));
        graph.addEdges(mp3, new ArrayList<>(Arrays.asList(mp1, mp4)));
        graph.addEdges(mp4, new ArrayList<>(Arrays.asList(mp2, mp3, mp5)));
        graph.addEdges(mp5, new ArrayList<>(Arrays.asList(mp4, mp6)));
        graph.addEdges(mp6, new ArrayList<>(Arrays.asList(mp5, mp7)));
    }

    @Test
    public void testFindPath1(){
        assertEquals(bfsPathFinder.findPath(mp1, mp2, graph), new ArrayList<>(Arrays.asList(mp1, mp2)));
    }

    @Test
    public void testFindPathWithNull(){
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(null, mp1, graph));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, null, graph));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, mp2, null));
    }

    @Test
    public void testFindPathNoSuchElement(){
        Location noSuch = new Location("map3", new Point(0,0));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(noSuch, mp1, graph));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(mp1, noSuch, graph));
    }
}
