package ItemTest;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemInstanceTest {

    @Test
    public void testConstructor(){
        Item item = generateItem(64);
        ItemInstance itemInstance = ItemInstance.builder().amount(32).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemInstance.getItem(), item),
                () -> Assertions.assertEquals(itemInstance.getAmount(), 32)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Item item = generateItem(64);
        ItemInstance.Builder itemInstance1 = ItemInstance.builder().amount(0).item(item);
        ItemInstance.Builder itemInstance2 = ItemInstance.builder().amount(65).item(item);
        ItemInstance.Builder itemInstance3 = ItemInstance.builder().amount(64).item(null);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, itemInstance1::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, itemInstance2::build),
                () -> Assertions.assertThrows(NullPointerException.class, itemInstance3::build)
        );
    }

    @Test
    public void testCanIncrease(){
        Item item = generateItem(32);
        ItemInstance itemInstance = ItemInstance.builder().amount(16).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemInstance.canIncreaseAmountBy(16)),
                () -> Assertions.assertFalse(itemInstance.canIncreaseAmountBy(17)),
                () -> Assertions.assertTrue(itemInstance.canIncreaseAmountBy(0)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.canIncreaseAmountBy(-1))
        );
    }

    @Test
    public void testIncrease(){
        Item item = generateItem(32);
        ItemInstance itemInstance = ItemInstance.builder().amount(16).item(item).build();
        itemInstance.increaseAmount(16);
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemInstance.getAmount(), 32),

                () -> itemInstance.increaseAmount(0),
                () -> Assertions.assertEquals(itemInstance.getAmount(), 32),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.increaseAmount(1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.increaseAmount(-1))
        );
    }


    @Test
    public void testCanDecrease(){
        Item item = generateItem(32);
        ItemInstance itemInstance = ItemInstance.builder().amount(16).item(item).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(itemInstance.canDecreaseAmountBy(16)),
                () -> Assertions.assertFalse(itemInstance.canDecreaseAmountBy(17)),
                () -> Assertions.assertTrue(itemInstance.canDecreaseAmountBy(0)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.canDecreaseAmountBy(-1))
        );
    }

    @Test
    public void testDecrease(){
        Item item = generateItem(32);
        ItemInstance itemInstance = ItemInstance.builder().amount(16).item(item).build();
        itemInstance.decreaseAmount(16);
        Assertions.assertAll(
                () -> Assertions.assertEquals(itemInstance.getAmount(), 0),

                () -> itemInstance.decreaseAmount(0),
                () -> Assertions.assertEquals(itemInstance.getAmount(), 0),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.decreaseAmount(1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> itemInstance.decreaseAmount(-1))
        );
    }




    private Item generateItem(int stackSize){
        return Item.itemBuilder().name("Item").spritePath("item.png").stackSize(stackSize).build();
    }
}
