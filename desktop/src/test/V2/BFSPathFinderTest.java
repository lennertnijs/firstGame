package V2;

import com.mygdx.game.V2.BFSPathFinder;
import com.mygdx.game.V2.MapPosition;
import com.mygdx.game.V2.Position;
import com.mygdx.game.V2.Route;
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
    private Map<MapPosition, List<MapPosition>> mapping;
    private final BFSPathFinder bfsPathFinder = new BFSPathFinder();

    @BeforeEach
    public void initialise(){
        mp1 = MapPosition.create("map1", Position.create(0,0));
        mp2 = MapPosition.create("map1", Position.create(50,0));
        mp3 = MapPosition.create("map1", Position.create(0,50));
        mp4 = MapPosition.create("map1", Position.create(50,50));
        mp5 = MapPosition.create("map1", Position.create(50,100));
        mp6 = MapPosition.create("map2", Position.create(0,0));
        mp7 = MapPosition.create("map2", Position.create(50,0));

        mapping = new HashMap<MapPosition, List<MapPosition>>(){{
            put(mp1, new ArrayList<>(Arrays.asList(mp2, mp3)));
            put(mp2, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp3, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp4, new ArrayList<>(Arrays.asList(mp2, mp3, mp5)));
            put(mp5, new ArrayList<>(Arrays.asList(mp4, mp6)));
            put(mp6, new ArrayList<>(Arrays.asList(mp5, mp7)));
        }};
    }

    @Test
    public void testFindPath1(){
        assertEquals(bfsPathFinder.findPath(mp1, mp2, mapping), Route.create(new ArrayList<>(Arrays.asList(mp1, mp2))));
    }

    @Test
    public void testFindPath2(){
        assertEquals(bfsPathFinder.findPath(mp1, mp7, mapping),
                Route.create(new ArrayList<>(Arrays.asList(mp1, mp2, mp4, mp5, mp6, mp7))));
    }

    @Test
    public void testFindPathWithNull(){
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(null, mp1, mapping));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, null, mapping));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, mp2, null));
        mapping.put(null, new ArrayList<>(Arrays.asList(mp1, mp2)));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, mp2, mapping));
        mapping.remove(null);
        mapping.put(mp1, new ArrayList<>(Arrays.asList(mp1, null)));
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(mp1, mp2, mapping));
    }

    @Test
    public void testFindPathNoSuchElement(){
        MapPosition noSuch = MapPosition.create("map3", Position.create(0,0));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(noSuch, mp1, mapping));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(mp1, noSuch, mapping));
    }
}
