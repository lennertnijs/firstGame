package ItemTest;

import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.ToolType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolTest {

    @Test
    public void testConstructor(){
        Tool tool = Tool.toolBuilder().itemId(0).name("Pickaxe").efficiency(1500).durability(5000)
                .toolType(ToolType.PICKAXE).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(tool.getItemId(), 0),
                () -> Assertions.assertEquals(tool.getName(), "Pickaxe"),
                () -> Assertions.assertEquals(tool.getStackSize(), 1),
                () -> Assertions.assertEquals(tool.getAmount(), 1),
                () -> Assertions.assertEquals(tool.getEfficiency(), 1500),
                () -> Assertions.assertEquals(tool.getDurability(), 5000),
                () -> Assertions.assertEquals(tool.getToolType(), ToolType.PICKAXE)
        );
    }

    @Test
    public void testConstructorInvalid(){
        Tool.Builder builder1 = Tool.toolBuilder().itemId(-1).name("Pickaxe").efficiency(500).durability(5).toolType(ToolType.PICKAXE);
        Tool.Builder builder2 = Tool.toolBuilder().itemId(0).name(null).efficiency(500).durability(5).toolType(ToolType.PICKAXE);
        Tool.Builder builder3 = Tool.toolBuilder().itemId(0).name("Pickaxe").efficiency(0).durability(5).toolType(ToolType.PICKAXE);
        Tool.Builder builder4 = Tool.toolBuilder().itemId(0).name("Pickaxe").efficiency(500).durability(-1).toolType(ToolType.PICKAXE);
        Tool.Builder builder5 = Tool.toolBuilder().itemId(0).name("Pickaxe").efficiency(500).durability(5).toolType(null);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder1::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder3::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, builder4::build),
                () -> Assertions.assertThrows(NullPointerException.class, builder5::build)
        );
    }


    @Test
    public void testEquals(){
        Tool pick = Tool.toolBuilder().itemId(0).name("pick").efficiency(50).durability(15).toolType(ToolType.PICKAXE).build();
        Tool axe = Tool.toolBuilder().itemId(0).name("pick").efficiency(50).durability(15).toolType(ToolType.AXE).build();
        Tool pick2 = Tool.toolBuilder().itemId(0).name("pick").efficiency(50).durability(15).toolType(ToolType.PICKAXE).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(pick, pick2),
                () -> Assertions.assertNotEquals(pick, axe),
                () -> Assertions.assertEquals(pick, pick),
                () -> Assertions.assertNotEquals(pick, new Object()),
                () -> Assertions.assertEquals(pick.hashCode(), pick2.hashCode()),
                () -> Assertions.assertNotEquals(pick.hashCode(), axe.hashCode()),
                () -> Assertions.assertEquals(pick.hashCode(), pick.hashCode())
        );
    }

    @Test
    public void testSetDurability(){
        Tool pick = Tool.toolBuilder().itemId(0).name("pick").efficiency(50).durability(15).toolType(ToolType.PICKAXE).build();

        Assertions.assertAll(
                () -> pick.setDurability(0),
                () -> Assertions.assertEquals(pick.getDurability(), 0),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> pick.setDurability(-1)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> pick.setDurability(1))
        );
    }
}
