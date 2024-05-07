package Inventory;

import com.mygdx.game.V2.Inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private final String name = "Stone";
    private final int stackSize = 64;
    private final int amount = 32;
    private Item item;

    @BeforeEach
    public void initialise(){
        item = new Item(name, stackSize, amount);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Item(null, stackSize, amount));
    }

    @Test
    public void testConstructorWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, -1, amount));
    }

    @Test
    public void testConstructorWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, 0, amount));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, stackSize, -1));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, stackSize, 0));
    }

    @Test
    public void testConstructorWithAmountTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, stackSize, 64 + 1));
    }

    @Test
    public void testGetName(){
        assertEquals(name, item.getName());
    }

    @Test
    public void testGetStackSize(){
        assertEquals(stackSize, item.getStackSize());
    }

    @Test
    public void testGetAmount(){
        assertEquals(amount, item.getAmount());
    }

    @Test
    public void testIncreaseAmount(){
        item.increaseAmount(30);
        assertEquals(62, item.getAmount());
        int increaseLeft = item.increaseAmount(30);
        assertEquals(64, item.getAmount());
        assertEquals(30 - 2, increaseLeft);
        increaseLeft = item.increaseAmount(30);
        assertEquals(64, item.getAmount());
        assertEquals(30, increaseLeft);
    }

    @Test
    public void testIncreaseAmountWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(-1));
    }

    @Test
    public void testIncreaseAmountWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(0));
    }

    @Test
    public void testDecreaseAmount(){
        item.decreaseAmount(30);
        assertEquals(32 - 30, item.getAmount());
        int decreaseLeft = item.decreaseAmount(30);
        assertEquals(0, item.getAmount());
        assertEquals(30 - 2, decreaseLeft);
        decreaseLeft = item.decreaseAmount(30);
        assertEquals(0, item.getAmount());
        assertEquals(30, decreaseLeft);
    }

    @Test
    public void testDecreaseAmountWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> item.decreaseAmount(-1));
    }

    @Test
    public void testDecreaseAmountWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> item.decreaseAmount(0));
    }

    @Test
    public void testAmountIsZero(){
        assertFalse(item.amountIsZero());
        item.decreaseAmount(32);
        assertTrue(item.amountIsZero());
        item.increaseAmount(1);
        assertFalse(item.amountIsZero());
    }

    @Test
    public void testEquals(){
        Item item1 = new Item(name, stackSize, amount);
        Item item2 = new Item(name, stackSize, amount);
        Item item3 = new Item(name, stackSize, amount);
        Item diffItem = new Item(name, stackSize, amount/2);
        // reflexive
        assertEquals(item1, item1);
        // symmetrical
        assertEquals(item1, item2);
        assertEquals(item2, item1);
        // transitive
        assertEquals(item1, item2);
        assertEquals(item2, item3);
        assertEquals(item1, item3);
        // not equals
        assertNotEquals(item1, diffItem);
        assertNotEquals(item1, new Object());
        assertNotEquals(item1, null);
    }

    @Test
    public void testHashCode(){
        Item item1 = new Item(name, stackSize, amount);
        Item item2 = new Item(name, stackSize, amount);
        Item item3 = new Item(name, stackSize, amount);
        Item diffItem = new Item(name, stackSize, amount/2);
        // reflexive
        assertEquals(item1.hashCode(), item1.hashCode());
        // symmetrical
        assertEquals(item1.hashCode(), item2.hashCode());
        assertEquals(item2.hashCode(), item1.hashCode());
        // transitive
        assertEquals(item1.hashCode(), item2.hashCode());
        assertEquals(item2.hashCode(), item3.hashCode());
        assertEquals(item1.hashCode(), item3.hashCode());
        // not equals
        assertNotEquals(item1.hashCode(), diffItem.hashCode());
    }

    @Test
    public void testToString(){
        assertNotNull(item.toString());
    }
}
