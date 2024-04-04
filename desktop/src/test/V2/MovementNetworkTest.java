package V2;

import com.mygdx.game.V2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MovementNetworkTest {

    private MapPosition mp1;
    private MapPosition mp2;
    private MapPosition mp3;
    private MapPosition mp4;
    private MapPosition mp5;
    private MapPosition mp6;
    private MapPosition mp7;
    private Map<MapPosition, List<MapPosition>> mapping1;
    private final BFSPathFinder bfsPathFinder = new BFSPathFinder();
    private MovementNetwork network1;
    private MovementNetwork network2;
    private MovementNetwork network3;

    @BeforeEach
    public void initialise(){
        mp1 = MapPosition.create("map1", Position.create(0,0));
        mp2 = MapPosition.create("map1", Position.create(50,0));
        mp3 = MapPosition.create("map1", Position.create(0,50));
        mp4 = MapPosition.create("map1", Position.create(50,50));
        mp5 = MapPosition.create("map1", Position.create(50,100));
        mp6 = MapPosition.create("map2", Position.create(0,0));
        mp7 = MapPosition.create("map2", Position.create(50,0));

        mapping1 = new HashMap<MapPosition, List<MapPosition>>(){{
            put(mp1, new ArrayList<>(Arrays.asList(mp2, mp3)));
            put(mp2, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp3, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp4, new ArrayList<>(Arrays.asList(mp2, mp3, mp5)));
            put(mp5, new ArrayList<>(Arrays.asList(mp4, mp6)));
            put(mp6, new ArrayList<>(Arrays.asList(mp5, mp7)));
        }};

        Map<MapPosition, List<MapPosition>> mapping2 = new HashMap<MapPosition, List<MapPosition>>() {{
            put(mp1, new ArrayList<>(Arrays.asList(mp2, mp3)));
            put(mp2, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp3, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp4, new ArrayList<>(Arrays.asList(mp2, mp3)));
        }};

        Map<MapPosition, List<MapPosition>> mapping3 = new HashMap<MapPosition, List<MapPosition>>() {{
            put(mp1, new ArrayList<>(Arrays.asList(mp2, mp3)));
            put(mp2, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp3, new ArrayList<>(Arrays.asList(mp1, mp4)));
            put(mp4, new ArrayList<>(Arrays.asList(mp2, mp3, mp5)));
            put(mp5, new ArrayList<>(Arrays.asList(mp4, mp6)));
            put(mp6, new ArrayList<>(Arrays.asList(mp5, mp7)));
        }};

        network1 = MovementNetwork.create(mapping1);
        network2 = MovementNetwork.create(mapping2);
        network3 = MovementNetwork.create(mapping3);
    }

    @Test
    public void testConstructor(){
        assertEquals(network1.getMapping(), mapping1);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> MovementNetwork.create(null));
        mapping1.put(null, new ArrayList<>(Arrays.asList(mp1, mp2)));
        assertThrows(NullPointerException.class, () -> MovementNetwork.create(mapping1));
        mapping1.remove(null);
        mapping1.put(mp1, null);
        assertThrows(NullPointerException.class, () -> MovementNetwork.create(mapping1));
    }

    @Test
    public void testFindPath(){
        assertEquals(network1.findPath(mp1, mp7, bfsPathFinder),
                Route.create(new ArrayList<>(Arrays.asList(mp1, mp2, mp4, mp5, mp6, mp7))));
        assertEquals(network2.findPath(mp1, mp4, bfsPathFinder),
                Route.create(new ArrayList<>(Arrays.asList(mp1, mp2, mp4))));
        assertEquals(network3.findPath(mp1, mp7, bfsPathFinder),
                Route.create(new ArrayList<>(Arrays.asList(mp1, mp2, mp4, mp5, mp6, mp7))));
    }

    @Test
    public void testEquals(){
        assertEquals(network1, network3);
        assertNotEquals(network1, network2);
        assertNotEquals(network1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(network1.hashCode(), network3.hashCode());
        assertNotEquals(network1.hashCode(), network2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "MovementNetwork[Mappings={MapPosition[mapName=map1, Position[x=0, y=50]]=[MapPosition[mapName=map1, Position[x=0, y=0]], MapPosition[mapName=map1, Position[x=50, y=50]]], MapPosition[mapName=map1, Position[x=50, y=100]]=[MapPosition[mapName=map1, Position[x=50, y=50]], MapPosition[mapName=map2, Position[x=0, y=0]]], MapPosition[mapName=map1, Position[x=50, y=0]]=[MapPosition[mapName=map1, Position[x=0, y=0]], MapPosition[mapName=map1, Position[x=50, y=50]]], MapPosition[mapName=map1, Position[x=0, y=0]]=[MapPosition[mapName=map1, Position[x=50, y=0]], MapPosition[mapName=map1, Position[x=0, y=50]]], MapPosition[mapName=map1, Position[x=50, y=50]]=[MapPosition[mapName=map1, Position[x=50, y=0]], MapPosition[mapName=map1, Position[x=0, y=50]], MapPosition[mapName=map1, Position[x=50, y=100]]], MapPosition[mapName=map2, Position[x=0, y=0]]=[MapPosition[mapName=map1, Position[x=50, y=100]], MapPosition[mapName=map2, Position[x=50, y=0]]]}]";
        assertEquals(network1.toString(), expectedString);
    }
}
