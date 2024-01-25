package Player;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Player.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class InventoryTest {

    @Test
    public void testConstructor(){
        HashMap<Integer, ItemInstance> invMap = new HashMap<>();

        Item stone = Item.builder().id(0).name("stone").spritePath("path").stackSize(32).build();
        Item wood = Item.builder().id(1).name("wood").spritePath("path").stackSize(32).build();
        ItemInstance itemInstance1 = ItemInstance.builder().amount(15).item(stone).build();
        ItemInstance itemInstance2 = ItemInstance.builder().amount(5).item(wood).build();

        invMap.put(0, itemInstance1);
        invMap.put(1, itemInstance2);
        Inventory inventory = Inventory.builder().size(2).inventory(invMap).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getSize(), 2),
                () -> Assertions.assertEquals(inventory.getInventory(), invMap)
        );
    }

    @Test
    public void testConstructorInvalidSize(){
        Inventory.Builder builder = Inventory.builder().size(1).inventory(new HashMap<>());
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.size(0).build()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> builder.size(1).build())
        );
    }

    @Test
    public void testConstructorInvalidMap(){
        Inventory.Builder builder = Inventory.builder().size(1).inventory(new HashMap<>());


        Item stone = Item.builder().id(0).name("stone").spritePath("path").stackSize(32).build();
        ItemInstance itemInstance1 = ItemInstance.builder().amount(15).item(stone).build();


        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.inventory(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.inventory(new HashMap<Integer, ItemInstance>(){{
                            put(null, itemInstance1);
                        }}).build())
        );
    }

    @Test
    public void testContains(){
        HashMap<Integer, ItemInstance> invMap = new HashMap<>();

        Item stone = Item.builder().id(0).name("stone").spritePath("path").stackSize(32).build();
        Item wood = Item.builder().id(1).name("wood").spritePath("path").stackSize(32).build();
        ItemInstance itemInstance1 = ItemInstance.builder().amount(15).item(stone).build();

        invMap.put(0, itemInstance1);
        Inventory inventory = Inventory.builder().size(1).inventory(invMap).build();

        Assertions.assertAll(
                () -> Assertions.assertTrue(inventory.contains(stone)),
                () -> Assertions.assertFalse(inventory.contains(wood))
        );
    }

    @Test
    public void testContainsAmount(){
        HashMap<Integer, ItemInstance> invMap = new HashMap<>();

        Item stone = Item.builder().id(0).name("stone").spritePath("path").stackSize(32).build();
        ItemInstance itemInstance1 = ItemInstance.builder().amount(15).item(stone).build();

        invMap.put(0, itemInstance1);
        Inventory inventory = Inventory.builder().size(1).inventory(invMap).build();

        Assertions.assertAll(
                () -> Assertions.assertTrue(inventory.containsAmount(stone, 15)),
                () -> Assertions.assertFalse(inventory.containsAmount(stone, 16)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> inventory.containsAmount(stone, 0)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> inventory.containsAmount(null, 1))

        );
    }
}
