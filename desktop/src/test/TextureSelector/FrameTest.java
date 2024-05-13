package TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.TextureSelector.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    private TextureRegion textureRegion;
    private Vector translation;
    private Dimensions dimensions;
    private Frame frame;

    @BeforeEach
    public void initialise(){
        textureRegion = Mockito.mock(TextureRegion.class);
        translation = new Vector(5, 5);
        dimensions = new Dimensions(10, 10);

        frame = new Frame(textureRegion, translation, dimensions);
    }

    @Test
    public void testConstructorWithNullTextureRegion(){
        assertThrows(NullPointerException.class,
                () -> new Frame(null, translation, dimensions));
    }

    @Test
    public void testConstructorWithNullTranslationVector(){
        assertThrows(NullPointerException.class,
                () -> new Frame(textureRegion, null, dimensions));
    }

    @Test
    public void testConstructorWithNullDimensions(){
        assertThrows(NullPointerException.class,
                () -> new Frame(textureRegion, translation, null));
    }

    @Test
    public void testTextureRegion(){
        assertEquals(textureRegion, frame.textureRegion());
    }

    @Test
    public void testTranslation(){
        assertEquals(translation, frame.translation());
    }

    @Test
    public void testDimensions(){
        assertEquals(dimensions, frame.dimensions());
    }

    @Test
    public void testEquals(){
        Frame frame1 = new Frame(textureRegion, translation, dimensions);
        Frame frame2 = new Frame(textureRegion, translation, dimensions);
        Frame frame3 = new Frame(textureRegion, translation, dimensions);
        Frame diffTextureRegion = new Frame(Mockito.mock(TextureRegion.class), translation, dimensions);
        Frame diffTranslation = new Frame(textureRegion, new Vector(25, 25), dimensions);
        Frame diffDimensions = new Frame(textureRegion, translation, new Dimensions(50, 50));
        // reflexive
        assertEquals(frame1, frame1);
        // symmetrical
        assertEquals(frame1, frame2);
        assertEquals(frame2, frame1);
        // transitive
        assertEquals(frame1, frame2);
        assertEquals(frame2, frame3);
        assertEquals(frame1, frame3);
        // not equals
        assertNotEquals(frame1, diffTextureRegion);
        assertNotEquals(frame1, diffTranslation);
        assertNotEquals(frame1, diffDimensions);
        assertNotEquals(frame1, new Object());
        assertNotEquals(frame1, null);
    }

    @Test
    public void testHashCode(){
        Frame frame1 = new Frame(textureRegion, translation, dimensions);
        Frame frame2 = new Frame(textureRegion, translation, dimensions);
        Frame frame3 = new Frame(textureRegion, translation, dimensions);
        Frame diffTextureRegion = new Frame(Mockito.mock(TextureRegion.class), translation, dimensions);
        Frame diffTranslation = new Frame(textureRegion, new Vector(25, 25), dimensions);
        Frame diffDimensions = new Frame(textureRegion, translation, new Dimensions(50, 50));
        // reflexive
        assertEquals(frame1.hashCode(), frame1.hashCode());
        // symmetrical
        assertEquals(frame1.hashCode(), frame2.hashCode());
        assertEquals(frame2.hashCode(), frame1.hashCode());
        // transitive
        assertEquals(frame1.hashCode(), frame2.hashCode());
        assertEquals(frame2.hashCode(), frame3.hashCode());
        assertEquals(frame1.hashCode(), frame3.hashCode());
        // not equals
        assertNotEquals(frame1.hashCode(), diffTextureRegion.hashCode());
        assertNotEquals(frame1.hashCode(), diffTranslation.hashCode());
        assertNotEquals(frame1.hashCode(), diffDimensions.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Frame[" +
                "textureRegion=" + textureRegion.toString() + ", " +
                "translation=Vector[x=5, y=5], " +
                "dimensions=Dimensions[width=10, height=10]" +
                "]";
        assertEquals(expected, frame.toString());
    }
}
