package Util;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    private final Vector vector = new Vector(-5, 10);

    @Test
    public void testGetX(){
        assertEquals(-5, vector.x());
    }

    @Test
    public void testGetY(){
        assertEquals(10, vector.y());
    }

    @Test
    public void testSize(){
        assertEquals(11, vector.size());
    }

    @Test
    public void testCreateBetweenPoints(){
        Point start = new Point(5, 15);
        Point goal = new Point(10, 10);
        Vector v1 = Vector.between(start, goal);
        assertEquals(new Vector(5, -5), v1);
        Vector v2 = Vector.between(goal, start);
        assertEquals(new Vector(-5, 5), v2);
    }

    @Test
    public void testCreateBetweenPointsWithNullStart(){
        Point goal = new Point(10, 10);
        assertThrows(NullPointerException.class,
                () -> Vector.between(null, goal));
    }

    @Test
    public void testCreateBetweenPointsWithNullGoal(){
        Point start = new Point(10, 10);
        assertThrows(NullPointerException.class,
                () -> Vector.between(start, null));
    }

    @Test
    public void testScale(){
        Vector v1 = vector.scale(0.4f);
        assertEquals(v1 , new Vector(-2, 4));
        Vector v2 =  v1.scale(2);
        assertEquals(v2, new Vector(-4, 8));
        Vector v3 = v2.scale(-2);
        assertEquals(v3, new Vector(8, -16));
    }

    @Test
    public void testScaleToSize(){
        Vector v1 = vector.scaleToSize(6);
        assertEquals(v1, new Vector(-3, 5));
        Vector zero = vector.scaleToSize(0);
        assertEquals(zero, new Vector(0, 0));
    }

    @Test
    public void testScaleToSizeWithSizeZero(){
        Vector zero = new Vector(0, 0);
        assertThrows(IllegalStateException.class,
                () -> zero.scaleToSize(10));
    }

    @Test
    public void testEquals(){
        Vector v1 = new Vector(5, 10);
        Vector v2 = new Vector(5, 10);
        Vector v3 = new Vector(5, 10);
        Vector diffX = new Vector(15, 10);
        Vector diffY = new Vector(5, 20);
        // reflexive
        assertEquals(v1, v1);
        // symmetrical
        assertEquals(v1, v2);
        assertEquals(v2, v1);
        // transitive
        assertEquals(v1, v2);
        assertEquals(v2, v3);
        assertEquals(v1, v3);
        // not equals
        assertNotEquals(v1, diffX);
        assertNotEquals(v1, diffY);
        assertNotEquals(v1, new Object());
        assertNotEquals(v1, null);
    }

    @Test
    public void testHashCode(){
        Vector v1 = new Vector(5, 10);
        Vector v2 = new Vector(5, 10);
        Vector v3 = new Vector(5, 10);
        Vector diffX = new Vector(15, 10);
        Vector diffY = new Vector(5, 20);
        // reflexive
        assertEquals(v1.hashCode(), v1.hashCode());
        // symmetrical
        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v2.hashCode(), v1.hashCode());
        // transitive
        assertEquals(v1.hashCode(), v2.hashCode());
        assertEquals(v2.hashCode(), v3.hashCode());
        assertEquals(v1.hashCode(), v3.hashCode());
        // not equals
        assertNotEquals(v1.hashCode(), diffX.hashCode());
        assertNotEquals(v1.hashCode(), diffY.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Vector[x=-5, y=10]";
        assertEquals(expected, vector.toString());
    }
}
