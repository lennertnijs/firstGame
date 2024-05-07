package TextureSelector;

import com.mygdx.game.TextureSelector.AnimationClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationClockTest {


    private AnimationClock clock;

    @BeforeEach
    public void initialise(){
        clock = new AnimationClock();
    }

    @Test
    public void testDelta(){
        assertEquals(0, clock.delta());
    }

    @Test
    public void testIncreaseDelta(){
        clock.increase(150);
        assertEquals(150, clock.delta());
        clock.increase(250);
        assertEquals( 400, clock.delta());
    }

    @Test
    public void testIncreaseNegative(){
        assertThrows(IllegalArgumentException.class, () -> clock.increase(-1));
    }

    @Test
    public void testIncreaseWithZero(){
        clock.increase(0);
        assertEquals(0, clock.delta());
    }

    @Test
    public void testReset(){
        clock.increase(150);
        clock.reset();
        assertEquals(0, clock.delta());
    }

    @Test
    public void testEquals(){
        AnimationClock clock1 = new AnimationClock();
        AnimationClock clock2 = new AnimationClock();
        AnimationClock clock3 = new AnimationClock();
        AnimationClock diffClock = new AnimationClock();
        diffClock.increase(100);
        // reflexive
        assertEquals(clock1, clock1);
        // symmetrical
        assertEquals(clock1, clock2);
        assertEquals(clock2, clock1);
        // transitive
        assertEquals(clock1, clock2);
        assertEquals(clock2, clock3);
        assertEquals(clock1, clock3);
        // not equals
        assertNotEquals(clock1, diffClock);
        assertNotEquals(clock1, new Object());
        assertNotEquals(clock1, null);
    }

    @Test
    public void testHashCode(){
        AnimationClock clock1 = new AnimationClock();
        AnimationClock clock2 = new AnimationClock();
        AnimationClock clock3 = new AnimationClock();
        AnimationClock diffClock = new AnimationClock();
        diffClock.increase(100);
        // reflexive
        assertEquals(clock1.hashCode(), clock1.hashCode());
        // symmetrical
        assertEquals(clock1.hashCode(), clock2.hashCode());
        assertEquals(clock2.hashCode(), clock1.hashCode());
        // transitive
        assertEquals(clock1.hashCode(), clock2.hashCode());
        assertEquals(clock2.hashCode(), clock3.hashCode());
        assertEquals(clock1.hashCode(), clock3.hashCode());
        // not equals
        assertNotEquals(clock1.hashCode(), diffClock.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "AnimationClock[deltaInMillis=0.000000]";
        assertEquals(expected, clock.toString());
    }

    @Test
    public void testCopy(){
        AnimationClock copy = clock.copy();
        assertEquals(clock, copy);
        clock.increase(100);
        assertNotEquals(clock, copy);

    }
}
