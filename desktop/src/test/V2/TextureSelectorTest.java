package V2;

import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.TextureSelector.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextureSelectorTest {

    private Animation<Integer> animation1;
    private Animation<Integer> animation2;
    private Animation<Integer> animation3;
    private AnimationRepo<Integer> repository;
    private TextureSelector textureSelector;
    @BeforeEach
    public void initialise(){
        animation1 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        animation3 = new Animation<>(new Integer[]{4,5,6}, 3);

        Map<Direction, Animation<Integer>> runningMap = new LinkedHashMap<>();
        runningMap.put(Direction.UP, animation1);
        runningMap.put(Direction.RIGHT, animation2);
        runningMap.put(Direction.DOWN, animation3);
        DirectionAnimationRepo<Integer> runningDirectionRepo = new DirectionAnimationRepo<>(runningMap);

        Map<Direction, Animation<Integer>> walkingMap = new LinkedHashMap<>();
        walkingMap.put(Direction.UP, animation1);
        DirectionAnimationRepo<Integer> walkingDirectionRepo = new DirectionAnimationRepo<>(walkingMap);

        Map<ActivityType, IDirectionAnimationRepository<Integer>> repoMap = new HashMap<>();
        repoMap.put(ActivityType.RUNNING, runningDirectionRepo);
        repoMap.put(ActivityType.WALKING, walkingDirectionRepo);
        repository = new AnimationRepo<>(repoMap);

//        DeltaTime deltaTime = DeltaTime.create(new MockTimeProvider());
//        textureSelector = TextureSelector.builder().activityType(ActivityType.RUNNING)
//                .direction(Direction.UP)
//                .deltaTime(deltaTime)
//                .build();

    }
}
