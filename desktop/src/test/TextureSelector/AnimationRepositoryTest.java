package TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.TextureSelector.Animation;
import com.mygdx.game.TextureSelector.AnimationRepository;
import com.mygdx.game.TextureSelector.Key;
import com.mygdx.game.Util.ActivityType;
import com.mygdx.game.Util.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationRepositoryTest {

    private Key key1;
    private Key key2;
    private Animation animation1;
    private Animation animation2;
    private Map<Key, Animation> map;
    private AnimationRepository repo;

    @BeforeEach
    public void initialise(){
        key1 = new Key(ActivityType.RUNNING, Direction.UP);
        key2 = new Key(ActivityType.IDLING, Direction.UP);
        TextureRegion t1 = Mockito.mock(TextureRegion.class);
        TextureRegion t2 = Mockito.mock(TextureRegion.class);
        animation1 = new Animation(Collections.singletonList(t1),  .5f);
        animation2 = new Animation(Collections.singletonList(t2),  .5f);

        map = new HashMap<>();
        map.put(key1, animation1);
        map.put(key2, animation2);
        repo = new AnimationRepository(map);
    }

    @Test
    public void testConstructorWithNullMap(){
        assertThrows(NullPointerException.class, () -> new AnimationRepository(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        map.put(null, animation1);
        assertThrows(NullPointerException.class, () -> new AnimationRepository(map));
    }

    @Test
    public void testConstructorWithNullValue(){
        map.put(key1, null);
        assertThrows(NullPointerException.class, () -> new AnimationRepository(map));
    }

    @Test
    public void testMap(){
        assertEquals(map, repo.map());
    }

    @Test
    public void testGet(){
        assertEquals(animation1, repo.get(key1));
        assertEquals(animation2, repo.get(key2));
    }

    @Test
    public void testGetWithNull(){
        assertThrows(NullPointerException.class, () -> repo.get(null));
    }

    @Test
    public void testGetNoMapping(){
        Key noMapKey = new Key(ActivityType.WALKING, Direction.RIGHT);
        assertThrows(NoSuchElementException.class, () -> repo.get(noMapKey));
    }

    @Test
    public void testEquals(){
        AnimationRepository repo1 = new AnimationRepository(map);
        AnimationRepository repo2 = new AnimationRepository(map);
        AnimationRepository repo3 = new AnimationRepository(map);
        Key newKey = new Key(ActivityType.WALKING, Direction.RIGHT);
        map.put(newKey, animation2);
        AnimationRepository diffRepo = new AnimationRepository(map);
        // reflexive
        assertEquals(repo1, repo1);
        // symmetrical
        assertEquals(repo1, repo2);
        assertEquals(repo2, repo1);
        // transitive
        assertEquals(repo1, repo2);
        assertEquals(repo2, repo3);
        assertEquals(repo1, repo3);
        // not equals
        assertNotEquals(repo1, diffRepo);
        assertNotEquals(repo1, new Object());
        assertNotEquals(repo1, null);
    }

    @Test
    public void testHashCode(){
        AnimationRepository repo1 = new AnimationRepository(map);
        AnimationRepository repo2 = new AnimationRepository(map);
        AnimationRepository repo3 = new AnimationRepository(map);
        Key newKey = new Key(ActivityType.WALKING, Direction.RIGHT);
        map.put(newKey, animation2);
        AnimationRepository diffRepo = new AnimationRepository(map);
        // reflexive
        assertEquals(repo1.hashCode(), repo1.hashCode());
        // symmetrical
        assertEquals(repo1.hashCode(), repo2.hashCode());
        assertEquals(repo2.hashCode(), repo1.hashCode());
        // transitive
        assertEquals(repo1.hashCode(), repo2.hashCode());
        assertEquals(repo2.hashCode(), repo3.hashCode());
        assertEquals(repo1.hashCode(), repo3.hashCode());
        // not equals
        assertNotEquals(repo1.hashCode(), diffRepo.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(repo.toString());
    }
}
