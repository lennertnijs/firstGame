package V2;

import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.TextureSelector.Animation;
import com.mygdx.game.V2.TextureSelector.DirectionAnimationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionAnimationRepoTest {

    private Animation<Integer> animation1;
    private Animation<Integer> animation2;
    private Animation<Integer> animation3;
    private DirectionAnimationRepo<Integer> repo;

    @BeforeEach
    public void initialise(){
        animation1 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        animation3 = new Animation<>(new Integer[]{4,5,6}, 3);

        Map<Direction, Animation<Integer>> map = new LinkedHashMap<>();
        map.put(Direction.UP, animation1);
        map.put(Direction.RIGHT, animation2);
        map.put(Direction.DOWN, animation3);

        repo = new DirectionAnimationRepo<>(map);
    }


    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        Map<Direction, Animation<Integer>> nullKeyMap = new HashMap<>();
        nullKeyMap.put(null, animation1);
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(nullKeyMap));
    }
    @Test
    public void testConstructorWithNullValue(){
        Map<Direction, Animation<Integer>> nullValueMap = new HashMap<>();
        nullValueMap.put(Direction.UP, null);
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(nullValueMap));
    }

    @Test
    public void testGetAnimation(){
        assertEquals(repo.getAnimation(Direction.UP), animation1);
        assertEquals(repo.getAnimation(Direction.RIGHT), animation2);
        assertEquals(repo.getAnimation(Direction.DOWN), animation3);
        assertThrows(NoSuchElementException.class, () -> repo.getAnimation(Direction.LEFT));
    }

    @Test
    public void testGetAnimationWithNull(){
        assertThrows(NullPointerException.class, () -> repo.getAnimation(null));
    }

}
