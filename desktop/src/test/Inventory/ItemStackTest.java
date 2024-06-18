package Inventory;

import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStackTest {

    private final Item item = new Item("Stone");
    private final int amount = 32;
    private final int stackSize = 64;
    private ItemStack itemStack;

    @BeforeEach
    public void initialise(){
        itemStack = new ItemStack(item, amount, stackSize);
    }

    @Test
    public void testConstructorWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> new ItemStack(null, amount, stackSize));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemStack(item, -1, stackSize));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemStack(item, 0, stackSize));
    }

    @Test
    public void testConstructorWithAmountTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemStack(item, stackSize + 1, stackSize));
    }

    @Test
    public void testConstructorWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemStack(item, amount, -1));
    }

    @Test
    public void testConstructorWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemStack(item, amount, 0));
    }

    @Test
    public void testGetItem(){
        assertEquals(item, itemStack.item());
    }

    @Test
    public void testGetAmount(){
        assertEquals(amount, itemStack.getAmount());
    }

    @Test
    public void testGetStackSize(){
        assertEquals(stackSize, itemStack.stackSize());
    }

    @Test
    public void testIncreaseAmount(){
        int increaseLeft = itemStack.increaseAmount(30);
        assertEquals(62, itemStack.getAmount());
        assertEquals(0, increaseLeft);

        increaseLeft = itemStack.increaseAmount(30);
        assertEquals(64, itemStack.getAmount());
        assertEquals(28, increaseLeft);

        increaseLeft = itemStack.increaseAmount(30);
        assertEquals(64, itemStack.getAmount());
        assertEquals(30, increaseLeft);
    }

    @Test
    public void testIncreaseAmountWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> itemStack.increaseAmount(-1));
    }

    @Test
    public void testIncreaseAmountWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> itemStack.increaseAmount(0));
    }

    @Test
    public void testDecreaseAmount(){
        int decreaseLeft = itemStack.decreaseAmount(30);
        assertEquals(2, itemStack.getAmount());
        assertEquals(0, decreaseLeft);

        decreaseLeft = itemStack.decreaseAmount(30);
        assertEquals(0, itemStack.getAmount());
        assertEquals(28, decreaseLeft);

        decreaseLeft = itemStack.decreaseAmount(30);
        assertEquals(0, itemStack.getAmount());
        assertEquals(30, decreaseLeft);
    }

    @Test
    public void testDecreaseAmountWithNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> itemStack.decreaseAmount(-1));
    }

    @Test
    public void testDecreaseAmountWithZero(){
        assertThrows(IllegalArgumentException.class,
                () -> itemStack.decreaseAmount(0));
    }

    @Test
    public void testIsFull(){
        assertFalse(itemStack.isFull());
        itemStack.increaseAmount(64);
        assertTrue(itemStack.isFull());
    }

    @Test
    public void testCopy(){
        ItemStack copy = itemStack.copy();
        assertEquals(itemStack, copy);
        copy.increaseAmount(1);
        assertNotEquals(itemStack, copy);
    }

    @Test
    public void testEquals(){
        ItemStack itemStack1 = new ItemStack(item, amount, stackSize);
        ItemStack itemStack2 = new ItemStack(item, amount, stackSize);
        ItemStack itemStack3 = new ItemStack(item, amount, stackSize);
        // reflexive
        assertEquals(itemStack1, itemStack1);
        // symmetrical
        assertEquals(itemStack1, itemStack2);
        assertEquals(itemStack2, itemStack1);
        // transitive
        assertEquals(itemStack1, itemStack2);
        assertEquals(itemStack2, itemStack3);
        assertEquals(itemStack1, itemStack3);

        // not equals
        ItemStack diffItem = new ItemStack(new Item("diff"), amount, stackSize);
        ItemStack diffAmount = new ItemStack(item, 2 * amount, stackSize);
        ItemStack diffStackSize = new ItemStack(item, amount, 2 * stackSize);
        assertNotEquals(itemStack1, diffItem);
        assertNotEquals(itemStack1, diffAmount);
        assertNotEquals(itemStack1, diffStackSize);
        assertNotEquals(itemStack1, new Object());
        assertNotEquals(itemStack1, null);
    }

    @Test
    public void testHashCode(){
        ItemStack itemStack1 = new ItemStack(item, amount, stackSize);
        ItemStack itemStack2 = new ItemStack(item, amount, stackSize);
        ItemStack itemStack3 = new ItemStack(item, amount, stackSize);
        // reflexive
        assertEquals(itemStack1.hashCode(), itemStack1.hashCode());
        // symmetrical
        assertEquals(itemStack1.hashCode(), itemStack2.hashCode());
        assertEquals(itemStack2.hashCode(), itemStack1.hashCode());
        // transitive
        assertEquals(itemStack1.hashCode(), itemStack2.hashCode());
        assertEquals(itemStack2.hashCode(), itemStack3.hashCode());
        assertEquals(itemStack1.hashCode(), itemStack3.hashCode());

        // not equals
        ItemStack diffItem = new ItemStack(new Item("diff"), amount, stackSize);
        ItemStack diffAmount = new ItemStack(item, 2 * amount, stackSize);
        ItemStack diffStackSize = new ItemStack(item, amount, 2 * stackSize);
        assertNotEquals(itemStack1.hashCode(), diffItem.hashCode());
        assertNotEquals(itemStack1.hashCode(), diffStackSize.hashCode());
        assertNotEquals(itemStack1.hashCode(), diffAmount.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "ItemStack[Item[name=Stone], amount=32, stackSize=64]";
        assertEquals(expected, itemStack.toString());
    }
}
