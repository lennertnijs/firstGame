package ItemTest;

import com.mygdx.game.Item.Tool;
import org.junit.jupiter.api.Test;

public class ToolTest {

    @Test
    public void testConstructor(){
        Tool.Builder<?> toolBuilder = new Tool.Builder<>();
        toolBuilder.stackSize(1).name("Pickaxe").spritePath("tools/pickaxe.png").proficiency(1500).build();
    }
}
