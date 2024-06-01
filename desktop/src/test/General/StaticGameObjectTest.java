package General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.StaticGameObject;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class StaticGameObjectTest {

    private final TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
    private final Point position = new Point(0, 0);
    private final Dimensions dimensions = new Dimensions(15, 10);
    private final String map = "map";
    private StaticGameObject gameObject;

    @BeforeEach
    public void initialise(){
        gameObject = new StaticGameObject(position, dimensions, map, textureRegion);
    }

    @Test
    public void testConstructorWithNullTextureRegion(){
        assertThrows(NullPointerException.class,
                () -> new StaticGameObject(position, dimensions, map, null));
    }

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class,
                () -> new StaticGameObject(null, dimensions, map, textureRegion));
    }

    @Test
    public void testConstructorWithNullDimensions(){
        assertThrows(NullPointerException.class,
                () -> new StaticGameObject(position, null, map, textureRegion));
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class,
                () -> new StaticGameObject(position, dimensions, null, textureRegion));
    }

    @Test
    public void testGetTexture(){
        assertEquals(textureRegion, gameObject.getTexture());
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, gameObject.getPosition());
    }

    @Test
    public void testSetPosition(){
        Point newPosition = new Point(100, 1);
        gameObject.setPosition(newPosition);
        assertEquals(newPosition, gameObject.getPosition());
    }

    @Test
    public void testSetPositionToNull(){
        assertThrows(NullPointerException.class,
                () -> gameObject.setPosition(null));
    }

    @Test
    public void getDimensions(){
        assertEquals(dimensions, gameObject.getDimensions());
    }

    @Test
    public void setDimensions(){
        Dimensions newDimensions = new Dimensions(16, 17);
        gameObject.setDimensions(newDimensions);
        assertEquals(newDimensions, gameObject.getDimensions());
    }

    @Test
    public void setDimensionsToNull(){
        assertThrows(NullPointerException.class,
                () -> gameObject.setDimensions(null));
    }

    @Test
    public void testGetMap(){
        assertEquals(map, gameObject.getMap());
    }

    @Test
    public void testSetMap(){
        String newMap = "new map";
        gameObject.setMap(newMap);
        assertEquals(newMap, gameObject.getMap());
    }

    @Test
    public void testSetMapToNull(){
        assertThrows(NullPointerException.class,
                () -> gameObject.setMap(null));
    }

    @Test
    public void testGetHitBox(){
        assertEquals(new Rectangle(position, dimensions), gameObject.getHitBox());
    }

    @Test
    public void testEquals(){
        GameObject object1 = new StaticGameObject(position, dimensions, map, textureRegion);
        GameObject object2 = new StaticGameObject(position, dimensions, map, textureRegion);
        GameObject object3 = new StaticGameObject(position, dimensions, map, textureRegion);
        // reflexive
        assertEquals(object1, object1);
        // symmetrical
        assertEquals(object1, object2);
        assertEquals(object2, object1);
        // transitive
        assertEquals(object1, object2);
        assertEquals(object2, object3);
        assertEquals(object1, object3);

        // not equals
        GameObject diffTextureRegion = new StaticGameObject(position, dimensions, map, Mockito.mock(TextureRegion.class));
        GameObject diffPosition = new StaticGameObject(new Point(1, 19), dimensions, map, textureRegion);
        GameObject diffDimensions = new StaticGameObject(position, new Dimensions(16, 17), map, textureRegion);
        GameObject diffMap = new StaticGameObject(position, dimensions, "new map", textureRegion);
        assertNotEquals(object1, diffTextureRegion);
        assertNotEquals(object1, diffPosition);
        assertNotEquals(object1, diffDimensions);
        assertNotEquals(object1, diffMap);
        assertNotEquals(object1, new Object());
        assertNotEquals(object1, null);
    }

    @Test
    public void testHashCode(){
        GameObject object1 = new StaticGameObject(position, dimensions, map, textureRegion);
        GameObject object2 = new StaticGameObject(position, dimensions, map, textureRegion);
        GameObject object3 = new StaticGameObject(position, dimensions, map, textureRegion);
        // reflexive
        assertEquals(object1.hashCode(), object1.hashCode());
        // symmetrical
        assertEquals(object1.hashCode(), object2.hashCode());
        assertEquals(object2.hashCode(), object1.hashCode());
        // transitive
        assertEquals(object1.hashCode(), object2.hashCode());
        assertEquals(object2.hashCode(), object3.hashCode());
        assertEquals(object1.hashCode(), object3.hashCode());

        // not equals
        GameObject diffTextureRegion = new StaticGameObject(position, dimensions, map, Mockito.mock(TextureRegion.class));
        GameObject diffPosition = new StaticGameObject(new Point(1, 19), dimensions, map, textureRegion);
        GameObject diffDimensions = new StaticGameObject(position, new Dimensions(16, 17), map, textureRegion);
        GameObject diffMap = new StaticGameObject(position, dimensions, "new map", textureRegion);
        assertNotEquals(object1.hashCode(), diffTextureRegion.hashCode());
        assertNotEquals(object1.hashCode(), diffPosition.hashCode());
        assertNotEquals(object1.hashCode(), diffDimensions.hashCode());
        assertNotEquals(object1.hashCode(), diffMap.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "StaticGameObject[texture=" + textureRegion.toString() +", " +
                "position=Point[x=0, y=0], " +
                "dimensions=Dimensions[width=15, height=10], " +
                "map=map]";
        assertEquals(expected, gameObject.toString());
    }
}
