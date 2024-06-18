package Inventory;

import com.mygdx.game.Inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private final String name = "Stone";
    private final int amount = 32;
    private final int stackSize = 64;
    private Item item;

    @BeforeEach
    public void initialise(){
        item = new Item(name, amount, stackSize);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Item(null, amount, stackSize));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, -1, stackSize));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, 0, stackSize));
    }

    @Test
    public void testConstructorWithAmountTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, stackSize + 1, stackSize));
    }

    @Test
    public void testConstructorWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, amount, -1));
    }

    @Test
    public void testConstructorWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, amount, 0));
    }

    @Test
    public void testGetName(){
        assertEquals(name, item.name());
    }

    @Test
    public void testGetAmount(){
        assertEquals(amount, item.getAmount());
    }

    @Test
    public void testGetStackSize(){
        assertEquals(stackSize, item.stackSize());
    }

    @Test
    public void testIncreaseAmount(){
        int increaseLeft = item.increaseAmount(30);
        assertEquals(62, item.getAmount());
        assertEquals(0, increaseLeft);

        increaseLeft = item.increaseAmount(30);
        assertEquals(64, item.getAmount());
        assertEquals(28, increaseLeft);

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
        int decreaseLeft = item.decreaseAmount(30);
        assertEquals(2, item.getAmount());
        assertEquals(0, decreaseLeft);

        decreaseLeft = item.decreaseAmount(30);
        assertEquals(0, item.getAmount());
        assertEquals(28, decreaseLeft);

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
    public void testCopy(){
        Item copy = item.copy();
        assertEquals(item, copy);
        copy.increaseAmount(1);
        assertNotEquals(item, copy);
    }

    @Test
    public void testEquals(){
        Item item1 = new Item(name, amount, stackSize);
        Item item2 = new Item(name, amount, stackSize);
        Item item3 = new Item(name, amount, stackSize);
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
        Item diffName = new Item("new name", amount, stackSize);
        Item diffAmount = new Item(name, 2 * amount, stackSize);
        Item diffStackSize = new Item(name, amount, 2 * stackSize);
        assertNotEquals(item1, diffName);
        assertNotEquals(item1, diffAmount);
        assertNotEquals(item1, diffStackSize);
        assertNotEquals(item1, new Object());
        assertNotEquals(item1, null);
    }

    @Test
    public void testHashCode(){
        Item item1 = new Item(name, amount, stackSize);
        Item item2 = new Item(name, amount, stackSize);
        Item item3 = new Item(name, amount, stackSize);
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
        Item diffName = new Item("new name", amount, stackSize);
        Item diffAmount = new Item(name, 2 * amount, stackSize);
        Item diffStackSize = new Item(name, amount, 2 * stackSize);
        assertNotEquals(item1.hashCode(), diffName.hashCode());
        assertNotEquals(item1.hashCode(), diffStackSize.hashCode());
        assertNotEquals(item1.hashCode(), diffAmount.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Item[name=Stone, amount=32, stackSize=64]";
        assertEquals(expected, item.toString());
    }
}
