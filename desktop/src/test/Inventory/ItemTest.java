package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.Inventory.Item;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private String name;
    private String description;
    private Texture texture;
    private int stackSize;
    private ItemTemplate template;
    private int amount;
    private Item item;

    @BeforeEach
    public void initialise(){
        name = "Stone";
        description = "A stone.";
        texture = Mockito.mock(Texture.class);
        stackSize = 64;

        template = new ItemTemplate(name, description, texture, stackSize);
        amount = 32;

        item = new Item(template, amount);
    }

    @Test
    public void testConstructorWithNullItemTemplate(){
        assertThrows(NullPointerException.class,
                () -> new Item(null, amount));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(template, -1));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(template, 0));
    }

    @Test
    public void testConstructorWithAmountTooBig(){
        assertThrows(IllegalArgumentException.class,
                () -> new Item(template, 64 + 1));
    }

    @Test
    public void testGetTemplate(){
        assertEquals(template, item.getTemplate());
    }

    @Test
    public void testGetName(){
        assertEquals(name, item.getName());
    }

    @Test
    public void testGetDescription(){
        assertEquals(description, item.getDescription());
    }

    @Test
    public void testGetTexture(){
        assertEquals(texture, item.getTexture());
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
        Item item1 = new Item(template, 32);
        Item item2 = new Item(template, 32);
        Item item3 = new Item(template, 32);
        Item diffItem = new Item(template, 64);
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
        Item item1 = new Item(template, 32);
        Item item2 = new Item(template, 32);
        Item item3 = new Item(template, 32);
        Item diffItem = new Item(template, 64);
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
