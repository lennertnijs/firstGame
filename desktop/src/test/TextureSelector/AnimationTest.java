package TextureSelector;

import com.mygdx.game.AnimationRepository.Animation;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.AnimationRepository.IFrame;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {

    private final Frame frame1 = Mockito.mock(Frame.class);
    private final Frame frame2 = Mockito.mock(Frame.class);
    private final List<IFrame> frames = Arrays.asList(frame1, frame2);
    private final float duration = 1.5f;
    private final Animation animation = new Animation(frames, duration);

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class,
                () -> new Animation(null, duration));
    }

    @Test
    public void testConstructorListContainsNull(){
        assertThrows(NullPointerException.class,
                () -> new Animation(Arrays.asList(frame1, null), duration));
    }

    @Test
    public void testConstructorListEmpty(){
        assertThrows(IllegalArgumentException.class,
                () -> new Animation(new ArrayList<>(), 0.5f));
    }

    @Test
    public void testConstructorNegativeDuration(){
        assertThrows(IllegalArgumentException.class,
                () -> new Animation(frames, -1));
    }

    @Test
    public void testConstructorZeroDuration(){
        assertThrows(IllegalArgumentException.class,
                () -> new Animation(frames, 0));
    }

    @Test
    public void testGetFrame(){
        assertEquals(frame1, animation.getFrame(0));
        assertEquals(frame1, animation.getFrame(0.74f));
        assertEquals(frame2, animation.getFrame(0.75f));
        assertEquals(frame2, animation.getFrame(1.49f));
        assertEquals(frame1, animation.getFrame(1.5f));
    }

    @Test
    public void testGetFrameNegativeDelta(){
        assertThrows(IllegalArgumentException.class,
                () -> animation.getFrame(-0.1f));
    }

    @Test
    public void testEquals(){
        Animation animation1 = new Animation(frames, 1.5f);
        Animation animation2 = new Animation(frames, 1.5f);
        Animation animation3 = new Animation(frames, 1.5f);
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
        Animation diffFrames = new Animation(Arrays.asList(frame2, frame1), 1.5f);
        Animation diffDuration = new Animation(frames, 3f);

        assertNotEquals(animation1, diffFrames);
        assertNotEquals(animation1, diffDuration);
        assertNotEquals(animation1, new Object());
        assertNotEquals(animation1, null);
    }

    @Test
    public void testHashCode(){
        Animation animation1 = new Animation(frames, 1.5f);
        Animation animation2 = new Animation(frames, 1.5f);
        Animation animation3 = new Animation(frames, 1.5f);
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
        Animation diffFrames = new Animation(Arrays.asList(frame2, frame1), 1.5f);
        Animation diffDuration = new Animation(frames, 3f);
        assertNotEquals(animation1.hashCode(), diffFrames.hashCode());
        assertNotEquals(animation1.hashCode(), diffDuration.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(animation.toString());
    }
}
