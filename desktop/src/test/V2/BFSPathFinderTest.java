package V2;

import com.mygdx.game.V2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BFSPathFinderTest {

    private MapPosition mp1;
    private MapPosition mp2;
    private MapPosition mp3;
    private MapPosition mp4;
    private MapPosition mp5;
    private MapPosition mp6;
    private MapPosition mp7;
    private final Graph<MapPosition> graph = new Graph<>();
    private final BFSPathFinder<MapPosition> bfsPathFinder = new BFSPathFinder<>();

    @BeforeEach
    public void initialise(){
        mp1 = MapPosition.create("map1", Position.create(0,0));
        mp2 = MapPosition.create("map1", Position.create(50,0));
        mp3 = MapPosition.create("map1", Position.create(0,50));
        mp4 = MapPosition.create("map1", Position.create(50,50));
        mp5 = MapPosition.create("map1", Position.create(50,100));
        mp6 = MapPosition.create("map2", Position.create(0,0));
        mp7 = MapPosition.create("map2", Position.create(50,0));

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
    public void testFindPath2(){
        assertEquals(bfsPathFinder.findPath(mp1, mp7, graph), new ArrayList<>(Arrays.asList(mp1, mp2, mp4, mp5, mp6, mp7)));
    }

    @Test
    public void testFindPathWithNull(){
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(null, mp1, graph));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, null, graph));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, mp2, null));
    }

    @Test
    public void testFindPathNoSuchElement(){
        MapPosition noSuch = MapPosition.create("map3", Position.create(0,0));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(noSuch, mp1, graph));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(mp1, noSuch, graph));
    }
}
