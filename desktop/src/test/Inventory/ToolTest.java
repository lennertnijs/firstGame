package Inventory;

import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTest {

    private final String name = "Stone";
    private final int efficiency = 15;
    private final int durability = 2000;
    private final ToolType type = ToolType.PICKAXE;
    private Tool tool;

    @BeforeEach
    public void initialise(){
        tool = new Tool(name, efficiency, durability, type);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Tool(null, efficiency, durability, type));
    }

    @Test
    public void testConstructorWithNegativeEfficiency(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tool(name, -1, durability, type));
    }

    @Test
    public void testConstructorWithZeroEfficiency(){
        new Tool(name, 0, durability, type); // allowed
    }

    @Test
    public void testConstructorWithNegativeDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tool(name, efficiency, -1, type));
    }

    @Test
    public void testConstructorWithZeroDurability(){
        new Tool(name, efficiency, 0, type); // allowed
    }

    @Test
    public void testConstructorWithNullType(){
        assertThrows(NullPointerException.class,
                () -> new Tool(name, efficiency, durability, null));
    }

    @Test
    public void testGetEfficiency(){
        assertEquals(15, tool.getEfficiency());
    }

    @Test
    public void testGetDurability(){
        assertEquals(2000, tool.getDurability());
    }

    @Test
    public void testGetToolType(){
        assertEquals(type, tool.getType());
    }

    @Test
    public void testSetDurability(){
        tool.setDurability(1000);
        assertEquals(1000, tool.getDurability());
        tool.setDurability(2000);
        assertEquals(2000, tool.getDurability());
    }

    @Test
    public void testSetDurabilityToNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> tool.setDurability(-1));
    }

    @Test
    public void testSetDurabilityToZero(){
        tool.setDurability(0);
        assertEquals(0, tool.getDurability());
    }

    @Test
    public void testDecrementDurability(){
        tool.decrementDurability();
        assertEquals(1999, tool.getDurability());
    }

    @Test
    public void testDecrementDurabilityWhenAtZero(){
        tool.setDurability(0);
        assertThrows(IllegalArgumentException.class,
                () -> tool.decrementDurability());
    }

    @Test
    public void testHasDurabilityLeft(){
        assertTrue(tool.hasDurabilityLeft());
        tool.setDurability(0);
        assertFalse(tool.hasDurabilityLeft());
        tool.setDurability(1);
        assertTrue(tool.hasDurabilityLeft());
    }
}
