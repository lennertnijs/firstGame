package TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.TextureSelector.Animation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {

    private TextureRegion frame1;
    private TextureRegion frame2;
    private TextureRegion frame3;
    private List<TextureRegion> frames;
    private float duration;
    private Animation animation;

    @BeforeEach
    public void initialise(){
        frame1 = Mockito.mock(TextureRegion.class);
        frame2 = Mockito.mock(TextureRegion.class);
        frame3 = Mockito.mock(TextureRegion.class);
        frames = Arrays.asList(frame1, frame2, frame3);
        duration = 1.5f;
        animation = new Animation(frames, duration);
    }

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class, () -> new Animation(null, duration));
    }

    @Test
    public void testConstructorListContainsNull(){
        assertThrows(NullPointerException.class, () -> new Animation(Arrays.asList(frame1, null), duration));
    }

    @Test
    public void testConstructorListEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Animation(new ArrayList<>(), 0.5f));
    }

    @Test
    public void testConstructorNegativeDuration(){
        assertThrows(IllegalArgumentException.class, () -> new Animation(frames, -1));
    }

    @Test
    public void testConstructorZeroDuration(){
        assertThrows(IllegalArgumentException.class, () -> new Animation(frames, 0));
    }

    @Test
    public void testFrames(){
        assertEquals(frames, animation.frames());
    }

    @Test
    public void testDuration(){
        assertEquals(duration, animation.duration());
    }

    @Test
    public void testGetFrame(){
        assertEquals(frame1, animation.getFrame(0));
        assertEquals(frame2, animation.getFrame(0.5f));
        assertEquals(frame3, animation.getFrame(1f));
        assertEquals(frame1, animation.getFrame(1.5f));
        assertEquals(frame2, animation.getFrame(2.0f));
        assertEquals(frame3, animation.getFrame(2.5f));
        assertEquals(frame1, animation.getFrame(3.0f));
        assertEquals(frame2, animation.getFrame(3.5f));
        assertEquals(frame3, animation.getFrame(4.0f));
    }

    @Test
    public void testGetFrameNegativeDelta(){
        assertThrows(IllegalArgumentException.class, () -> animation.getFrame(-0.1f));
    }

    @Test
    public void testEquals(){
        Animation animation1 = new Animation(frames, 1.5f);
        Animation animation2 = new Animation(frames, 1.5f);
        Animation animation3 = new Animation(frames, 1.5f);
        Animation diffAnimation = new Animation(frames, 3f);
        // reflexive
        assertEquals(animation1, animation1);
        // symmetrical
        assertEquals(animation1, animation2);
        assertEquals(animation2, animation1);
        // transitive
        assertEquals(animation1, animation2);
        assertEquals(animation2, animation3);
        assertEquals(animation1, animation3);
        // not equals
        assertNotEquals(animation1, diffAnimation);
        assertNotEquals(animation1, new Object());
        assertNotEquals(animation1, null);
    }

    @Test
    public void testHashCode(){
        Animation animation1 = new Animation(frames, 1.5f);
        Animation animation2 = new Animation(frames, 1.5f);
        Animation animation3 = new Animation(frames, 1.5f);
        Animation diffAnimation = new Animation(frames, 3f);
        // reflexive
        assertEquals(animation1.hashCode(), animation1.hashCode());
        // symmetrical
        assertEquals(animation1.hashCode(), animation2.hashCode());
        assertEquals(animation2.hashCode(), animation1.hashCode());
        // transitive
        assertEquals(animation1.hashCode(), animation2.hashCode());
        assertEquals(animation2.hashCode(), animation3.hashCode());
        assertEquals(animation1.hashCode(), animation3.hashCode());
        // not equals
        assertNotEquals(animation1.hashCode(), diffAnimation.hashCode());
    }

    @Test
    public void testToString(){
        String prefix = "Animation[frames=[";
        String suffix = ", durationInMillis=1.500000]";
        assertNotNull(animation.toString());
        assertTrue(animation.toString().contains(prefix));
        assertTrue(animation.toString().contains(suffix));
    }

}
