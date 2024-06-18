package Util;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.UtilMethods.UtilMethods;
import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilMethodsTest {

    private final Point start = new Point(15, 15);
    private final Point endTop = new Point(15, 25);
    private final Point endTopRight = new Point(25, 25);
    private final Point endRight = new Point(25, 15);
    private final Point endBottomRight = new Point(25, 5);
    private final Point endBottom = new Point(15, 5);
    private final Point endBottomLeft = new Point(5, 5);
    private final Point endLeft = new Point(5, 15);
    private final Point endTopLeft = new Point(5, 25);

    @Test
    public void testCalculateNextPositionStraightUp(){
        Point result = UtilMethods.calculateNextPosition(start, endTop, 10);
        Point expected = new Point(15, 15 + 10);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionUpRight(){
        Point result = UtilMethods.calculateNextPosition(start, endTopRight, 10);
        Point expected = new Point(15 + 7, 15 + 7);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionStraightRight(){
        Point result = UtilMethods.calculateNextPosition(start, endRight, 10);
        Point expected = new Point(15 + 10, 15);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionBottomRight(){
        Point result = UtilMethods.calculateNextPosition(start, endBottomRight, 10);
        Point expected = new Point(15 + 7, 15 - 7);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionStraightBottom(){
        Point result = UtilMethods.calculateNextPosition(start, endBottom, 10);
        Point expected = new Point(15, 15 - 10);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionBottomLeft(){
        Point result = UtilMethods.calculateNextPosition(start, endBottomLeft, 10);
        Point expected = new Point(15 - 7, 15 - 7);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionStraightLeft(){
        Point result = UtilMethods.calculateNextPosition(start, endLeft, 10);
        Point expected = new Point(15 - 10, 15);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionTopLeft(){
        Point result = UtilMethods.calculateNextPosition(start, endTopLeft, 10);
        Point expected = new Point(15 - 7, 15 + 7);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculateNextPositionSameStartAndEnd(){
        Point result = UtilMethods.calculateNextPosition(start, start, 10);
        assertEquals(start, result);
    }

    @Test
    public void testCalculateNextPositionGoesOverGoal(){
        Point result = UtilMethods.calculateNextPosition(start, endTopRight, 1000);
        assertEquals(endTopRight, result);
    }

    @Test
    public void testCalculateNextPositionWithNullStart(){
        assertThrows(NullPointerException.class,
                () -> UtilMethods.calculateNextPosition(null, endTopRight, 10));
    }

    @Test
    public void testCalculateNextPositionWithNullEnd(){
        assertThrows(NullPointerException.class,
                () -> UtilMethods.calculateNextPosition(start, Direction.valueOf(null), 10));
    }

    @Test
    public void testCalculateNextPositionWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> UtilMethods.calculateNextPosition(start, endTopRight, -1));
    }

    @Test
    public void testCalculateNextPositionWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> UtilMethods.calculateNextPosition(start, endTopRight, 0));
    }
}
