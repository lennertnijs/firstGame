package Util;

import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Rectangle;
import com.mygdx.game.Util.Point;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Rect;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private final Point position = new Point(0, 0);
    private final Dimensions dimensions = new Dimensions(10, 10);
    private final Rectangle rectangle = new Rectangle(position, dimensions);

    @Test
    public void testConstructorWithNullPosition(){
        assertThrows(NullPointerException.class,
                () -> new Rectangle(null, dimensions));
    }

    @Test
    public void testConstructorWithNullDimensions(){
        assertThrows(NullPointerException.class,
                () -> new Rectangle(position, null));
    }

    @Test
    public void testGetX(){
        assertEquals(0, rectangle.x());
    }

    @Test
    public void testGetY(){
        assertEquals(0, rectangle.y());
    }

    @Test
    public void testGetWidth(){
        assertEquals(10, rectangle.width());
    }

    @Test
    public void testGetHeight(){
        assertEquals(10, rectangle.height());
    }

    @Test
    public void testOverlapsVerticallyInAndOutside(){
        Rectangle r = new Rectangle(new Point(5, 5), new Dimensions(2, 10));
        assertTrue(rectangle.overlaps(r));
        assertTrue(r.overlaps(rectangle));
    }

    @Test
    public void testOverlapsHorizontallyInAndOutside(){
        Rectangle r = new Rectangle(new Point(5, 5), new Dimensions(10, 2));
        assertTrue(rectangle.overlaps(r));
        assertTrue(r.overlaps(rectangle));
    }

    @Test
    public void testOverlapsFullyInside(){
        Rectangle r = new Rectangle(new Point(5, 5), new Dimensions(2, 2));
        assertTrue(rectangle.overlaps(r));
        assertTrue(r.overlaps(rectangle));
    }

    @Test
    public void testOverlapsAround(){
        Rectangle r = new Rectangle(new Point(0, 0), new Dimensions(20, 20));
        assertTrue(rectangle.overlaps(r));
        assertTrue(r.overlaps(rectangle));
    }

    @Test
    public void testNotOverlapsVertically(){
        Rectangle r = new Rectangle(new Point(0, 10), dimensions);
        assertFalse(r.overlaps(rectangle));
        assertFalse(rectangle.overlaps(r));
    }

    @Test
    public void testNotOverlapsHorizontally(){
        Rectangle r = new Rectangle(new Point(10, 0), dimensions);
        assertFalse(r.overlaps(rectangle));
        assertFalse(rectangle.overlaps(r));
    }

    @Test
    public void testOverlapsWithNull(){
        assertThrows(NullPointerException.class,
                () -> rectangle.overlaps(null));
    }

    @Test
    public void testContains(){
        Point bottomLeft = new Point(0, 0);
        Point bottomRight = new Point(9, 0);
        Point topLeft = new Point(0, 9);
        Point topRight = new Point(9, 9);
        assertTrue(rectangle.contains(bottomLeft));
        assertTrue(rectangle.contains(bottomRight));
        assertTrue(rectangle.contains(topLeft));
        assertTrue(rectangle.contains(topRight));
    }

    @Test
    public void testNotContains(){
        Point bottomRight = new Point(10, 0);
        Point topLeft = new Point(0, 10);
        Point topRight = new Point(10, 10);
        assertFalse(rectangle.contains(bottomRight));
        assertFalse(rectangle.contains(topLeft));
        assertFalse(rectangle.contains(topRight));
    }

    @Test
    public void testContainsWithNull(){
        assertThrows(NullPointerException.class,
                () -> rectangle.contains(null));
    }

    @Test
    public void testEquals(){
        Rectangle r1 = new Rectangle(position, dimensions);
        Rectangle r2 = new Rectangle(position, dimensions);
        Rectangle r3 = new Rectangle(position, dimensions);
        // reflexive
        assertEquals(r1, r1);
        // symmetrical
        assertEquals(r1, r2);
        assertEquals(r2, r1);
        // transitive
        assertEquals(r1, r2);
        assertEquals(r2, r3);
        assertEquals(r1, r3);

        // not equals
        Rectangle diffX = new Rectangle(new Point(5, 0), dimensions);
        Rectangle diffY = new Rectangle(new Point(0, 5), dimensions);
        Rectangle diffWidth = new Rectangle(position, new Dimensions(5, 10));
        Rectangle diffHeight = new Rectangle(position, new Dimensions(10, 5));
        assertNotEquals(r1, diffX);
        assertNotEquals(r1, diffY);
        assertNotEquals(r1, diffWidth);
        assertNotEquals(r1, diffHeight);
        assertNotEquals(r1, new Object());
        assertNotEquals(r1, null);
    }

    @Test
    public void testHashCode(){
        Rectangle r1 = new Rectangle(position, dimensions);
        Rectangle r2 = new Rectangle(position, dimensions);
        Rectangle r3 = new Rectangle(position, dimensions);
        // reflexive
        assertEquals(r1.hashCode(), r1.hashCode());
        // symmetrical
        assertEquals(r1.hashCode(), r2.hashCode());
        assertEquals(r2.hashCode(), r1.hashCode());
        // transitive
        assertEquals(r1.hashCode(), r2.hashCode());
        assertEquals(r2.hashCode(), r3.hashCode());
        assertEquals(r1.hashCode(), r3.hashCode());

        // not equals
        Rectangle diffX = new Rectangle(new Point(5, 0), dimensions);
        Rectangle diffY = new Rectangle(new Point(0, 5), dimensions);
        Rectangle diffWidth = new Rectangle(position, new Dimensions(5, 10));
        Rectangle diffHeight = new Rectangle(position, new Dimensions(10, 5));
        assertNotEquals(r1.hashCode(), diffX.hashCode());
        assertNotEquals(r1.hashCode(), diffY.hashCode());
        assertNotEquals(r1.hashCode(), diffWidth.hashCode());
        assertNotEquals(r1.hashCode(), diffHeight.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Rectangle[x=0, y=0, width=10, height=10]";
        assertEquals(expected, rectangle.toString());
    }
}
