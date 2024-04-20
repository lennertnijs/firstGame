package V2;

import com.mygdx.game.V2.TextureSelector.ActivityType;
import com.mygdx.game.V2.TextureSelector.Direction;
import com.mygdx.game.V2.TextureSelector.AnimationKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationKeyTest {


    private AnimationKey key1;
    private AnimationKey key2;
    private AnimationKey key3;

    @BeforeEach
    public void initialise(){
        key1 = AnimationKey.create(ActivityType.RUNNING, Direction.UP);
        key2 = AnimationKey.create(ActivityType.RUNNING, Direction.RIGHT);
        key3 = AnimationKey.create(ActivityType.RUNNING, Direction.UP);
    }

    @Test
    public void testConstructor(){
        assertEquals(key1.getDirection(), Direction.UP);
        assertEquals(key1.getActivityType(), ActivityType.RUNNING);
    }

    @Test
    public void testConstructorWithNullActivityType(){
        assertThrows(NullPointerException.class, () -> AnimationKey.create(null, Direction.DOWN));
    }

    @Test
    public void testConstructorWithNullDirection(){
        assertThrows(NullPointerException.class, () -> AnimationKey.create(ActivityType.RUNNING, null));
    }

    @Test
    public void testEquals(){
        assertEquals(key1, key3);
        assertNotEquals(key1, key2);
        assertNotEquals(key1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(key1.hashCode(), key3.hashCode());
        assertNotEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "AnimationKey[ActivityType=RUNNING, Direction=UP]";
        assertEquals(key1.toString(), expectedString);
    }
}
