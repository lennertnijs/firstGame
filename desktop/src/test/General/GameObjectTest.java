package General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.HitBox.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {

    private final TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
    private final Point position = new Point(0, 0);
    private final Dimensions dimensions = new Dimensions(15, 10);
    private final String map = "map";
    private GameObject gameObject;

    @BeforeEach
    public void initialise(){
        gameObject = new GameObject(textureRegion, position, dimensions, map);
    }

    @Test
    public void testConstructorWithNullTextureRegion(){
        assertThrows(NullPointerException.class,
                () -> new GameObject(null, position, dimensions, map));
    }

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class,
                () -> new GameObject(textureRegion, null, dimensions, map));
    }

    @Test
    public void testConstructorWithNullDimensions(){
        assertThrows(NullPointerException.class,
                () -> new GameObject(textureRegion, position, null, map));
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class,
                () -> new GameObject(textureRegion, position, dimensions, null));
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
        GameObject object1 = new GameObject(textureRegion, position, dimensions, map);
        GameObject object2 = new GameObject(textureRegion, position, dimensions, map);
        GameObject object3 = new GameObject(textureRegion, position, dimensions, map);
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
        GameObject diffTextureRegion = new GameObject(Mockito.mock(TextureRegion.class), position, dimensions, map);
        GameObject diffPosition = new GameObject(textureRegion, new Point(1, 19), dimensions, map);
        GameObject diffDimensions = new GameObject(textureRegion, position, new Dimensions(16, 17), map);
        GameObject diffMap = new GameObject(textureRegion, position, dimensions, "new map");
        assertNotEquals(object1, diffTextureRegion);
        assertNotEquals(object1, diffPosition);
        assertNotEquals(object1, diffDimensions);
        assertNotEquals(object1, diffMap);
        assertNotEquals(object1, new Object());
        assertNotEquals(object1, null);
    }

    @Test
    public void testHashCode(){
        GameObject object1 = new GameObject(textureRegion, position, dimensions, map);
        GameObject object2 = new GameObject(textureRegion, position, dimensions, map);
        GameObject object3 = new GameObject(textureRegion, position, dimensions, map);
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
        GameObject diffTextureRegion = new GameObject(Mockito.mock(TextureRegion.class), position, dimensions, map);
        GameObject diffPosition = new GameObject(textureRegion, new Point(1, 19), dimensions, map);
        GameObject diffDimensions = new GameObject(textureRegion, position, new Dimensions(16, 17), map);
        GameObject diffMap = new GameObject(textureRegion, position, dimensions, "new map");
        assertNotEquals(object1.hashCode(), diffTextureRegion.hashCode());
        assertNotEquals(object1.hashCode(), diffPosition.hashCode());
        assertNotEquals(object1.hashCode(), diffDimensions.hashCode());
        assertNotEquals(object1.hashCode(), diffMap.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "GameObject[textureRegion=" + textureRegion.toString() +", " +
                "position=(0, 0), " +
                "dimensions=Dimensions[width=15, height=10], " +
                "map=map]";
        assertEquals(expected, gameObject.toString());
    }
}
