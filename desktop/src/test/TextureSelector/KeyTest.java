package TextureSelector;

import com.mygdx.game.WeekSchedule.Action;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.TextureSelector.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTest {

    private Action action;
    private Direction direction;
    private Key key;

    @BeforeEach
    public void initialise(){
        action = Action.RUNNING;
        direction = Direction.UP;
        key = new Key(action, direction);
    }

    @Test
    public void testConstructorWithNullActivityType(){
        assertThrows(NullPointerException.class, () -> new Key(null, Direction.DOWN));
    }

    @Test
    public void testConstructorWithNullDirection(){
        assertThrows(NullPointerException.class, () -> new Key(Action.RUNNING, null));
    }

    @Test
    public void testActivityType(){
        assertEquals(action, key.action());
    }

    @Test
    public void testDirection(){
        assertEquals(direction, key.direction());
    }

    @Test
    public void testEquals(){
        Key key1 = new Key(action, direction);
        Key key2 = new Key(action, direction);
        Key key3 = new Key(action, direction);
        Key diffKey = new Key(action, Direction.RIGHT);
        // reflexive
        assertEquals(key1, key1);
        // symmetrical
        assertEquals(key1, key2);
        assertEquals(key2, key1);
        // transitive
        assertEquals(key1, key2);
        assertEquals(key2, key3);
        assertEquals(key1, key3);
        // not equals
        assertNotEquals(key1, diffKey);
        assertNotEquals(key1, new Object());
        assertNotEquals(key1, null);
    }

    @Test
    public void testHashCode(){
        Key key1 = new Key(action, direction);
        Key key2 = new Key(action, direction);
        Key key3 = new Key(action, direction);
        Key diffKey = new Key(action, Direction.RIGHT);
        // reflexive
        assertEquals(key1.hashCode(), key1.hashCode());
        // symmetrical
        assertEquals(key1.hashCode(), key2.hashCode());
        assertEquals(key2.hashCode(), key1.hashCode());
        // transitive
        assertEquals(key1.hashCode(), key2.hashCode());
        assertEquals(key2.hashCode(), key3.hashCode());
        assertEquals(key1.hashCode(), key3.hashCode());
        // not equals
        assertNotEquals(key1.hashCode(), diffKey.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Key[Action=RUNNING, Direction=UP]";
        assertEquals(key.toString(), expectedString);
    }
}
