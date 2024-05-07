package Util;

import com.mygdx.game.Util.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    private Vector vector;

    @BeforeEach
    public void initialise(){
        vector = new Vector(-5, 10);
    }

    @Test
    public void testX(){
        assertEquals(-5, vector.x());
    }

    @Test
    public void testY(){
        assertEquals(10, vector.y());
    }

    @Test
    public void testSize(){
        assertEquals(11, vector.size());
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
        Vector diffVector = new Vector(15, 10);
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
        assertNotEquals(v1, diffVector);
        assertNotEquals(v1, new Object());
        assertNotEquals(v1, null);
    }

    @Test
    public void testHashCode(){
        Vector v1 = new Vector(5, 10);
        Vector v2 = new Vector(5, 10);
        Vector v3 = new Vector(5, 10);
        Vector diffVector = new Vector(15, 10);
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
        assertNotEquals(v1.hashCode(), diffVector.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Vector[x=-5, y=10]";
        assertEquals(expected, vector.toString());
    }
}
