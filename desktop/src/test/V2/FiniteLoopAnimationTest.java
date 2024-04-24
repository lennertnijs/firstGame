package V2;

import com.mygdx.game.V2.TextureSelector.OldAnimation;
import com.mygdx.game.V2.TextureSelector.FiniteLoopAnimation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FiniteLoopAnimationTest {

    private OldAnimation<Integer> oldAnimation;
    private FiniteLoopAnimation<Integer> finiteAnimation;
    @BeforeEach
    public void initialise(){
        Integer[] frames = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] fallbackFrames = new Integer[]{7, 8, 9};
        oldAnimation = new OldAnimation<>(frames, 3);
        OldAnimation<Integer> fallbackOldAnimation = new OldAnimation<>(fallbackFrames, 3);
        finiteAnimation = new FiniteLoopAnimation<>(oldAnimation, 1, fallbackOldAnimation);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> new FiniteLoopAnimation<>(null, 1, oldAnimation));
        assertThrows(IllegalArgumentException.class, () -> new FiniteLoopAnimation<>(oldAnimation, -1, oldAnimation));
        assertThrows(NullPointerException.class, () -> new FiniteLoopAnimation<>(oldAnimation, 1, null));
    }

    @Test
    public void testGetFrame(){
        assertEquals(finiteAnimation.getFrame(0), 1);
        assertEquals(finiteAnimation.getFrame(0.5f), 2);
        assertEquals(finiteAnimation.getFrame(1f), 3);
        assertEquals(finiteAnimation.getFrame(1.5f), 4);
        assertEquals(finiteAnimation.getFrame(2f), 5);
        assertEquals(finiteAnimation.getFrame(2.5f), 6);
    }

    @Test
    public void testGetFrameFallBack(){
        assertEquals(finiteAnimation.getFrame(3), 7);
        assertEquals(finiteAnimation.getFrame(3.5f), 7);
        assertEquals(finiteAnimation.getFrame(4f), 8);
        assertEquals(finiteAnimation.getFrame(4.5f), 8);
        assertEquals(finiteAnimation.getFrame(5f), 9);
        assertEquals(finiteAnimation.getFrame(5.5f), 9);
        assertEquals(finiteAnimation.getFrame(6f), 7);
    }

    @Test
    public void testGetFrameNegativeDelta(){
        assertThrows(IllegalArgumentException.class, () -> finiteAnimation.getFrame(-0.1f));
    }
}
