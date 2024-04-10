package V2;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.TextureSelector.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextureSelectorTest {

//    private Texture texture1;
//    private Texture texture2;
//    private Texture texture3;
//    private Texture texture4;
//    private MockTimeProvider timeProvider;
//    private DeltaTime deltaTime;
//    private AnimationRepo<Texture> repository;
//    private TextureSelector textureSelector;
//    @BeforeEach
//    public void initialise(){
//        texture1 = Mockito.mock(Texture.class);
//        texture2 = Mockito.mock(Texture.class);
//        texture3 = Mockito.mock(Texture.class);
//        texture4 = Mockito.mock(Texture.class);
//
//        Animation<Texture> animation1 = new Animation<>(new Texture[]{texture1, texture2, texture3}, 3);
//        Animation<Texture> animation2 = new Animation<>(new Texture[]{texture2, texture3}, 2);
//        Animation<Texture> animation3 = new Animation<>(new Texture[]{texture3, texture4}, 2);
//
//        Map<Direction, Animation<Texture>> runningMap = new LinkedHashMap<>();
//        runningMap.put(Direction.UP, animation1);
//        runningMap.put(Direction.RIGHT, animation2);
//        runningMap.put(Direction.DOWN, animation3);
//        DirectionAnimationRepo<Texture> runningDirectionRepo = new DirectionAnimationRepo<>(runningMap);
//
//        Map<Direction, Animation<Texture>> walkingMap = new LinkedHashMap<>();
//        walkingMap.put(Direction.UP, animation3);
//        walkingMap.put(Direction.RIGHT, animation2);
//        runningMap.put(Direction.DOWN, animation1);
//        DirectionAnimationRepo<Texture> walkingDirectionRepo = new DirectionAnimationRepo<>(walkingMap);
//
//        Map<ActivityType, IDirectionAnimationRepository<Texture>> repoMap = new HashMap<>();
//        repoMap.put(ActivityType.RUNNING, runningDirectionRepo);
//        repoMap.put(ActivityType.WALKING, walkingDirectionRepo);
//        repository = new AnimationRepo<>(repoMap);
//
//        timeProvider = new MockTimeProvider();
//        deltaTime = DeltaTime.create(timeProvider);
//
//        textureSelector = TextureSelector.builder()
//                .activityType(ActivityType.RUNNING)
//                .direction(Direction.UP)
//                .deltaTime(deltaTime)
//                .animationRepository(repository)
//                .build();
//    }
//
//    @Test
//    public void testBuilderWithNullActivityType(){
//        TextureSelector.Builder nullActivityType = TextureSelector.builder().activityType(null)
//                .direction(Direction.UP).deltaTime(deltaTime).animationRepository(repository);
//        assertThrows(NullPointerException.class, nullActivityType::build);
//    }
//
//    @Test
//    public void testBuilderWithNullDirection(){
//        TextureSelector.Builder nullDirection = TextureSelector.builder().activityType(ActivityType.IDLING)
//                .direction(null).deltaTime(deltaTime).animationRepository(repository);
//        assertThrows(NullPointerException.class, nullDirection::build);
//    }
//
//    @Test
//    public void testBuilderWithNullDeltaTime(){
//        TextureSelector.Builder nullDeltaTime = TextureSelector.builder().activityType(ActivityType.IDLING)
//                .direction(Direction.UP).deltaTime(null).animationRepository(repository);
//        assertThrows(NullPointerException.class, nullDeltaTime::build);
//    }
//
//    @Test
//    public void testBuilderWithNullRepository(){
//        TextureSelector.Builder nullRepository = TextureSelector.builder().activityType(ActivityType.IDLING)
//                .direction(Direction.UP).deltaTime(deltaTime).animationRepository(null);
//        assertThrows(NullPointerException.class, nullRepository::build);
//    }
//
//    @Test
//    public void testGetTexture(){
//        assertEquals(textureSelector.getTexture(), texture1);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture2);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture3);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture1);
//    }
//
//
//    @Test
//    public void testSetSameDirection(){
//        textureSelector.setDirection(Direction.UP);
//        assertEquals(textureSelector.getTexture(), texture1);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture2);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture3);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture1);
//    }
//    @Test
//    public void testSetNewDirection(){
//        textureSelector.setDirection(Direction.RIGHT);
//        assertEquals(textureSelector.getTexture(), texture2);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture3);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture2);
//    }
//
//    @Test
//    public void testSetDirectionWithNull(){
//        assertThrows(NullPointerException.class, () -> textureSelector.setDirection(null));
//    }
//
//    @Test
//    public void testSetNewActivityType(){
//        textureSelector.setActivityType(ActivityType.WALKING);
//        assertEquals(textureSelector.getTexture(), texture3);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture4);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture3);
//    }
//
//    @Test
//    public void testSetSameActivityType(){
//        textureSelector.setActivityType(ActivityType.RUNNING);
//        assertEquals(textureSelector.getTexture(), texture1);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture2);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture3);
//        timeProvider.incrementMockValue();
//        assertEquals(textureSelector.getTexture(), texture1);
//    }
//
//    @Test
//    public void testSetActivityTypeWithNull(){
//        assertThrows(NullPointerException.class, () -> textureSelector.setActivityType(null));
//    }
}
