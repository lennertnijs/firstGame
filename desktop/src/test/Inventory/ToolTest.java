package Inventory;

import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTest {

    private final String name = "Stone";
    private final int efficiency = 15;
    private final int maxDurability = 2500;
    private final int durability = 2000;
    private final ToolType type = ToolType.PICKAXE;
    private Tool tool;

    @BeforeEach
    public void initialise(){
        tool = new Tool(name, efficiency, maxDurability, durability, type);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Tool(null, efficiency, maxDurability, durability, type));
    }

    @Test
    public void testConstructorWithNegativeEfficiency(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tool(name, -1, maxDurability, durability, type));
    }

    @Test
    public void testConstructorWithZeroEfficiency(){
        new Tool(name, 0, maxDurability, durability, type); // allowed
    }

    @Test
    public void testConstructorWithNegativeDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Tool(name, efficiency, maxDurability, -1, type));
    }

    @Test
    public void testConstructorWithZeroDurability(){
        new Tool(name, efficiency, maxDurability,0, type); // allowed
    }

    @Test
    public void testConstructorWithNullType(){
        assertThrows(NullPointerException.class,
                () -> new Tool(name, efficiency, maxDurability, durability, null));
    }

    @Test
    public void testGetEfficiency(){
        assertEquals(15, tool.efficiency());
    }

    @Test
    public void testGetDurability(){
        assertEquals(2000, tool.getDurability());
    }

    @Test
    public void testGetToolType(){
        assertEquals(type, tool.type());
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
}
