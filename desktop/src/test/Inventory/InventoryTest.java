package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Inventory.Inventory;
import com.mygdx.game.V2.Inventory.Item;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Item stone;
    private Item wood;
    private Inventory emptyInventory;
    private Inventory inventory;

    @BeforeEach
    public void initialise(){
        emptyInventory = new Inventory(4);
        stone = new Item("Stone", 64, 48);
        wood = new Item("Wood", 64, 16);

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
    public void testAddFullStacks(){
        Item stone = new Item("Stone", 64, 32);
        emptyInventory.addItem(stone);
        assertArrayEquals(new Item[]{stone, null, null, null}, emptyInventory.getItems());

        Item wood = new Item("Wood", 64, 32);
        emptyInventory.addItem(wood);
        assertArrayEquals(new Item[]{stone, wood, null, null}, emptyInventory.getItems());
    }

}
