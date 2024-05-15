package TextureSelector;

import com.mygdx.game.TextureSelector.Animation;
import com.mygdx.game.TextureSelector.AnimationRepository;
import com.mygdx.game.TextureSelector.Frame;
import com.mygdx.game.TextureSelector.Key;
import com.mygdx.game.WeekSchedule.Action;
import com.mygdx.game.Util.Direction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static com.mygdx.game.Util.Direction.UP;
import static com.mygdx.game.WeekSchedule.Action.RUNNING;
import static org.junit.jupiter.api.Assertions.*;

public class AnimationRepositoryTest {

    private final Key key = new Key(RUNNING,UP);
    private final Animation animation = new Animation(Collections.singletonList(Mockito.mock(Frame.class)), 0.5f);
    private final Map<Key, Animation> map = new HashMap<Key, Animation>(){{
        put(key, animation);
    }};
    private final AnimationRepository repository = new AnimationRepository(map);

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class,
                () -> new AnimationRepository(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        map.put(null, animation);
        assertThrows(NullPointerException.class,
                () -> new AnimationRepository(map));
    }

    @Test
    public void testConstructorWithNullValue(){
        map.put(key, null);
        assertThrows(NullPointerException.class,
                () -> new AnimationRepository(map));
    }

    @Test
    public void testGet(){
        assertEquals(animation, repository.get(key));
    }

    @Test
    public void testGetWithNull(){
        assertThrows(NullPointerException.class,
                () -> repository.get(null));
    }

    @Test
    public void testGetNoMapping(){
        Key noMapKey = new Key(Action.WALKING, Direction.RIGHT);
        assertThrows(NoSuchElementException.class,
                () -> repository.get(noMapKey));
    }

    @Test
    public void testToString(){
        assertNotNull(repository.toString());
    }
}
