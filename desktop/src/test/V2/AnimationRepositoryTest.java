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
    private DirectionAnimationRepo<Integer> runningDirectionRepo;
    private AnimationRepo<Integer> animationRepo;

    @BeforeEach
    public void initialise(){
        animation1 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        Animation<Integer> animation3 = new Animation<>(new Integer[]{4, 5, 6}, 3);

        Map<Direction, Animation<Integer>> runningMap = new LinkedHashMap<>();
        runningMap.put(Direction.UP, animation1);
        runningMap.put(Direction.RIGHT, animation2);
        runningMap.put(Direction.DOWN, animation3);
        runningDirectionRepo = new DirectionAnimationRepo<>(runningMap);

        Map<Direction, Animation<Integer>> walkingMap = new LinkedHashMap<Direction, Animation<Integer>>();
        walkingMap.put(Direction.UP, animation1);
        DirectionAnimationRepo<Integer> walkingDirectionRepo = new DirectionAnimationRepo<>(walkingMap);

        Map<ActivityType, IDirectionAnimationRepository<Integer>> repoMap = new HashMap<>();
        repoMap.put(ActivityType.RUNNING, runningDirectionRepo);
        repoMap.put(ActivityType.WALKING, walkingDirectionRepo);
        animationRepo = new AnimationRepo<>(repoMap);
    }

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        Map<ActivityType, IDirectionAnimationRepository<Integer>> nullKeyMap = new HashMap<>();
        nullKeyMap.put(null, runningDirectionRepo);
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(nullKeyMap));
    }
    @Test
    public void testConstructorWithNullValue(){
        Map<ActivityType, IDirectionAnimationRepository<Integer>> nullValueMap = new HashMap<>();
        nullValueMap.put(ActivityType.IDLING, null);
        assertThrows(NullPointerException.class, () -> new AnimationRepo<>(nullValueMap));
    }

    @Test
    public void testGetAnimation(){
        assertEquals(animationRepo.getAnimation(ActivityType.WALKING, Direction.UP), animation1);
        assertEquals(animationRepo.getAnimation(ActivityType.RUNNING, Direction.UP), animation1);
        assertEquals(animationRepo.getAnimation(ActivityType.RUNNING, Direction.RIGHT), animation2);
        assertThrows(NoSuchElementException.class,
                () -> animationRepo.getAnimation(ActivityType.IDLING, Direction.DOWN));
    }
}
