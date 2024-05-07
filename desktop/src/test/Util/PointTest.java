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
    public void testConstructorWithNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 10));
    }

    @Test
    public void testConstructorWithZeroX(){
        new Point(0, 10); // allowed
    }

    @Test
    public void testConstructorWithNegativeCoordinateY(){
        assertThrows(IllegalArgumentException.class, () -> new Point(10, -1));
    }

    @Test
    public void testConstructorWithZeroY(){
        new Point(5, 0); // allowed
    }

    @Test
    public void testX(){
        assertEquals(5, point.x());
    }

    @Test
    public void testY(){
        assertEquals(10, point.y());
    }

    @Test
    public void testEquals(){
        Point point1 = new Point(5, 10);
        Point point2 = new Point(5, 10);
        Point point3 = new Point(5, 10);
        Point diffX = new Point(5, 20);
        Point diffY = new Point(10, 10);
        // reflexive
        assertEquals(point1, point1);
        // symmetrical
        assertEquals(point1, point2);
        assertEquals(point2, point1);
        // transitive
        assertEquals(point1, point2);
        assertEquals(point2, point3);
        assertEquals(point1, point3);
        // not equals
        assertNotEquals(point1, diffX);
        assertNotEquals(point1, diffY);
        assertNotEquals(point1, new Object());
        assertNotEquals(point1, null);
    }

    @Test
    public void testHashCode(){
        Point point1 = new Point(5, 10);
        Point point2 = new Point(5, 10);
        Point point3 = new Point(5, 10);
        Point diffX = new Point(5, 20);
        Point diffY = new Point(10, 10);
        // reflexive
        assertEquals(point1.hashCode(), point1.hashCode());
        // symmetrical
        assertEquals(point1.hashCode(), point2.hashCode());
        assertEquals(point2.hashCode(), point1.hashCode());
        // transitive
        assertEquals(point1.hashCode(), point2.hashCode());
        assertEquals(point2.hashCode(), point3.hashCode());
        assertEquals(point1.hashCode(), point3.hashCode());
        // not equals
        assertNotEquals(point1.hashCode(), diffX.hashCode());
        assertNotEquals(point1.hashCode(), diffY.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Point[x=5, y=10]";
        assertEquals(expected, point.toString());
    }
}
