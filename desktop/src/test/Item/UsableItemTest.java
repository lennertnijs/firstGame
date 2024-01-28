package Item;

import com.mygdx.game.Item.UsableItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsableItemTest {

    @Test
    public void testConstructor(){
        UsableItem usableItem = UsableItem.builder().id(0).stackSize(64).name("stone").spritePath("/items").build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(usableItem.getId(), 0),
                () -> Assertions.assertEquals(usableItem.getStackSize(), 64),
                () -> Assertions.assertEquals(usableItem.getName(), "stone"),
                () -> Assertions.assertEquals(usableItem.getSpritePath(), "/items")
        );
    }

    @Test
    public void testConstructorInvalidId(){
        UsableItem.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.id(-1).build())
        );
    }

    @Test
    public void testConstructorInvalidStackSize(){
        UsableItem.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.stackSize(0).build())
        );
    }

    @Test
    public void testConstructorInvalidName(){
        UsableItem.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.name(null).build())
        );
    }

    @Test
    public void testConstructorInvalidSpritePath(){
        UsableItem.Builder builder = generateValidItemBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.spritePath(null).build())
        );
    }

    private UsableItem.Builder generateValidItemBuilder(){
       return UsableItem.builder().id(0).stackSize(64).name("stone").spritePath("/items");
    }
}
