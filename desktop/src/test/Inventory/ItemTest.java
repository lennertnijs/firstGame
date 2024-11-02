package Inventory;

import com.mygdx.game.inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private final String name = "Stone";
    private final int amount = 32;
    private Item item;

    @BeforeEach
    public void initialise(){
        item = new Item(name, amount);
    }

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Item(null, amount));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, -1));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(name, 0));
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
    public void testIncreaseAmount(){
        assertEquals(32, item.getAmount());

        int remainder = item.increaseAmount(16, 64);
        assertEquals(32 + 16, item.getAmount());
        assertEquals(0, remainder);

        remainder = item.increaseAmount(32, 64);
        assertEquals(64, item.getAmount());
        assertEquals(16, remainder);

        remainder = item.increaseAmount(32, 128);
        assertEquals(96, item.getAmount());
        assertEquals(0, remainder);

        remainder = item.increaseAmount(32, 96);
        assertEquals(96, item.getAmount());
        assertEquals(32, remainder);
    }

    @Test
    public void testIncreaseWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(-1, 64));
    }

    @Test
    public void testIncreaseWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(0, 64));
    }

    @Test
    public void testIncreaseWithNegativeStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(32, -1));
    }

    @Test
    public void testIncreaseWithZeroStackSize(){
        assertThrows(IllegalArgumentException.class,
                () -> item.increaseAmount(32, 0));
    }

    @Test
    public void testIncreaseWithCurrentAmountBiggerThanStackSize(){
        assertThrows(IllegalStateException.class,
                () -> item.increaseAmount(32, 16)); // amount is 32 currently
    }

    @Test
    public void testDecreaseAmount(){
        assertEquals(32, item.getAmount());

        int remainder = item.decreaseAmount(16);
        assertEquals(32 - 16, item.getAmount());
        assertEquals(0, remainder);

        remainder = item.decreaseAmount(32);
        assertEquals(0, item.getAmount());
        assertEquals(16, remainder);
    }

    @Test
    public void testDecreaseWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> item.decreaseAmount(-1));
    }

    @Test
    public void testDecreaseWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> item.decreaseAmount(0));
    }

    @Test
    public void testToString(){
        String expected = "Item[name=Stone, amount=32]";
        assertEquals(expected, item.toString());
    }
}
