package TextureSelector;

import com.mygdx.game.AnimationRepository.Animation;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.AnimationRepository.Key;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static com.mygdx.game.Util.Direction.UP;
import static com.mygdx.game.WeekSchedule.ActivityType.RUNNING;
import static com.mygdx.game.WeekSchedule.ActivityType.WALKING;
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
        assertEquals(animation, repository.get(RUNNING, UP));
    }

    @Test
    public void testGetWithNullActivityType(){
        assertThrows(NullPointerException.class,
                () -> repository.get(null, UP));
    }

    @Test
    public void testGetWithNullDirection(){
        assertThrows(NullPointerException.class,
                () -> repository.get(RUNNING, null));
    }

    @Test
    public void testGetNoMapping(){
        assertThrows(NoSuchElementException.class,
                () -> repository.get(WALKING, UP));
    }

    @Test
    public void testToString(){
        assertNotNull(repository.toString());
    }
}
