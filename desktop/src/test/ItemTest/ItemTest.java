package ItemTest;

import com.mygdx.game.Item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void testConstructor(){
        Item item = Item.itemBuilder().name("Stone").spritePath("stone.png").stackSize(64).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getName(), "Stone"),
                () -> Assertions.assertEquals(item.getSpritePath(), "stone.png"),
                () -> Assertions.assertEquals(item.getStackSize(), 64)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Item.Builder builder1 = Item.itemBuilder().name(null).spritePath("stone.png").stackSize(64);
        Item.Builder builder2 = Item.itemBuilder().name("Stone").spritePath(null).stackSize(64);
        Item.Builder builder3 = Item.itemBuilder().name("Stone").spritePath("stone.png").stackSize(0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build)
        );
    }
}
