package Inventory;

import com.mygdx.game.V2.Inventory.Pickaxe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PickaxeTest {

    private final String name = "Stone Pickaxe";
    private final int efficiency = 15;
    private final int durability = 2000;
    private Pickaxe pickaxe;

    @BeforeEach
    public void initialise(){
        pickaxe = new Pickaxe(name, efficiency, durability);
    }

    @Test
    public void testEquals(){
        Pickaxe pickaxe1 = new Pickaxe(name, efficiency, durability);
        Pickaxe pickaxe2 = new Pickaxe(name, efficiency, durability);
        Pickaxe pickaxe3 = new Pickaxe(name, efficiency, durability);
        Pickaxe diffPickaxe = new Pickaxe(name, efficiency, 5);
        // reflexive
        assertEquals(pickaxe1, pickaxe1);
        // symmetrical
        assertEquals(pickaxe1, pickaxe2);
        assertEquals(pickaxe2, pickaxe1);
        // transitive
        assertEquals(pickaxe1, pickaxe2);
        assertEquals(pickaxe2, pickaxe3);
        assertEquals(pickaxe1, pickaxe3);
        // not equals
        assertNotEquals(pickaxe1, diffPickaxe);
        assertNotEquals(pickaxe1, new Object());
        assertNotEquals(pickaxe1, null);
    }

    @Test
    public void testHashCode(){
        Pickaxe pickaxe1 = new Pickaxe(name, efficiency, durability);
        Pickaxe pickaxe2 = new Pickaxe(name, efficiency, durability);
        Pickaxe pickaxe3 = new Pickaxe(name, efficiency, durability);
        Pickaxe diffPickaxe = new Pickaxe(name, efficiency, 5);
        // reflexive
        assertEquals(pickaxe1.hashCode(), pickaxe1.hashCode());
        // symmetrical
        assertEquals(pickaxe1.hashCode(), pickaxe2.hashCode());
        assertEquals(pickaxe2.hashCode(), pickaxe1.hashCode());
        // transitive
        assertEquals(pickaxe1.hashCode(), pickaxe2.hashCode());
        assertEquals(pickaxe2.hashCode(), pickaxe3.hashCode());
        assertEquals(pickaxe1.hashCode(), pickaxe3.hashCode());
        // not equals
        assertNotEquals(pickaxe1.hashCode(), diffPickaxe.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Pickaxe[amount=1, efficiency=15, durability=2000]";
        assertEquals(expected, pickaxe.toString());
    }
}
