package General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.General.AnimatedGameObject;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Rectangle;
import com.mygdx.game.Util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AnimatedGameObjectTest {

    private final TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
    private final Point position = new Point(0, 0);
    private final Dimensions dimensions = new Dimensions(15, 10);
    private final String map = "map";
    private final Vector translation = new Vector(5, 5);
    private AnimatedGameObject object;

    @BeforeEach
    public void initialise(){
        object = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
    }

    @Test
    public void testConstructorWithNullTranslationVector(){
        assertThrows(NullPointerException.class,
                () -> new AnimatedGameObject(textureRegion, position, dimensions, map, null));
    }

    @Test
    public void testGetPosition(){
        Point scaledPosition = new Point(5, 5);
        assertEquals(scaledPosition, object.getPosition());
    }

    @Test
    public void testGetHitBox(){
        Rectangle r = new Rectangle(new Point(5, 5), dimensions);
        assertEquals(r, object.getHitBox());
    }

    @Test
    public void testSetTranslation(){
        Vector biggerTranslation = new Vector(20, 10);
        object.setTranslation(biggerTranslation);
        Point expectedPosition = new Point(20, 10);
        assertEquals(expectedPosition, object.getPosition());
    }

    @Test
    public void testSetTranslationToNull(){
        assertThrows(NullPointerException.class,
                () -> object.setTranslation(null));
    }

    @Test
    public void testEquals(){
        AnimatedGameObject object1 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
        AnimatedGameObject object2 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
        AnimatedGameObject object3 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
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
        Vector diff = new Vector(14, 10);
        AnimatedGameObject diffTranslation = new AnimatedGameObject(textureRegion, position, dimensions, map, diff);
        assertNotEquals(object1, diffTranslation);
        assertNotEquals(object1, new Object());
        assertNotEquals(object1, null);

        AnimatedGameObject diffSuperClassField =
                new AnimatedGameObject(textureRegion, position, dimensions, "m", translation);
        assertNotEquals(object1, diffSuperClassField);
    }

    @Test
    public void testHashCode(){
        AnimatedGameObject object1 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
        AnimatedGameObject object2 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
        AnimatedGameObject object3 = new AnimatedGameObject(textureRegion, position, dimensions, map, translation);
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
        Vector diff = new Vector(14, 10);
        AnimatedGameObject diffTranslation = new AnimatedGameObject(textureRegion, position, dimensions, map, diff);
        assertNotEquals(object1.hashCode(), diffTranslation.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "AnimatedGameObject[" +
                "textureRegion=" + textureRegion.toString() + ", "+
                "position=Point[x=0, y=0], " +
                "dimensions=Dimensions[width=15, height=10], " +
                "map=map, " +
                "translation=Vector[x=5, y=5]]";
        assertEquals(expected, object.toString());
    }
}
