package Inventory;

import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private final Item stone = new Item("Stone");
    private final Item wood = new Item("Wood");
    private final ItemStack stack1 = new ItemStack(stone, 32, 64);
    private final ItemStack stack2 = new ItemStack(wood, 16, 64);
    private final ItemStack stack3 = new ItemStack(stone, 8, 64);
    private final ItemStack[] stacks = new ItemStack[]{stack1, stack2, null, null, stack3};
    private Inventory inventory;

    @BeforeEach
    public void initialise(){
        inventory = Inventory.createWithStacks(stacks);
    }

    @Test
    public void testStaticFactoryWithNullArray(){
        assertThrows(NullPointerException.class,
                () -> Inventory.createWithStacks(null));
    }

    @Test
    public void testStaticFactoryWithEmptyArray(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createWithStacks(new ItemStack[0]));
    }

    @Test
    public void testStaticFactoryWithNegativeSize(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createEmptyOfSize(-1));
    }

    @Test
    public void testStaticFactoryWithZeroSize(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createEmptyOfSize(0));
    }

    @Test
    public void testGetSize(){
        assertEquals(5, inventory.size());
    }

    @Test
    public void testGetItems(){
        assertArrayEquals(stacks, inventory.getItems());
    }

    @Test
    public void testGetItemsHandsCopy(){
        ItemStack[] shouldBeCopied = inventory.getItems();
        assertArrayEquals(shouldBeCopied, inventory.getItems());
        shouldBeCopied[1].increaseAmount(16);
        assertFalse(Arrays.equals(shouldBeCopied, inventory.getItems()));
    }

    @Test
    public void testGetItem(){
        assertEquals(inventory.getItem(0), stack1);
        assertEquals(inventory.getItem(1), stack2);
        assertNull(inventory.getItem(2));
        assertNull(inventory.getItem(3));
        assertEquals(inventory.getItem(4), stack3);
    }

    @Test
    public void testGetItemWithNegativeIndex(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.getItem(-1));
    }

    @Test
    public void testGetItemWithIndexOutOfBounds(){
        assertThrows(IndexOutOfBoundsException.class,
                () -> inventory.getItem(5));
    }

    @Test
    public void testCreateEmpty(){
        Inventory emptyInventory = Inventory.createEmptyOfSize(6);
        assertEquals(6, emptyInventory.size());
        assertArrayEquals(new ItemStack[]{null, null, null, null, null, null}, emptyInventory.getItems());
    }

    @Test
    public void testContains(){
        assertTrue(inventory.contains(stone, 32));
        assertTrue(inventory.contains(stone, 40));
        assertFalse(inventory.contains(stone, 40 + 1));

        assertTrue(inventory.contains(wood, 16));
        assertFalse(inventory.contains(wood, 17));
    }

    @Test
    public void testContainsWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> inventory.contains(null, 32));
    }

    @Test
    public void testContainsWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.contains(stone, -1));
    }

    @Test
    public void testContainsWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.contains(stone, 0));
    }

    @Test
    public void testAdd(){
        inventory.add(stone, 64, 64);
        assertTrue(inventory.contains(stone, 40 + 64));
        ItemStack[] expected = new ItemStack[5];
        expected[0] = new ItemStack(stone, 32 + 32, 64);
        expected[1] = new ItemStack(wood, 16, 64);
        expected[2] = null;
        expected[3] = null;
        expected[4] = new ItemStack(stone, 8 + 32, 64);
        assertArrayEquals(expected, inventory.getItems());

        inventory.add(stone, 128, 64);
        assertTrue(inventory.contains(stone, 40 + 64 + 128));
        ItemStack[] expected2 = new ItemStack[5];
        expected2[0] = new ItemStack(stone, 64, 64);
        expected2[1] = new ItemStack(wood, 16, 64);
        expected2[2] = new ItemStack(stone, 64, 64);
        expected2[3] = new ItemStack(stone, 40, 64);
        expected2[4] = new ItemStack(stone, 64, 64);
        assertArrayEquals(expected2, inventory.getItems());

        int remainder = inventory.add(stone, 64, 64);
        assertTrue(inventory.contains(stone, 256));
        ItemStack[] expected3 = new ItemStack[5];
        expected3[0] = new ItemStack(stone, 64, 64);
        expected3[1] = new ItemStack(wood, 16, 64);
        expected3[2] = new ItemStack(stone, 64, 64);
        expected3[3] = new ItemStack(stone, 64, 64);
        expected3[4] = new ItemStack(stone, 64, 64);
        assertArrayEquals(expected3, inventory.getItems());
        assertEquals(64 - 24, remainder);
    }

    @Test
    public void testAddWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> inventory.add(null, 64, 64));
    }

    @Test
    public void testAddWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add(stone, -1, 64));
    }

    @Test
    public void testAddWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add(stone, 0, 64));
    }

    @Test
    public void testAddWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add(stone, 64, -1));
    }

    @Test
    public void testAddWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add(stone, 64, 0));
    }
}
