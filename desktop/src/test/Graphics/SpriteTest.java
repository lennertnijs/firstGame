package Graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Graphics.Sprite;
import com.mygdx.game.V2.Util.Dimensions;
import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {

    private TextureRegion texture;
    private Point position;
    private Dimensions dimensions;
    private Sprite sprite;

    @BeforeEach
    public void initialise(){
        texture = Mockito.mock(TextureRegion.class);
        position = new Point(5, 15);
        dimensions = new Dimensions(10, 20);

        sprite = new Sprite(texture, position, dimensions);
    }

    @Test
    public void testConstructorWithNullTexture(){
        assertThrows(NullPointerException.class, () -> new Sprite(null, position, dimensions));
    }

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class, () -> new Sprite(texture, null, dimensions));
    }

    @Test
    public void testConstructorWithNullDimensions(){
        assertThrows(NullPointerException.class, () -> new Sprite(texture, position, null));
    }

    @Test
    public void testGetTexture(){
        assertEquals(texture, sprite.getTexture());
    }

    @Test
    public void testGetPosition(){
        assertEquals(position, sprite.getPosition());
    }

    @Test
    public void testGetDimensions(){
        assertEquals(dimensions, sprite.getDimensions());
    }

    @Test
    public void testSetTexture(){
        TextureRegion texture2 = Mockito.mock(TextureRegion.class);
        sprite.setTexture(texture2);
        assertEquals(texture2, sprite.getTexture());
    }

    @Test
    public void testSetTextureToNull(){
        assertThrows(NullPointerException.class, () -> sprite.setTexture(null));
    }

    @Test
    public void testSetPosition(){
        Point position2 = new Point(0, 15);
        sprite.setPosition(position2);
        assertEquals(position2, sprite.getPosition());
    }

    @Test
    public void testSetPositionToNull(){
        assertThrows(NullPointerException.class, () -> sprite.setPosition(null));
    }

    @Test
    public void testSetDimensions(){
        Dimensions dimensions2 = new Dimensions(7, 14);
        sprite.setDimensions(dimensions2);
        assertEquals(dimensions2, sprite.getDimensions());
    }

    @Test
    public void testSetDimensionsToNull(){
        assertThrows(NullPointerException.class, () -> sprite.setDimensions(null));
    }

    @Test
    public void testEquals(){
        Sprite sprite1 = new Sprite(texture, position, dimensions);
        Sprite sprite2 = new Sprite(texture, position, dimensions);
        Sprite sprite3 = new Sprite(texture, position, dimensions);
        Sprite diffSprite = new Sprite(texture, new Point(1, 1), dimensions);
        // reflexive
        assertEquals(sprite1, sprite1);
        // symmetrical
        assertEquals(sprite1, sprite2);
        assertEquals(sprite2, sprite1);
        // transitive
        assertEquals(sprite1, sprite2);
        assertEquals(sprite2, sprite3);
        assertEquals(sprite1, sprite3);
        // not equals
        assertNotEquals(sprite1, diffSprite);
        assertNotEquals(sprite1, new Object());
        assertNotEquals(sprite1, null);
    }

    @Test
    public void testHashCode(){
        Sprite sprite1 = new Sprite(texture, position, dimensions);
        Sprite sprite2 = new Sprite(texture, position, dimensions);
        Sprite sprite3 = new Sprite(texture, position, dimensions);
        Sprite diffSprite = new Sprite(texture, new Point(1, 1), dimensions);
        // reflexive
        assertEquals(sprite1.hashCode(), sprite1.hashCode());
        // symmetrical
        assertEquals(sprite1.hashCode(), sprite2.hashCode());
        assertEquals(sprite2.hashCode(), sprite1.hashCode());
        // transitive
        assertEquals(sprite1.hashCode(), sprite2.hashCode());
        assertEquals(sprite2.hashCode(), sprite3.hashCode());
        assertEquals(sprite1.hashCode(), sprite3.hashCode());
        // not equals
        assertNotEquals(sprite1.hashCode(), diffSprite.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(sprite.toString());
    }
}
