package Entity;

import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PositionTest {

    @Test
    public void testConstructor(){
        Position point1 = Position.builder().x(120).y( 200).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(point1.getX(), 120),
                () -> Assertions.assertEquals(point1.getY(), 200)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Position.Builder builder = Position.builder().x(120).y(200);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> Position.builder().build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.x(-1).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.y(-1).build())
        );
    }

    @Test
    public void testEquals(){
        Position position1 = Position.builder().x(120).y(200).build();
        Position position2 = Position.builder().x(120).y(200).build();
        Position position3 = Position.builder().x(200).y(200).build();
        ArrayList<Position> list = new ArrayList<>();
        Assertions.assertAll(
                () -> Assertions.assertEquals(position1, position2),
                () -> Assertions.assertNotEquals(position1, position3),
                () -> Assertions.assertEquals(position2, position1),
                () -> Assertions.assertNotEquals(null, position1),
                () -> Assertions.assertNotEquals(position1, list),
                () -> Assertions.assertEquals(position1.hashCode(), position2.hashCode())
        );
    }

    @Test
    public void testToString(){
        Position position = Position.builder().x(120).y(200).build();
        String positionString = "Position(120, 200)";
        Assertions.assertAll(
                () -> Assertions.assertEquals(position.toString(), positionString)
        );
    }
}
