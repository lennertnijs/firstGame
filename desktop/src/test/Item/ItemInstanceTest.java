package Item;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemInstanceTest {

    @Test
    public void testConstructor(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance itemInstance = ItemInstance.builder().amount(24).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemInstance.getItem(), item),
                () -> Assertions.assertEquals(itemInstance.getAmount(), 24)
        );
    }

    @Test
    public void testConstructorInvalidAmount(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance.Builder builder = ItemInstance.builder().amount(24).item(item);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.amount(-1).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> builder.amount(item.getStackSize()+1).build())
        );
    }

    @Test
    public void testConstructorInvalidItem(){
        ItemInstance.Builder builder = ItemInstance.builder().amount(24).item(null);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder::build)
        );
    }

    @Test
    public void testCanIncreaseAmountBy(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance itemInstance = ItemInstance.builder().amount(24).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemInstance.canIncreaseAmountBy(40)),
                () -> Assertions.assertTrue(itemInstance.canIncreaseAmountBy(0)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> itemInstance.canIncreaseAmountBy(-1)),
                () -> Assertions.assertFalse(itemInstance.canIncreaseAmountBy(41))
        );
    }

    @Test
    public void testCanDecreaseAmountBy(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance itemInstance = ItemInstance.builder().amount(24).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemInstance.canDecreaseAmountBy(24)),
                () -> Assertions.assertTrue(itemInstance.canDecreaseAmountBy(0)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> itemInstance.canDecreaseAmountBy(-1)),
                () -> Assertions.assertFalse(itemInstance.canDecreaseAmountBy(25))
        );
    }

    @Test
    public void testIncreaseAmountBy(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance itemInstance = ItemInstance.builder().amount(24).item(item).build();
        itemInstance.increaseAmount(40);
        Assertions.assertEquals(itemInstance.getAmount(), 64);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.increaseAmount(1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.increaseAmount(-1))
        );
    }

    @Test
    public void testDecreaseAmountBy(){
        Item item = Item.builder().id(0).stackSize(64).name("Bert").spritePath("/item").build();
        ItemInstance itemInstance = ItemInstance.builder().amount(24).item(item).build();
        itemInstance.decreaseAmount(24);
        Assertions.assertEquals(itemInstance.getAmount(), 0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.decreaseAmount(1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.decreaseAmount(-1))
        );
    }
}
