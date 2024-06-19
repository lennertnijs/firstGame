package Inventory;

import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private final Item stone1 = new Item("Stone", 32);
    private final Item wood = new Item("Wood", 16);
    private final Item stone2 = new Item("Stone", 8);
    private final Item[] stacks = new Item[]{stone1, wood, null, null, stone2};
    private final Map<String, Integer> stackSizeMap = new HashMap<String, Integer>(){{
        put("Stone", 64);
        put("Wood", 64);
    }};
    private Inventory inventory;

    @BeforeEach
    public void initialise(){
        inventory = Inventory.createWithItems(stacks, stackSizeMap);
    }

    @Test
    public void testStaticFactoryWithNullArray(){
        assertThrows(NullPointerException.class,
                () -> Inventory.createWithItems(null, stackSizeMap));
    }

    @Test
    public void testStaticFactoryWithEmptyArray(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createWithItems(new Item[0], stackSizeMap));
    }

    @Test
    public void testStaticFactoryWithNegativeSize(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createEmptyOfSize(-1, stackSizeMap));
    }

    @Test
    public void testStaticFactoryWithZeroSize(){
        assertThrows(IllegalArgumentException.class,
                () -> Inventory.createEmptyOfSize(0, stackSizeMap));
    }

    @Test
    public void testGetSize(){
        assertEquals(5, inventory.size());
    }

    @Test
    public void testGetItems(){
        assertTrue(areItemArraysEqual(stacks, inventory.getItems()));
    }

    @Test
    public void testGetItemsDoesDeepCopy(){
        Item[] copy = inventory.getItems();
        copy[0].increaseAmount(16, 64); // change one
        assertFalse(areItemArraysEqual(copy, inventory.getItems()));
    }

    @Test
    public void testGetItem(){
        assertTrue(ItemsAreEqual(inventory.getItem(0), stone1));
        assertTrue(ItemsAreEqual(inventory.getItem(1), wood));
        assertTrue(ItemsAreEqual(inventory.getItem(2), null));
        assertTrue(ItemsAreEqual(inventory.getItem(3), null));
        assertTrue(ItemsAreEqual(inventory.getItem(4), stone2));
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
        Inventory emptyInventory = Inventory.createEmptyOfSize(6, stackSizeMap);
        assertEquals(6, emptyInventory.size());
        Item[] emptyExpected = new Item[]{null, null, null, null, null, null};
        assertTrue(areItemArraysEqual(emptyExpected, emptyInventory.getItems()));
    }

    @Test
    public void testContains(){
        assertTrue(inventory.contains("Stone", 32));
        assertTrue(inventory.contains("Stone", 40));
        assertFalse(inventory.contains("Stone", 40 + 1));

        assertTrue(inventory.contains("Wood", 16));
        assertFalse(inventory.contains("Wood", 17));
    }

    @Test
    public void testContainsWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> inventory.contains(null, 32));
    }

    @Test
    public void testContainsWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.contains("Stone", -1));
    }

    @Test
    public void testContainsWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.contains("Stone", 0));
    }

    @Test
    public void testAdd(){
        inventory.add("Stone", 64);
        assertTrue(inventory.contains("Stone", 40 + 64));
        Item[] expected = new Item[5];
        expected[0] = new Item("Stone", 32 + 32);
        expected[1] = new Item("Wood", 16);
        expected[2] = null;
        expected[3] = null;
        expected[4] = new Item("Stone", 8 + 32);
        assertTrue(areItemArraysEqual(expected, inventory.getItems()));

        inventory.add("Stone", 128);
        assertTrue(inventory.contains("Stone", 40 + 64 + 128));
        Item[] expected2 = new Item[5];
        expected2[0] = new Item("Stone", 64);
        expected2[1] = new Item("Wood", 16);
        expected2[2] = new Item("Stone", 64);
        expected2[3] = new Item("Stone", 40);
        expected2[4] = new Item("Stone", 64);
        assertTrue(areItemArraysEqual(expected2, inventory.getItems()));

        int remainder = inventory.add("Stone", 64);
        assertTrue(inventory.contains("Stone", 256));
        Item[] expected3 = new Item[5];
        expected3[0] = new Item("Stone", 64);
        expected3[1] = new Item("Wood", 16);
        expected3[2] = new Item("Stone", 64);
        expected3[3] = new Item("Stone", 64);
        expected3[4] = new Item("Stone", 64);
        assertTrue(areItemArraysEqual(expected3, inventory.getItems()));
        assertEquals(64 - 24, remainder);
    }

    @Test
    public void testAddWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> inventory.add(null, 64));
    }

    @Test
    public void testAddWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add("Stone", -1));
    }

    @Test
    public void testAddWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.add("Stone", 0));
    }

    @Test
    public void testAddWithItemNameNotRegistered(){
        assertThrows(NoSuchElementException.class,
                () -> inventory.add("Rock", 32));
    }

    @Test
    public void testRemove(){
        inventory.remove("Stone", 16);
        assertTrue(inventory.contains("Stone", 40 - 16));
        Item[] expected = new Item[5];
        expected[0] = new Item("Stone", 16);
        expected[1] = new Item("Wood", 16);
        expected[2] = null;
        expected[3] = null;
        expected[4] = new Item("Stone", 8);
        assertTrue(areItemArraysEqual(expected, inventory.getItems()));

        inventory.remove("Stone", 20);
        assertTrue(inventory.contains("Stone", 40 - 16 - 20));
        Item[] expected2 = new Item[5];
        expected2[0] = null;
        expected2[1] = new Item("Wood", 16);
        expected2[2] = null;
        expected2[3] = null;
        expected2[4] = new Item("Stone", 8 - 4);
        assertTrue(areItemArraysEqual(expected2, inventory.getItems()));
    }

    @Test
    public void testRemoveWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> inventory.remove(null, 10));
    }

    @Test
    public void testRemoveWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.remove("Stone", -1));
    }

    @Test
    public void testRemoveWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.remove("Stone", 0));
    }

    @Test
    public void testRemoveDoesNotContain(){
        assertThrows(IllegalArgumentException.class,
                () -> inventory.remove("Stone", 32 + 8 + 1));
    }

    @Test
    public void testToString(){
        String expected = "Inventory[" + System.lineSeparator() +
                "Slot 0: Item[name=Stone, amount=32]"  + System.lineSeparator() +
                "Slot 1: Item[name=Wood, amount=16]"  + System.lineSeparator() +
                "Slot 2: null"  + System.lineSeparator() +
                "Slot 3: null"  + System.lineSeparator() +
                "Slot 4: Item[name=Stone, amount=8]"  + System.lineSeparator() +
                "]";
        assertEquals(expected, inventory.toString());
    }

    private boolean areItemArraysEqual(Item[] array1, Item[] array2){
        boolean equal = true;
        for(int i = 0; i < inventory.size(); i++){
            equal = equal && ItemsAreEqual(array1[i], array2[i]);
        }
        return equal;
    }

    private boolean ItemsAreEqual(Item stack1, Item stack2){
        if(stack1 == null && stack2 == null) return true;
        if(stack1 == null || stack2 == null) return false;
        return stack1.name().equals(stack2.name())
                && stack1.getAmount() == stack2.getAmount();
    }
}
