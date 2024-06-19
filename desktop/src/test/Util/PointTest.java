package Util;

import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point point;

    @BeforeEach
    public void initialise(){
        point = new Point(5,10);
    }

    @Test
    public void testGetX(){
        assertEquals(5, point.x());
    }

    @Test
    public void testGetY(){
        assertEquals(10, point.y());
    }

    @Test
    public void testEquals(){
        Point p1 = new Point(5, 10);
        Point p2 = new Point(5, 10);
        Point p3 = new Point(5, 10);

        // reflexive
        assertEquals(p1, p1);
        // symmetrical
        assertEquals(p1, p2);
        assertEquals(p2, p1);
        // transitive
        assertEquals(p1, p2);
        assertEquals(p2, p3);
        assertEquals(p1, p3);

        // not equals
        Point differentX = new Point(15, 10);
        Point differentY = new Point(5, 20);
        assertNotEquals(p1, differentX);
        assertNotEquals(p1, differentY);
        assertNotEquals(p1, new Object());
        assertNotEquals(p1, null);
    }

    @Test
    public void testHashCode(){
        Point p1 = new Point(5, 10);
        Point p2 = new Point(5, 10);
        Point p3 = new Point(5, 10);

        // reflexive
        assertEquals(p1.hashCode(), p1.hashCode());
        // symmetrical
        assertEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p2.hashCode(), p1.hashCode());
        // transitive
        assertEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p2.hashCode(), p3.hashCode());
        assertEquals(p1.hashCode(), p3.hashCode());

        // not equals
        Point differentX = new Point(15, 10);
        Point differentY = new Point(5, 20);
        assertNotEquals(p1.hashCode(), differentX.hashCode());
        assertNotEquals(p1.hashCode(), differentY.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "(5, 10)";
        assertEquals(expected, point.toString());
    }
}
