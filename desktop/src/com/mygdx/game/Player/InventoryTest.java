package com.mygdx.game.Player;

import com.mygdx.game.Item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryTest {

    @Test
    public void testConstructor(){
        Item[] items = new Item[9];
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        items[0] = axe;
        for(int i = 1; i < 9; i++){
            items[i] = null;
        }
        Inventory inventory = Inventory.builder().items(items).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getSize(), 9),
                () -> Assertions.assertEquals(inventory.getItems(), items),
                () -> Assertions.assertEquals(inventory.getItems()[0], axe)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Assertions.assertThrows(NullPointerException.class, () -> Inventory.builder().build());
    }


    @Test
    public void testGetItem(){
        Inventory inventory = generateInventory(9);
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getItem(0), axe),
                () -> Assertions.assertNull(inventory.getItem(1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> inventory.getItem(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> inventory.getItem(9))
        );
    }

    @Test
    public void testHasEmptySlots(){
        Item[] items = new Item[9];
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        for(int i = 0; i < 8; i++){
            items[i] = axe;
        }
        Inventory inventory = Inventory.builder().items(items).build();
        Assertions.assertAll(
                () -> Assertions.assertTrue(inventory.hasEmptySlot()),
                () -> inventory.addItem(axe),
                () -> Assertions.assertFalse(inventory.hasEmptySlot())
        );
    }
    @Test
    public void testAddItem(){
        Inventory inventory = generateInventory(9);
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getItem(0), axe),
                () -> inventory.addItem(axe),
                () -> Assertions.assertEquals(inventory.getItem(1), axe)
        );
    }

    @Test
    public void testRemoveItem(){
        Inventory inventory = generateInventory(9);
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getItem(0), axe),
                () -> inventory.removeItem(0),
                () -> Assertions.assertNull(inventory.getItem(0)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> inventory.removeItem(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> inventory.removeItem(9))
        );
    }

    @Test
    public void testEqualsAndHashCode(){
        Inventory inventory1 = generateInventory(9);
        Inventory inventory2 = generateInventory(9);
        Inventory inventory3 = generateInventory(8);
        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory1, inventory2),
                () -> Assertions.assertNotEquals(inventory1, inventory3),
                () -> Assertions.assertEquals(inventory1, inventory1),
                () -> Assertions.assertNotEquals(inventory1, new Object()),
                () -> Assertions.assertEquals(inventory1.hashCode(), inventory2.hashCode()),
                () -> Assertions.assertNotEquals(inventory1.hashCode(), inventory3.hashCode())
        );
    }


    private Inventory generateInventory(int size){
        Item[] items = new Item[size];
        Item axe = Item.itemBuilder().name("Axe").itemId(0).amount(15).stackSize(64).build();
        items[0] = axe;
        for(int i = 1; i < size; i++){
            items[i] = null;
        }
        return Inventory.builder().items(items).build();
    }
}
