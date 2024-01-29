package ItemTest;

import com.mygdx.game.Item.NotUsableItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotUsableItemTest {

    @Test
    public void testConstructor(){
        NotUsableItem.Builder<?> itemBuilder = new NotUsableItem.Builder<>();
        NotUsableItem item = itemBuilder.stackSize(64).name("Stone").spritePath("resources/stone.png").build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getStackSize(), 64),
                () -> Assertions.assertEquals(item.getName(), "Stone"),
                () -> Assertions.assertEquals(item.getSpritePath(), "resources/stone.png")
        );
    }

    @Test
    public void testConstructorInvalid(){
        NotUsableItem.Builder<?> builder1 = new NotUsableItem.Builder<>();
        builder1.stackSize(0).name("Stone").spritePath("resources/stone.png");

        NotUsableItem.Builder<?> builder2 = new NotUsableItem.Builder<>();
        builder2.stackSize(64).name(null).spritePath("resources/stone.png");

        NotUsableItem.Builder<?> builder3 = new NotUsableItem.Builder<>();
        builder3.stackSize(64).name("Stone").spritePath(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder3::build)
        );
    }
}
