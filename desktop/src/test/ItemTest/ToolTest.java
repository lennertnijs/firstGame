package ItemTest;

import com.mygdx.game.Item.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolTest {

    @Test
    public void testConstructor(){
        Tool tool = Tool.toolBuilder().name("Pickaxe").spritePath("tools/pickaxe.png").proficiency(1500).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tool.getName(), "Pickaxe"),
                () -> Assertions.assertEquals(tool.getSpritePath(), "tools/pickaxe.png"),
                () -> Assertions.assertEquals(tool.getStackSize(), 1),
                () -> Assertions.assertEquals(tool.getProficiency(), 1500)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Tool.Builder builder1 = Tool.toolBuilder().name(null).spritePath("pickaxe.png").proficiency(1500);
        Tool.Builder builder2 = Tool.toolBuilder().name("Pickaxe").spritePath(null).proficiency(1500);
        Tool.Builder builder3 = Tool.toolBuilder().name("Pickaxe").spritePath("pickaxe.png").proficiency(0);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build)
        );
    }
}
