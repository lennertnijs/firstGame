package V2;

import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.TextureSelector.Animation;
import com.mygdx.game.V2.TextureSelector.AnimationRepo;
import com.mygdx.game.V2.TextureSelector.DirectionAnimationRepo;
import com.mygdx.game.V2.TextureSelector.IDirectionAnimationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimationRepositoryTest {

    private Animation<Integer> animation1;
    private Animation<Integer> animation2;
    private Animation<Integer> animation3;
    private Map<Direction, Animation<Integer>> map1;
    private DirectionAnimationRepo<Integer> repo1;
    private DirectionAnimationRepo<Integer> repo2;
    private AnimationRepo<Integer> animationRepo;

    @BeforeEach
    public void initialise(){
        animation1 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        animation3 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);

        map1 = new LinkedHashMap<Direction, Animation<Integer>>(){{
            put(Direction.UP, animation1);
            put(Direction.RIGHT, animation2);
            put(Direction.DOWN, animation3);
        }};

        Map<Direction, Animation<Integer>> map2 = new LinkedHashMap<Direction, Animation<Integer>>(){{
            put(Direction.UP, animation1);
        }};

        repo1 = new DirectionAnimationRepo<>(map1);
        repo2 = new DirectionAnimationRepo<>(map2);

        Map<ActivityType, IDirectionAnimationRepository<Integer>> repoMap =
                new HashMap<ActivityType, IDirectionAnimationRepository<Integer>>(){{
            put(ActivityType.RUNNING, repo1);
            put(ActivityType.WALKING, repo2);
        }};
        animationRepo = new AnimationRepo<>(repoMap);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(null));
        Map<ActivityType, IDirectionAnimationRepository<Integer>> map1 = new HashMap<ActivityType, IDirectionAnimationRepository<Integer>>(){{
            put(null, repo1);
        }};
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(map1));
        Map<ActivityType, IDirectionAnimationRepository<Integer>> map2 = new HashMap<ActivityType, IDirectionAnimationRepository<Integer>>(){{
            put(ActivityType.IDLING, null);
        }};
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(map2));
    }

    @Test
    public void testGetAnimation(){
        assertEquals(animationRepo.getAnimation(ActivityType.WALKING, Direction.UP), new Animation<>(new Integer[]{1,2,3,4,5,6}, 3));
        assertEquals(animationRepo.getAnimation(ActivityType.RUNNING, Direction.UP), new Animation<>(new Integer[]{1,2,3,4,5,6}, 3));
        assertEquals(animationRepo.getAnimation(ActivityType.RUNNING, Direction.RIGHT), new Animation<>(new Integer[]{1,2,3}, 3));
    }

    @Test
    public void testGetAnimationNoMappingForActivity(){
        assertThrows(NoSuchElementException.class, () -> animationRepo.getAnimation(ActivityType.IDLING, Direction.DOWN));
    }


}
