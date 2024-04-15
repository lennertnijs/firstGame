package V2;

import com.mygdx.game.V2.Util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    private Position position1;
    private Position position2;
    private Position position3;

    @BeforeEach
    public void initialise(){
        position1 = Position.create(0,0);
        position2 = Position.create(5,5);
        position3 = Position.create(0,0);
    }

    @Test
    public void testConstructor(){
        Position position = Position.create(10, 6);
        assertEquals(position.getX(), 10);
        assertEquals(position.getY(), 6);
    }

    @Test
    public void testConstructorIllegal(){
        assertThrows(IllegalArgumentException.class, () -> Position.create(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> Position.create(0, -1));
    }

    @Test
    public void testEqualsAndHashCode(){
        assertEquals(position1, position3);
        assertNotEquals(position1, position2);
        assertNotEquals(position1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(position1.hashCode(), position3.hashCode());
        assertNotEquals(position1.hashCode(), position2.hashCode());
    }

    @Test
    public void testToString(){
        String expectedString = "Position[x=0, y=0]";
        assertEquals(position1.toString(), expectedString);
    }
}
