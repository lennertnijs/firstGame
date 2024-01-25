package Item;

import com.mygdx.game.Item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void testConstructor(){
        Item item = Item.builder().id(0).stackSize(64).name("stone").spritePath("/items").build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getId(), 0),
                () -> Assertions.assertEquals(item.getStackSize(), 64),
                () -> Assertions.assertEquals(item.getName(), "stone"),
                () -> Assertions.assertEquals(item.getSpritePath(), "/items")
        );
    }

    @Test
    public void testConstructorInvalidId(){
        Item.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.id(-1).build())
        );
    }

    @Test
    public void testConstructorInvalidStackSize(){
        Item.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.stackSize(0).build())
        );
    }

    @Test
    public void testConstructorInvalidName(){
        Item.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.name(null).build())
        );
    }

    @Test
    public void testConstructorInvalidSpritePath(){
        Item.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.spritePath(null).build())
        );
    }

    private Item.Builder generateValidItemBuilder(){
       return Item.builder().id(0).stackSize(64).name("stone").spritePath("/items");
    }
}
