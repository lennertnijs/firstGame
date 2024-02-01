package com.mygdx.game.Item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void testConstructor(){
        Item item = Item.itemBuilder().itemId(0).name("Stone").stackSize(64).amount(1).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(item.getItemId(), 0),
                () -> Assertions.assertEquals(item.getName(), "Stone"),
                () -> Assertions.assertEquals(item.getStackSize(), 64),
                () -> Assertions.assertEquals(item.getAmount(), 1)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Item.Builder builder1 = Item.itemBuilder().itemId(-1).name("Stone").stackSize(64).amount(32);
        Item.Builder builder2 = Item.itemBuilder().itemId(0).name(null).stackSize(64).amount(32);
        Item.Builder builder3 = Item.itemBuilder().itemId(0).name("Stone").stackSize(0).amount(32);
        Item.Builder builder4 = Item.itemBuilder().itemId(0).name("Stone").stackSize(64).amount(0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder4::build)
        );
    }

    @Test
    public void testEquals(){
        Item pick = Item.itemBuilder().itemId(0).name("pickaxe").stackSize(1).amount(1).build();
        Item axe = Item.itemBuilder().itemId(1).name("axe").stackSize(1).amount(1).build();
        Item pick2 = Item.itemBuilder().itemId(0).name("pickaxe").stackSize(1).amount(1).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(pick, pick2),
                () -> Assertions.assertEquals(pick, pick),
                () -> Assertions.assertNotEquals(pick, axe),
                () -> Assertions.assertNotEquals(pick, new Object()),
                () -> Assertions.assertEquals(pick.hashCode(), pick2.hashCode()),
                () -> Assertions.assertNotEquals(pick.hashCode(), axe.hashCode()),
                () -> Assertions.assertEquals(pick.hashCode(), pick.hashCode())
        );
    }


    @Test
    public void testSetAmount(){
        Item pick = Item.itemBuilder().itemId(0).name("pickaxe").stackSize(64).amount(32).build();

        Assertions.assertAll(
                () -> pick.setAmount(64),
                () -> Assertions.assertEquals(pick.getAmount(), 64),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> pick.setAmount(65)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> pick.setAmount(-1))
        );
    }
}
