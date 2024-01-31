package ItemTest;

import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.ToolType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolTest {

    @Test
    public void testConstructor(){
        Tool tool = Tool.toolBuilder().name("Pickaxe").efficiency(1500).texture(null).toolType(ToolType.PICKAXE).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tool.getName(), "Pickaxe"),
                () -> Assertions.assertEquals(tool.getStackSize(), 1),
                () -> Assertions.assertEquals(tool.getEfficiency(), 1500),
                () -> Assertions.assertEquals(tool.getToolType(), ToolType.PICKAXE),
                () -> Assertions.assertNull(tool.getTexture())
        );
    }

    @Test
    public void testConstructorInvalid(){
        Tool.Builder builder1 = Tool.toolBuilder().name(null).efficiency(1500).toolType(ToolType.PICKAXE);
        Tool.Builder builder2 = Tool.toolBuilder().name("Pickaxe").efficiency(0).toolType(ToolType.PICKAXE);
        Tool.Builder builder3 = Tool.toolBuilder().name("Pickaxe").efficiency(500).toolType(null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder1::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder2::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder3::build)
        );
    }


    @Test
    public void testEquals(){
        Tool pick = Tool.toolBuilder().name("pick").efficiency(50).toolType(ToolType.PICKAXE).build();
        Tool axe = Tool.toolBuilder().name("pick").efficiency(50).toolType(ToolType.AXE).build();
        Tool pick2 = Tool.toolBuilder().name("pick").efficiency(50).toolType(ToolType.PICKAXE).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(pick, pick2),
                () -> Assertions.assertNotEquals(pick, axe),
                () -> Assertions.assertEquals(pick, pick),
                () -> Assertions.assertNotEquals(pick, new Object()),
                () -> Assertions.assertEquals(pick.hashCode(), pick2.hashCode()),
                () -> Assertions.assertNotEquals(pick.hashCode(), axe.hashCode())
        );
    }
}
