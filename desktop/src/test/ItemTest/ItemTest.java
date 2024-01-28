package ItemTest;

import com.mygdx.game.Item.Potion;
import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.UsableItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTest {

    @Test
    public void testItem(){
        List<UsableItem> list = new ArrayList<>();
        Tool tool = new Tool();
        Potion potion = new Potion();
        UsableItem usableItem = new UsableItem();
        list.add(tool);
        list.add(potion);
        list.add(usableItem);
        for(UsableItem item: list){
            item.print();
        }
    }
}
