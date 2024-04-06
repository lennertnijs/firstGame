package V2;

import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.TextureSelector.Animation;
import com.mygdx.game.V2.TextureSelector.DirectionAnimationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectionAnimationRepoTest {

    private Animation<Integer> animation1;
    private Animation<Integer> animation2;
    private Animation<Integer> animation3;
    private Map<Direction, Animation<Integer>> map1;
    private DirectionAnimationRepo<Integer> repo1;
    private DirectionAnimationRepo<Integer> repo2;
    private DirectionAnimationRepo<Integer> repo3;

    @BeforeEach
    public void initialise(){
        animation1 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);
        animation2 = new Animation<>(new Integer[]{1,2,3}, 3);
        animation3 = new Animation<>(new Integer[]{1,2,3,4,5,6}, 3);

        map1 = new HashMap<Direction, Animation<Integer>>(){{
            put(Direction.UP, animation1);
            put(Direction.RIGHT, animation2);
            put(Direction.DOWN, animation3);
        }};

        Map<Direction, Animation<Integer>> map2 = new HashMap<Direction, Animation<Integer>>(){{
                put(Direction.UP, animation1);
        }};

        Map<Direction, Animation<Integer>> map3 = new HashMap<Direction, Animation<Integer>>(){{
            put(Direction.RIGHT, animation2);
            put(Direction.UP, animation1);
            put(Direction.DOWN, animation3);
        }};


        repo1 = new DirectionAnimationRepo<>(map1);
        repo2 = new DirectionAnimationRepo<>(map2);
        repo3 = new DirectionAnimationRepo<>(map3);
    }

    @Test
    public void testConstructor(){
        assertEquals(repo1.getMapping(), map1);
    }

    @Test
    public void testConstructorInvalid(){
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(null));
        Map<Direction, Animation<Integer>> map1 = new HashMap<Direction, Animation<Integer>>(){{
            put(null, animation1);
        }};
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(map1));
        Map<Direction, Animation<Integer>> map2 = new HashMap<Direction, Animation<Integer>>(){{
            put(Direction.UP, null);
        }};
        assertThrows(NullPointerException.class, () -> new DirectionAnimationRepo<>(map2));
    }

    @Test
    public void testGetAnimation(){
        assertEquals(repo1.getAnimation(Direction.UP), animation1);
        assertEquals(repo1.getAnimation(Direction.DOWN), animation3);
        assertThrows(NoSuchElementException.class, () -> repo1.getAnimation(Direction.LEFT));
    }

    @Test
    public void testGetAnimationWithNull(){
        assertThrows(NullPointerException.class, () -> repo1.getAnimation(null));
    }
}
