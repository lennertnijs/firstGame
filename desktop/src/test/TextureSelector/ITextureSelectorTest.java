package TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.TextureSelector.*;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ITextureSelectorTest {

    private Frame frame1;
    private Frame frame2;
    private Frame frame3;
    private Key key1;
    private AnimationRepository repo;
    private AnimationClock clock;
    private TextureSelector selector;

    @BeforeEach
    public void initialise(){
        key1 = new Key(ActivityType.RUNNING, Direction.UP);
        Key key2 = new Key(ActivityType.RUNNING, Direction.RIGHT);
        Key key3 = new Key(ActivityType.WALKING, Direction.UP);
        frame1 = new Frame(Mockito.mock(TextureRegion.class), new Vector(5, 5), new Dimensions(15, 15));
        frame2 = new Frame(Mockito.mock(TextureRegion.class), new Vector(5, 5), new Dimensions(15, 15));
        frame3 = new Frame(Mockito.mock(TextureRegion.class), new Vector(5, 5), new Dimensions(15, 15));
        Animation animation1 = new Animation(Arrays.asList(frame1, frame2), 1000);
        Animation animation2 = new Animation(Arrays.asList(frame1, frame2, frame3), 3000);
        Map<Key, Animation> map = new HashMap<>();
        map.put(key1, animation1);
        map.put(key2, animation2);
        map.put(key3, animation2);
        repo = new AnimationRepository(map);
        clock = new AnimationClock();

        selector = new TextureSelector(key1, repo, clock);
    }

    @Test
    public void testConstructorWithNullKey(){
        assertThrows(NullPointerException.class, () -> new TextureSelector(null, repo, clock));
    }

    @Test
    public void testConstructorWithNullRepo(){
        assertThrows(NullPointerException.class, () -> new TextureSelector(key1, null, clock));
    }

    @Test
    public void testConstructorWithNullClock(){
        assertThrows(NullPointerException.class, () -> new TextureSelector(key1, repo, null));
    }

    @Test
    public void testGetFrame(){
        assertEquals(frame1, selector.getFrame());
        selector.increaseClock(500);
        assertEquals(frame2, selector.getFrame());
        selector.increaseClock(500);
        assertEquals(frame1, selector.getFrame());
    }

    @Test
    public void testIncreaseClockNegative(){
        assertThrows(IllegalArgumentException.class, () -> selector.increaseClock(-1000));
    }

    @Test
    public void testSetActivityType(){
        selector.increaseClock(5000);
        selector.setActivityType(ActivityType.WALKING);
        assertEquals(frame1, selector.getFrame());
        selector.increaseClock(1000);
        assertEquals(frame2, selector.getFrame());
        selector.increaseClock(1000);
        assertEquals(frame3, selector.getFrame());
    }

    @Test
    public void testSetActivityTypeNull(){
        assertThrows(NullPointerException.class, () -> selector.setActivityType(null));
    }

    @Test
    public void testSetActivityTypeToSame(){
        selector.increaseClock(500);
        selector.setActivityType(ActivityType.RUNNING);
        // clock does not reset
        assertEquals(frame2, selector.getFrame());
    }

    @Test
    public void testSetDirection(){
        selector.increaseClock(1500);
        selector.setDirection(Direction.RIGHT);
        assertEquals(frame1, selector.getFrame());
        selector.increaseClock(1000);
        assertEquals(frame2, selector.getFrame());
        selector.increaseClock(1000);
        assertEquals(frame3, selector.getFrame());
    }

    @Test
    public void testSetDirectionNull(){
        assertThrows(NullPointerException.class, () -> selector.setDirection(null));
    }

    @Test
    public void testSetDirectionToSame(){
        selector.increaseClock(500);
        selector.setDirection(Direction.UP);
        // clock does not reset
        assertEquals(frame2, selector.getFrame());
    }

    @Test
    public void testEquals(){
        TextureSelector selector1 = new TextureSelector(key1, repo, clock);
        TextureSelector selector2 = new TextureSelector(key1, repo, clock);
        TextureSelector selector3 = new TextureSelector(key1, repo, clock);
        clock.increase(500);
        TextureSelector diffSelector = new TextureSelector(key1, repo, clock);
        // reflexive
        assertEquals(selector1, selector1);
        // symmetrical
        assertEquals(selector1, selector2);
        assertEquals(selector2, selector1);
        // transitive
        assertEquals(selector1, selector2);
        assertEquals(selector2, selector3);
        assertEquals(selector1, selector3);
        // not equals
        assertNotEquals(selector1, diffSelector);
        assertNotEquals(selector1, new Object());
        assertNotEquals(selector1, null);
    }

    @Test
    public void testHashCode(){
        TextureSelector selector1 = new TextureSelector(key1, repo, clock);
        TextureSelector selector2 = new TextureSelector(key1, repo, clock);
        TextureSelector selector3 = new TextureSelector(key1, repo, clock);
        clock.increase(500);
        TextureSelector diffSelector = new TextureSelector(key1, repo, clock);
        // reflexive
        assertEquals(selector1.hashCode(), selector1.hashCode());
        // symmetrical
        assertEquals(selector1.hashCode(), selector2.hashCode());
        assertEquals(selector2.hashCode(), selector1.hashCode());
        // transitive
        assertEquals(selector1.hashCode(), selector2.hashCode());
        assertEquals(selector2.hashCode(), selector3.hashCode());
        assertEquals(selector1.hashCode(), selector3.hashCode());
        // not equals
        assertNotEquals(selector1.hashCode(), diffSelector.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(selector.toString());
    }

    @Test
    public void testCopy(){
        TextureSelector copy = selector.copy();
        assertEquals(selector, copy);
        copy.setActivityType(ActivityType.IDLING);
        assertNotEquals(selector, copy);
    }
}
