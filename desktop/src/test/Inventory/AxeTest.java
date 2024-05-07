package Inventory;

import com.mygdx.game.V2.Inventory.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AxeTest {

    private final String name = "Stone Axe";
    private final int efficiency = 15;
    private final int durability = 2000;
    private Axe axe;

    @BeforeEach
    public void initialise(){
        axe = new Axe(name, efficiency, durability);
    }

    @Test
    public void testEquals(){
        Axe axe1 = new Axe(name, efficiency, durability);
        Axe axe2 = new Axe(name, efficiency, durability);
        Axe axe3 = new Axe(name, efficiency, durability);
        Axe diffAxe = new Axe(name, efficiency, 5);
        // reflexive
        assertEquals(axe1, axe1);
        // symmetrical
        assertEquals(axe1, axe2);
        assertEquals(axe2, axe1);
        // transitive
        assertEquals(axe1, axe2);
        assertEquals(axe2, axe3);
        assertEquals(axe1, axe3);
        // not equals
        assertNotEquals(axe1, diffAxe);
        assertNotEquals(axe1, new Object());
        assertNotEquals(axe1, null);
    }

    @Test
    public void testHashCode(){
        Axe axe1 = new Axe(name, efficiency, durability);
        Axe axe2 = new Axe(name, efficiency, durability);
        Axe axe3 = new Axe(name, efficiency, durability);
        Axe diffAxe = new Axe(name, efficiency, 5);
        // reflexive
        assertEquals(axe1.hashCode(), axe1.hashCode());
        // symmetrical
        assertEquals(axe1.hashCode(), axe2.hashCode());
        assertEquals(axe2.hashCode(), axe1.hashCode());
        // transitive
        assertEquals(axe1.hashCode(), axe2.hashCode());
        assertEquals(axe2.hashCode(), axe3.hashCode());
        assertEquals(axe1.hashCode(), axe3.hashCode());
        // not equals
        assertNotEquals(axe1.hashCode(), diffAxe.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Axe[amount=1, efficiency=15, durability=2000]";
        assertEquals(expected, axe.toString());
    }

}
