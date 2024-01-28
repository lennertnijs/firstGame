package Player;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Player.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InventoryTest {

    @Test
    public void testConstructor(){
        Item stone = Item.builder().id(0).name("stone").spritePath("path").stackSize(32).build();
        Item wood = Item.builder().id(1).name("wood").spritePath("path").stackSize(32).build();
        ItemInstance itemInstance1 = ItemInstance.builder().amount(15).item(stone).build();
        ItemInstance itemInstance2 = ItemInstance.builder().amount(5).item(wood).build();

        List<ItemInstance> items = new ArrayList<>(Arrays.asList(itemInstance1, itemInstance2));

        Inventory inventory = Inventory.builder().size(2).items(items).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(inventory.getSize(), 2),
                () -> Assertions.assertEquals(inventory.getItems(), items)
        );
    }
}
