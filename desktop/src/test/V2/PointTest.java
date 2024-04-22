package V2;

import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point point;

    @BeforeEach
    public void initialise(){
        point = new Point(0,10);
    }

    @Test
    public void testConstructorWithNegativeCoordinate(){
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new Point(10, -1));
    }

    @Test
    public void testX(){
        assertEquals(0, point.x());
    }

    @Test
    public void testY(){
        assertEquals(10, point.y());
    }

    @Test
    public void testEquals(){
        Point point1 = new Point(0, 10);
        Point point2 = new Point(0, 10);
        Point point3 = new Point(0, 10);
        Point diffPoint = new Point(0, 20);
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
        assertNotEquals(point1, diffPoint);
        assertNotEquals(point1, new Object());
        assertNotEquals(point1, null);
    }

    @Test
    public void testHashCode(){
        Point point1 = new Point(0, 10);
        Point point2 = new Point(0, 10);
        Point point3 = new Point(0, 10);
        Point diffPoint = new Point(0, 20);
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
        assertNotEquals(point1.hashCode(), diffPoint.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Point[x=0, y=10]";
        assertEquals(expected, point.toString());
    }
}
