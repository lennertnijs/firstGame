package TextureSelector;

import com.mygdx.game.TextureSelector.FrameSelectionData;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.WeekSchedule.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameSelectionDataTest {

    private final List<Action> actionList = Arrays.asList(Action.IDLING, Action.WALKING);
    private final Direction direction = Direction.UP;
    private final double delta = 1000d;
    private FrameSelectionData selectionData;

    @BeforeEach
    public void initialise(){
        selectionData = new FrameSelectionData(actionList, direction, delta);
    }

    @Test
    public void testConstructorWithNullActionList(){
        assertThrows(NullPointerException.class,
                () -> new FrameSelectionData(null, direction, delta));
    }

    @Test
    public void testConstructorWithNullInActionList(){
        assertThrows(NullPointerException.class,
                () -> new FrameSelectionData(Arrays.asList(Action.MINING, null), direction, delta));
    }

    @Test
    public void testConstructorWithEmptyActionList(){
        assertThrows(IllegalArgumentException.class,
                () -> new FrameSelectionData(new ArrayList<>(), direction, delta));
    }

    @Test
    public void testConstructorWithNullDirection(){
        assertThrows(NullPointerException.class,
                () -> new FrameSelectionData(actionList, null, delta));
    }

    @Test
    public void testConstructorWithNegativeDelta(){
        assertThrows(IllegalArgumentException.class,
                () -> new FrameSelectionData(actionList, direction, -1));
    }

    @Test
    public void testGetActiveAction(){
        assertEquals(Action.WALKING, selectionData.getActiveAction());
    }

    @Test
    public void testAddAction(){
        selectionData.addAction(Action.RUNNING);
        assertEquals(Action.RUNNING, selectionData.getActiveAction());
    }

    @Test
    public void testAddActionWithNull(){
        assertThrows(NullPointerException.class,
                () -> selectionData.addAction(null));
    }

    @Test
    public void testAddActionDuplicate(){
        assertThrows(IllegalArgumentException.class,
                () -> selectionData.addAction(Action.WALKING));
    }

    @Test
    public void testRemoveAction(){
        selectionData.removeAction();
        assertEquals(Action.IDLING, selectionData.getActiveAction());
    }

    @Test
    public void testRemoveActionWithOnlyOneAction(){
        selectionData.removeAction();
        assertThrows(IllegalStateException.class,
                () -> selectionData.removeAction());
    }

    @Test
    public void testGetDirection(){
        assertEquals(Direction.UP, selectionData.getDirection());
    }

    @Test
    public void testSetDirection(){
        selectionData.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, selectionData.getDirection());
    }

    @Test
    public void testSetDirectionToNull(){
        assertThrows(NullPointerException.class,
                () -> selectionData.setDirection(null));
    }

    @Test
    public void testGetDelta(){
        assertEquals(1000d, selectionData.getDelta());
    }

    @Test
    public void testIncreaseDelta(){
        selectionData.increaseDelta(500d);
        assertEquals(1500d, selectionData.getDelta());
    }

    @Test
    public void testIncreaseDeltaWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> selectionData.increaseDelta(-1));
    }

    @Test
    public void testIncreaseDeltaWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> selectionData.increaseDelta(0));
    }
}
