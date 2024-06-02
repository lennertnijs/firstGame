package AnimationRepository;

import com.mygdx.game.AnimationRepository.*;
import com.mygdx.game.Keys.EntityKey;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static com.mygdx.game.Util.Direction.UP;
import static com.mygdx.game.Keys.NPCActivityType.RUNNING;
import static com.mygdx.game.Keys.NPCActivityType.WALKING;
import static org.junit.jupiter.api.Assertions.*;

public class AnimationRepositoryTest {

    private final EntityKey entityKey = new EntityKey(RUNNING,UP);
    private final Animation animation = new Animation(Collections.singletonList(Mockito.mock(Frame.class)), 0.5f);
    private final Map<AnimationKey, Animation> map = new HashMap<AnimationKey, Animation>(){{
        put(entityKey, animation);
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
        map.put(entityKey, null);
        assertThrows(NullPointerException.class,
                () -> new AnimationRepository(map));
    }

    @Test
    public void testGet(){
        assertEquals(animation, repository.get(new EntityKey(RUNNING, UP)));
    }

    @Test
    public void testGetWithNullAnimationKey(){
        assertThrows(NullPointerException.class,
                () -> repository.get(null));
    }

    @Test
    public void testGetNoMapping(){
        assertThrows(NoSuchElementException.class,
                () -> repository.get(new EntityKey(WALKING, UP)));
    }

    @Test
    public void testToString(){
        assertNotNull(repository.toString());
    }
}
