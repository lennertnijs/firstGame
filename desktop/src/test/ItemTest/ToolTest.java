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
}
