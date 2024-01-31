package ItemTest;

import com.mygdx.game.Item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void testConstructor(){
        Item item = Item.itemBuilder().name("Stone").stackSize(64).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getName(), "Stone"),
                () -> Assertions.assertEquals(item.getStackSize(), 64),
                () -> Assertions.assertNull(item.getTexture())
        );
    }

    @Test
    public void testConstructorInvalid(){
        Item.Builder builder1 = Item.itemBuilder().name(null).stackSize(64);
        Item.Builder builder3 = Item.itemBuilder().name("Stone").stackSize(0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder1::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build)
        );
    }

    @Test
    public void testEquals(){
        Item pick = Item.itemBuilder().name("pickaxe").stackSize(1).build();
        Item axe = Item.itemBuilder().name("axe").stackSize(1).build();
        Item pick2 = Item.itemBuilder().name("pickaxe").stackSize(1).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(pick, pick2),
                () -> Assertions.assertEquals(pick, pick),
                () -> Assertions.assertNotEquals(pick, axe),
                () -> Assertions.assertNotEquals(pick, new Object()),
                () -> Assertions.assertEquals(pick.hashCode(), pick2.hashCode()),
                () -> Assertions.assertNotEquals(pick.hashCode(), axe.hashCode())
        );

    }
}
