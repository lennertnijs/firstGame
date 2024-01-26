package Entity;

import com.mygdx.game.Entity.Position2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Position2DTest {

    @Test
    public void testPosition2D(){
        Position2D point1 = new Position2D(120, 200);
        Position2D point2 = new Position2D(200, 120);
        Position2D point3 = new Position2D(120, 200);
        Assertions.assertEquals(point1.getX(), 120);
        Assertions.assertEquals(point1.getY(), 200);
        Assertions.assertEquals(point1, point3);
        Assertions.assertEquals(point1, point3);
        Assertions.assertNotEquals(point1, point2);

        point1.setX(500);
        Assertions.assertEquals(point1.getX(), 500);
        point1.setY(50);
        Assertions.assertEquals(point1.getY(), 50);
    }
}
