package Inventory;

import com.mygdx.game.V2.Inventory.Pickaxe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTest {

    private final String name = "Stone";
    private final int efficiency = 15;
    private final int durability = 2000;
    private Pickaxe pickaxe;

    @BeforeEach
    public void initialise(){
        pickaxe = new Pickaxe(name, efficiency, durability);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Pickaxe(null, efficiency, durability));
    }

    @Test
    public void testConstructorWithNegativeEfficiency(){
        assertThrows(IllegalArgumentException.class,
                () -> new Pickaxe(name, -1, durability));
    }

    @Test
    public void testConstructorWithZeroEfficiency(){
        new Pickaxe(name, 0, durability); // allowed
    }

    @Test
    public void testConstructorWithNegativeDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Pickaxe(name, efficiency, -1));
    }

    @Test
    public void testConstructorWithZeroDurability(){
        new Pickaxe(name, efficiency, 0); // allowed
    }

    @Test
    public void testGetEfficiency(){
        assertEquals(15, pickaxe.getEfficiency());
    }

    @Test
    public void testGetDurability(){
        assertEquals(2000, pickaxe.getDurability());
    }

    @Test
    public void testSetDurability(){
        pickaxe.setDurability(1000);
        assertEquals(1000, pickaxe.getDurability());
        pickaxe.setDurability(2000);
        assertEquals(2000, pickaxe.getDurability());
    }

    @Test
    public void testSetDurabilityToNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> pickaxe.setDurability(-1));
    }

    @Test
    public void testSetDurabilityToZero(){
        pickaxe.setDurability(0);
        assertEquals(0, pickaxe.getDurability());
    }

    @Test
    public void testDecrementDurability(){
        pickaxe.decrementDurability();
        assertEquals(1999, pickaxe.getDurability());
    }

    @Test
    public void testDecrementDurabilityWhenAtZero(){
        pickaxe.setDurability(0);
        assertThrows(IllegalArgumentException.class,
                () -> pickaxe.decrementDurability());
    }

    @Test
    public void testHasDurabilityLeft(){
        assertTrue(pickaxe.hasDurabilityLeft());
        pickaxe.setDurability(0);
        assertFalse(pickaxe.hasDurabilityLeft());
        pickaxe.setDurability(1);
        assertTrue(pickaxe.hasDurabilityLeft());
    }
}
