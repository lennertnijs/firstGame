package V2;

import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point point1;
    private Point point2;
    private Point point3;

    @BeforeEach
    public void initialise(){
        point1 = new Point(0,0);
        point2 = new Point(5,5);
        point3 = new Point(0,0);
    }

    @Test
    public void testConstructor(){
        Point point = new Point(10, 6);
        assertEquals(point.getX(), 10);
        assertEquals(point.getY(), 6);
    }

    @Test
    public void testConstructorIllegal(){
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Point(0, -1));
    }

    @Test
    public void testEqualsAndHashCode(){
        assertEquals(point1, point3);
        assertNotEquals(point1, point2);
        assertNotEquals(point1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(point1.hashCode(), point3.hashCode());
        assertNotEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Point[x=0, y=0]";
        assertEquals(point1.toString(), expectedString);
    }
}
