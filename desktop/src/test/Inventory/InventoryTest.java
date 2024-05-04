package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Inventory.Inventory;
import com.mygdx.game.V2.Inventory.Item;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

    private ItemTemplate stoneTemplate;
    private ItemTemplate woodTemplate;
    private Item stone;
    private Item wood;
    private Inventory emptyInventory;
    private Inventory inventory;

    @BeforeEach
    public void initialise(){
        emptyInventory = new Inventory(9);

        stoneTemplate = new ItemTemplate("Stone", "A Stone.", Mockito.mock(Texture.class), 64);
        woodTemplate = new ItemTemplate("Wood", "A wood.", Mockito.mock(Texture.class), 64);
        stone = new Item(stoneTemplate, 48);
        wood = new Item(woodTemplate, 16);

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
    public void testAdd(){
        Item moreStone = new Item(stoneTemplate, 64);
        emptyInventory.addItem(moreStone);
    }

}
