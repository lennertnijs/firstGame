package Inventory;

import com.mygdx.game.Inventory.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private final String name = "Stone";
    private final Item item = new Item(name);

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class,
                () -> new Item(null));
    }

    @Test
    public void testGetName(){
        assertEquals(name, item.name());
    }

    @Test
    public void testEquals(){
        Item item1 = new Item(name);
        Item item2 = new Item(name);
        Item item3 = new Item(name);
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
        Item diffName = new Item("diff name");
        assertNotEquals(item1, diffName);
        assertNotEquals(item1, new Object());
        assertNotEquals(item1, null);
    }

    @Test
    public void testHashCode(){
        Item item1 = new Item(name);
        Item item2 = new Item(name);
        Item item3 = new Item(name);
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
        Item diffName = new Item("diff name");
        assertNotEquals(item1.hashCode(), diffName.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Item[name=Stone]";
        assertEquals(expected, item.toString());
    }
}
