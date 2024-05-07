package Util;

import com.mygdx.game.Util.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DimensionsTest {

    private Dimensions dimensions;

    @BeforeEach
    public void initialise(){
        dimensions = new Dimensions(5, 15);
    }

    @Test
    public void testConstructorWithNegativeWidth(){
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(-1, 15));
    }

    @Test
    public void testConstructorWithNegativeHeight(){
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(5, -1));
    }

    @Test
    public void testWidth(){
        assertEquals(5, dimensions.width());
    }

    @Test
    public void testHeight(){
        assertEquals(15, dimensions.height());
    }

    @Test
    public void testEquals(){
        Dimensions dim1 = new Dimensions(5, 15);
        Dimensions dim2 = new Dimensions(5, 15);
        Dimensions dim3 = new Dimensions(5, 15);
        Dimensions diffDim = new Dimensions(2, 15);
        // reflexive
        assertEquals(dim1, dim1);
        // symmetrical
        assertEquals(dim1, dim2);
        assertEquals(dim2, dim1);
        // transitive
        assertEquals(dim1, dim2);
        assertEquals(dim2, dim3);
        assertEquals(dim1, dim3);
        // not equals
        assertNotEquals(dim1, diffDim);
        assertNotEquals(dim1, new Object());
        assertNotEquals(dim1, null);
    }

    @Test
    public void testHashCode(){
        Dimensions dim1 = new Dimensions(5, 15);
        Dimensions dim2 = new Dimensions(5, 15);
        Dimensions dim3 = new Dimensions(5, 15);
        Dimensions diffDim = new Dimensions(2, 15);
        // reflexive
        assertEquals(dim1.hashCode(), dim1.hashCode());
        // symmetrical
        assertEquals(dim1.hashCode(), dim2.hashCode());
        assertEquals(dim2.hashCode(), dim1.hashCode());
        // transitive
        assertEquals(dim1.hashCode(), dim2.hashCode());
        assertEquals(dim2.hashCode(), dim3.hashCode());
        assertEquals(dim1.hashCode(), dim3.hashCode());
        // not equals
        assertNotEquals(dim1.hashCode(), diffDim.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Dimensions[width=5, height=15]";
        assertEquals(expected, dimensions.toString());
    }
}
