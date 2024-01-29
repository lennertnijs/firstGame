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
}
