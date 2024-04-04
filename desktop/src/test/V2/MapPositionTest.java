package V2;

import com.mygdx.game.V2.MapPosition;
import com.mygdx.game.V2.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapPositionTest {

    private MapPosition mapPosition1;
    private MapPosition mapPosition2;
    private MapPosition mapPosition3;

    @BeforeEach
    public void initialise(){
        mapPosition1 = MapPosition.create("map1", Position.create(0,0));
        mapPosition2 = MapPosition.create("map2", Position.create(0,0));
        mapPosition3 = MapPosition.create("map1", Position.create(0,0));
    }

    @Test
    public void testConstructor(){
        assertEquals(mapPosition1.getMapName(), "map1");
        assertEquals(mapPosition1.getPosition(), Position.create(0,0));
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> MapPosition.create(null, Position.create(0,0)));
        assertThrows(NullPointerException.class, () -> MapPosition.create("map1", null));
    }

    @Test
    public void testEquals(){
        assertEquals(mapPosition1, mapPosition3);
        assertNotEquals(mapPosition1, mapPosition2);
        assertNotEquals(mapPosition1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(mapPosition1.hashCode(), mapPosition3.hashCode());
        assertNotEquals(mapPosition1.hashCode(), mapPosition2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "MapPosition[mapName=map1, Position[x=0, y=0]]";
        assertEquals(mapPosition1.toString(), expectedString);
    }
}
