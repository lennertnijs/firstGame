package Inventory;

import com.mygdx.game.V2.Inventory.Shovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShovelTest {

    private final String name = "Stone Shovel";
    private final int efficiency = 15;
    private final int durability = 2000;
    private Shovel shovel;

    @BeforeEach
    public void initialise(){
        shovel = new Shovel(name, efficiency, durability);
    }

    @Test
    public void testEquals(){
        Shovel shovel1 = new Shovel(name, efficiency, durability);
        Shovel shovel2 = new Shovel(name, efficiency, durability);
        Shovel shovel3 = new Shovel(name, efficiency, durability);
        Shovel diffShovel = new Shovel(name, efficiency, 5);
        // reflexive
        assertEquals(shovel1, shovel1);
        // symmetrical
        assertEquals(shovel1, shovel2);
        assertEquals(shovel2, shovel1);
        // transitive
        assertEquals(shovel1, shovel2);
        assertEquals(shovel2, shovel3);
        assertEquals(shovel1, shovel3);
        // not equals
        assertNotEquals(shovel1, diffShovel);
        assertNotEquals(shovel1, new Object());
        assertNotEquals(shovel1, null);
    }

    @Test
    public void testHashCode(){
        Shovel shovel1 = new Shovel(name, efficiency, durability);
        Shovel shovel2 = new Shovel(name, efficiency, durability);
        Shovel shovel3 = new Shovel(name, efficiency, durability);
        Shovel diffShovel = new Shovel(name, efficiency, 5);
        // reflexive
        assertEquals(shovel1.hashCode(), shovel1.hashCode());
        // symmetrical
        assertEquals(shovel1.hashCode(), shovel2.hashCode());
        assertEquals(shovel2.hashCode(), shovel1.hashCode());
        // transitive
        assertEquals(shovel1.hashCode(), shovel2.hashCode());
        assertEquals(shovel2.hashCode(), shovel3.hashCode());
        assertEquals(shovel1.hashCode(), shovel3.hashCode());
        // not equals
        assertNotEquals(shovel1.hashCode(), diffShovel.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Shovel[amount=1, efficiency=15, durability=2000]";
        assertEquals(expected, shovel.toString());
    }
}
