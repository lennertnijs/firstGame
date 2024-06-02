package AnimationRepository;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.AnimationRepository.EntityKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityKeyTest {

    private ActivityType activityType;
    private Direction direction;
    private EntityKey entityKey;

    @BeforeEach
    public void initialise(){
        activityType = ActivityType.RUNNING;
        direction = Direction.UP;
        entityKey = new EntityKey(activityType, direction);
    }

    @Test
    public void testConstructorWithNullActivityType(){
        assertThrows(NullPointerException.class, () -> new EntityKey(null, Direction.DOWN));
    }

    @Test
    public void testConstructorWithNullDirection(){
        assertThrows(NullPointerException.class, () -> new EntityKey(ActivityType.RUNNING, null));
    }

    @Test
    public void testActivityType(){
        assertEquals(activityType, entityKey.action());
    }

    @Test
    public void testDirection(){
        assertEquals(direction, entityKey.direction());
    }

    @Test
    public void testEquals(){
        EntityKey entityKey1 = new EntityKey(activityType, direction);
        EntityKey entityKey2 = new EntityKey(activityType, direction);
        EntityKey entityKey3 = new EntityKey(activityType, direction);
        EntityKey diffEntityKey = new EntityKey(activityType, Direction.RIGHT);
        // reflexive
        assertEquals(entityKey1, entityKey1);
        // symmetrical
        assertEquals(entityKey1, entityKey2);
        assertEquals(entityKey2, entityKey1);
        // transitive
        assertEquals(entityKey1, entityKey2);
        assertEquals(entityKey2, entityKey3);
        assertEquals(entityKey1, entityKey3);
        // not equals
        assertNotEquals(entityKey1, diffEntityKey);
        assertNotEquals(entityKey1, new Object());
        assertNotEquals(entityKey1, null);
    }

    @Test
    public void testHashCode(){
        EntityKey entityKey1 = new EntityKey(activityType, direction);
        EntityKey entityKey2 = new EntityKey(activityType, direction);
        EntityKey entityKey3 = new EntityKey(activityType, direction);
        EntityKey diffEntityKey = new EntityKey(activityType, Direction.RIGHT);
        // reflexive
        assertEquals(entityKey1.hashCode(), entityKey1.hashCode());
        // symmetrical
        assertEquals(entityKey1.hashCode(), entityKey2.hashCode());
        assertEquals(entityKey2.hashCode(), entityKey1.hashCode());
        // transitive
        assertEquals(entityKey1.hashCode(), entityKey2.hashCode());
        assertEquals(entityKey2.hashCode(), entityKey3.hashCode());
        assertEquals(entityKey1.hashCode(), entityKey3.hashCode());
        // not equals
        assertNotEquals(entityKey1.hashCode(), diffEntityKey.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Key[Action=RUNNING, Direction=UP]";
        assertEquals(entityKey.toString(), expectedString);
    }
}
