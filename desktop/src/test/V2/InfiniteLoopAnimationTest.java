package V2;

import com.mygdx.game.V2.TextureSelector.OldAnimation;
import com.mygdx.game.V2.TextureSelector.InfiniteLoopAnimation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InfiniteLoopAnimationTest {

    private Integer[] frames;
    private OldAnimation<Integer> oldAnimation;
    private InfiniteLoopAnimation<Integer> infAnimation;
    @BeforeEach
    public void initialise(){
        frames = new Integer[]{1,2,3,4,5,6};
        oldAnimation = new OldAnimation<>(frames, 3);
        infAnimation = new InfiniteLoopAnimation<>(oldAnimation);
    }


    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> new InfiniteLoopAnimation<Integer>(null));
    }
    @Test
    public void testGetFrame(){
        assertEquals(infAnimation.getFrame(0), 1);
        assertEquals(infAnimation.getFrame(0.5f), 2);
        assertEquals(infAnimation.getFrame(1f), 3);
        assertEquals(infAnimation.getFrame(1.5f), 4);
        assertEquals(infAnimation.getFrame(2f), 5);
        assertEquals(infAnimation.getFrame(2.5f), 6);
    }

    @Test
    public void testGetFrameValuesOutsideAnimationLength(){
        assertEquals(infAnimation.getFrame(3), 1);
        assertEquals(infAnimation.getFrame(3.5f), 2);
        assertEquals(infAnimation.getFrame(4f), 3);
        assertEquals(infAnimation.getFrame(4.5f), 4);
        assertEquals(infAnimation.getFrame(5f), 5);
        assertEquals(infAnimation.getFrame(5.5f), 6);
        assertEquals(infAnimation.getFrame(6f), 1);
    }

    @Test
    public void testGetFrameNegativeDelta(){
        assertThrows(IllegalArgumentException.class, () -> infAnimation.getFrame(-0.1f));
    }


}
