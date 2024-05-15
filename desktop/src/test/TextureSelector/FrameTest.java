package TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.TextureSelector.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    private final TextureRegion textureRegion = Mockito.mock(TextureRegion.class);
    private final Vector translation = new Vector(5, 5);
    private final Dimensions dimensions = new Dimensions(10, 10);
    private final Frame frame = Frame
            .builder()
            .textureRegion(textureRegion)
            .translation(translation)
            .dimensions(dimensions)
            .build();

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
    public void testBuilderWithDefaultTranslationVector(){
        Frame defaultTranslationFrame = Frame.builder().textureRegion(textureRegion).dimensions(dimensions).build();
        assertEquals(new Vector(0, 0), defaultTranslationFrame.translation());
    }

    @Test
    public void testBuilderWithDefaultDimensions(){
        Frame defaultDimensionsFrame = Frame.builder().textureRegion(textureRegion).translation(translation).build();
        Dimensions expectedDimensions = new Dimensions(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        assertEquals(expectedDimensions, defaultDimensionsFrame.dimensions());
    }

    @Test
    public void testBuilderWithDefaultTranslationVectorAndDimensions(){
        Frame defaultFrame = Frame.builder().textureRegion(textureRegion).build();
        assertEquals(new Vector(0, 0), defaultFrame.translation());
        Dimensions expectedDimensions = new Dimensions(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        assertEquals(expectedDimensions, defaultFrame.dimensions());

    }

    @Test
    public void testEquals(){
        Frame frame1 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
        Frame frame2 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
        Frame frame3 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
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
        Frame diffTextureRegion = Frame.builder().textureRegion(Mockito.mock(TextureRegion.class))
                .translation(translation).dimensions(dimensions).build();
        Frame diffTranslation = Frame.builder().textureRegion(textureRegion)
                .translation(new Vector(3, 4)).dimensions(dimensions).build();
        Frame diffDimensions = Frame.builder().textureRegion(textureRegion)
                .translation(translation).dimensions(new Dimensions(14, 16)).build();

        assertNotEquals(frame1, diffTextureRegion);
        assertNotEquals(frame1, diffTranslation);
        assertNotEquals(frame1, diffDimensions);
        assertNotEquals(frame1, new Object());
        assertNotEquals(frame1, null);
    }

    @Test
    public void testHashCode(){
        Frame frame1 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
        Frame frame2 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
        Frame frame3 = Frame.builder().textureRegion(textureRegion).translation(translation).dimensions(dimensions).build();
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
        Frame diffTextureRegion = Frame.builder().textureRegion(Mockito.mock(TextureRegion.class))
                .translation(translation).dimensions(dimensions).build();
        Frame diffTranslation = Frame.builder().textureRegion(textureRegion)
                .translation(new Vector(3, 4)).dimensions(dimensions).build();
        Frame diffDimensions = Frame.builder().textureRegion(textureRegion)
                .translation(translation).dimensions(new Dimensions(14, 16)).build();

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
