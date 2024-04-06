package V2;

import com.mygdx.game.V2.TextureSelector.Animation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {

    private Integer[] frames1;
    private Animation<Integer> animation1;
    private Animation<Integer> animation2;
    private Animation<Integer> animation3;

    @BeforeEach
    public void initialise(){
        frames1 = new Integer[]{1,2,3,4,5,6};
        animation1 = new Animation<>(frames1, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        animation3 = new Animation<>(frames1, 3);
    }

    @Test
    public void testConstructor(){
        assertEquals(animation1.getFrames(), frames1);
        assertEquals(animation1.getDuration(), 3);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> new Animation<>(null, 3));
        assertThrows(NullPointerException.class, () -> new Animation<>(new Integer[]{1, null}, 3));
        assertThrows(IllegalArgumentException.class, () -> new Animation<>(frames1, -1));
    }

    @Test
    public void testGetFrame(){
        assertEquals(animation1.getFrame(0f), 1);
        assertEquals(animation1.getFrame(0.49999f), 1);
        assertEquals(animation1.getFrame(0.5f), 2);
        assertEquals(animation1.getFrame(1f), 3);
        assertEquals(animation1.getFrame(1.5f), 4);
        assertEquals(animation1.getFrame(2f), 5);
        assertEquals(animation1.getFrame(2.5f), 6);
        assertEquals(animation1.getFrame(2.99f), 6);
    }

    @Test
    public void testGetFrameWithInvalid(){
        assertThrows(IllegalArgumentException.class, () -> animation1.getFrame(-0.1f));
        assertThrows(IllegalArgumentException.class, () -> animation1.getFrame(3));
    }

    @Test
    public void testEquals(){
        assertEquals(animation1, animation3);
        assertNotEquals(animation1, animation2);
        assertNotEquals(animation1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(animation1.hashCode(), animation3.hashCode());
        assertNotEquals(animation1.hashCode(), animation2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Animation[Frames=[1, 2, 3, 4, 5, 6], Duration=3.000000]";
        assertEquals(animation1.toString(), expectedString);
    }
}
