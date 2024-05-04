package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import com.mygdx.game.V2.Inventory.Pickaxe;
import com.mygdx.game.V2.Inventory.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PickaxeTest {

    private ItemTemplate template;
    private int efficiency;
    private int durability;
    private Pickaxe pickaxe;

    @BeforeEach
    public void initialise(){
        template = new ItemTemplate("Stone", "A Stone.", Mockito.mock(Texture.class), 64);
        efficiency = 15;
        durability = 2000;

        pickaxe = new Pickaxe(template, efficiency, durability);
    }

    @Test
    public void testConstructorWithNullTemplate(){
        assertThrows(NullPointerException.class,
                () -> new Pickaxe(null, efficiency, durability));
    }

    @Test
    public void testConstructorWithNegativeEfficiency(){
        assertThrows(IllegalArgumentException.class,
                () -> new Pickaxe(template, -1, durability));
    }

    @Test
    public void testConstructorWithZeroEfficiency(){
        new Pickaxe(template, 0, durability);
    }

    @Test
    public void testConstructorWithNegativeDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> new Pickaxe(template, efficiency, -1));
    }

    @Test
    public void testConstructorWithZeroDurability(){
        new Pickaxe(template, efficiency, 0);
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

    @Test
    public void testUse(){
        // to be done
    }

    @Test
    public void testEquals(){
        Pickaxe pickaxe1 = new Pickaxe(template, efficiency, durability);
        Pickaxe pickaxe2 = new Pickaxe(template, efficiency, durability);
        Pickaxe pickaxe3 = new Pickaxe(template, efficiency, durability);
        Pickaxe diffPickaxe = new Pickaxe(template, efficiency, 5);
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
        Pickaxe pickaxe1 = new Pickaxe(template, efficiency, durability);
        Pickaxe pickaxe2 = new Pickaxe(template, efficiency, durability);
        Pickaxe pickaxe3 = new Pickaxe(template, efficiency, durability);
        Pickaxe diffPickaxe = new Pickaxe(template, efficiency, 5);
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
