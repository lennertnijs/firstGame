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
        assertTrue(itemStacksAreEqual(copy, itemStack));
        copy.increaseAmount(1);
        assertFalse(itemStacksAreEqual(copy, itemStack));
    }

    @Test
    public void testToString(){
        String expected = "ItemStack[Item[name=Stone], amount=32, stackSize=64]";
        assertEquals(expected, itemStack.toString());
    }

    private boolean itemStacksAreEqual(ItemStack stack1, ItemStack stack2){
        return stack1.item().equals(stack2.item())
                && stack1.getAmount() == stack2.getAmount()
                && stack1.stackSize() == stack2.stackSize();
    }
}
