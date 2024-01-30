package ItemTest;

import com.mygdx.game.Item.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolTest {

    @Test
    public void testConstructor(){
        Tool tool = Tool.toolBuilder().name("Pickaxe").proficiency(1500).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tool.getName(), "Pickaxe"),
                () -> Assertions.assertEquals(tool.getStackSize(), 1),
                () -> Assertions.assertEquals(tool.getProficiency(), 1500)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Tool.Builder builder1 = Tool.toolBuilder().name(null).proficiency(1500);
        Tool.Builder builder3 = Tool.toolBuilder().name("Pickaxe").proficiency(0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder1::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build)
        );
    }
}
