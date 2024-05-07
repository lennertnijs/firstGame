package Inventory;

import com.mygdx.game.V2.Inventory.Inventory;
import com.mygdx.game.V2.Inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private final Item stone = new Item("Stone", 64, 48);
    private final Item wood = new Item("Wood", 64, 16);
    private Inventory emptyInventory;
    private Inventory inventory;

    @BeforeEach
    public void initialise(){
        emptyInventory = new Inventory(4);
        inventory = new Inventory(new Item[]{wood, stone, wood});
    }

    @Test
    public void testConstructorWithNegativeSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Inventory(-1));
    }

    @Test
    public void testConstructorWithZeroSize(){
        assertThrows(IllegalArgumentException.class,
                () -> new Inventory(0));
    }

    @Test
    public void testConstructorWithNullArray(){
        assertThrows(NullPointerException.class,
                () -> new Inventory(null));
    }

    @Test
    public void testConstructorWithNullInArray(){
        assertThrows(NullPointerException.class,
                () -> new Inventory(new Item[]{stone, null, wood}));
    }

    @Test
    public void testConstructorWithEmptyArray(){
        assertThrows(IllegalArgumentException.class,
                () -> new Inventory(new Item[0]));
    }

    @Test
    public void testGetItems(){
        assertArrayEquals(new Item[]{wood, stone, wood}, inventory.getItems());
    }

    @Test
    public void testContains(){
        assertTrue(inventory.contains("Wood", 32));
        assertFalse(inventory.contains("Wood", 33));
        assertTrue(inventory.contains("Stone", 48));
        assertFalse(inventory.contains("Stone", 49));
    }

    @Test
    public void testContainsWithNullName(){
        assertThrows(NullPointerException.class,
                () -> inventory.contains(null, 30));
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
    public void testAddFullStacks(){
        Item stone = new Item("Stone", 64, 64);
        emptyInventory.addItem(stone);
        assertArrayEquals(new Item[]{stone, null, null, null}, emptyInventory.getItems());

        Item wood = new Item("Wood", 64, 64);
        emptyInventory.addItem(wood);
        assertArrayEquals(new Item[]{stone, wood, null, null}, emptyInventory.getItems());
    }

    @Test
    public void testAddPartialStacks(){
        Item stone = new Item("Stone", 64, 32);
        emptyInventory.addItem(stone);
        assertArrayEquals(new Item[]{stone, null, null, null}, emptyInventory.getItems());

        Item moreStone = new Item("Stone", 64, 32);
        emptyInventory.addItem(moreStone);
        Item expectedStone = new Item("Stone", 64, 64);
        assertArrayEquals(new Item[]{expectedStone, null, null, null}, emptyInventory.getItems());
    }

    @Test
    public void testAddToFullInventory(){
        Item moreStone = new Item("Stone", 64, 48);
        int stoneLeft = inventory.addItem(moreStone);
        assertEquals(stoneLeft, 32);
    }

    @Test
    public void testAddWithNullItem(){
        assertThrows(NullPointerException.class,
                () -> emptyInventory.addItem(null));
    }

    @Test
    public void testRemove(){
        inventory.remove("Wood", 8);
        Item woodLeft = new Item("Wood", 64, 8);
        assertArrayEquals(new Item[]{woodLeft, stone, wood}, inventory.getItems());

        inventory.remove("Wood", 20);
        Item woodLeft2 = new Item("Wood", 64, 4);
        assertArrayEquals(new Item[]{null, stone, woodLeft2}, inventory.getItems());
    }

    @Test
    public void testRemoveWithNullName(){
        assertThrows(NullPointerException.class,
                () -> inventory.remove(null, 64));
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
    public void testRemoveInventoryDoesntContainAsMuch(){
        assertThrows(IllegalStateException.class,
                () -> inventory.remove("Stone", 49));
    }

    @Test
    public void testCopy(){
        Inventory copy = inventory.copy();
        assertEquals(inventory, copy);
        copy.remove("Stone", 10);
        assertNotEquals(inventory, copy);
    }

    @Test
    public void testEquals(){
        Inventory inventory1 = new Inventory(new Item[]{wood, stone});
        Inventory inventory2 = new Inventory(new Item[]{wood, stone});
        Inventory inventory3 = new Inventory(new Item[]{wood, stone});
        Inventory diffInventory = new Inventory(new Item[]{stone, stone});
        // reflexive
        assertEquals(inventory1, inventory1);
        // symmetrical
        assertEquals(inventory1, inventory2);
        assertEquals(inventory2, inventory1);
        // transitive
        assertEquals(inventory1, inventory2);
        assertEquals(inventory2, inventory3);
        assertEquals(inventory1, inventory3);
        // not equals
        assertNotEquals(inventory1, diffInventory);
        assertNotEquals(inventory1, new Object());
        assertNotEquals(inventory1, null);
    }

    @Test
    public void testHashCode(){
        Inventory inventory1 = new Inventory(new Item[]{wood, stone});
        Inventory inventory2 = new Inventory(new Item[]{wood, stone});
        Inventory inventory3 = new Inventory(new Item[]{wood, stone});
        Inventory diffInventory = new Inventory(new Item[]{stone, stone});
        // reflexive
        assertEquals(inventory1.hashCode(), inventory1.hashCode());
        // symmetrical
        assertEquals(inventory1.hashCode(), inventory2.hashCode());
        assertEquals(inventory2.hashCode(), inventory1.hashCode());
        // transitive
        assertEquals(inventory1.hashCode(), inventory2.hashCode());
        assertEquals(inventory2.hashCode(), inventory3.hashCode());
        assertEquals(inventory1.hashCode(), inventory3.hashCode());
        // not equals
        assertNotEquals(inventory1.hashCode(), diffInventory.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Inventory{Items=[" +
                "Item[name=Wood, stackSize=64, amount=16], " +
                "Item[name=Stone, stackSize=64, amount=48], " +
                "Item[name=Wood, stackSize=64, amount=16]" +
                "]}";
        assertEquals(expected, inventory.toString());
    }

}
