package Inventory;

import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolTest {

    private final String name = "Pickaxe";
    private final int efficiency = 15;
    private final int durability = 2000;
    private final int maxDurability = 2500;
    private final ToolType type = ToolType.PICKAXE;
    private Tool tool;

    @BeforeEach
    public void initialise(){
        tool = Tool.builder()
                .name(name)
                .efficiency(efficiency)
                .durability(durability)
                .maxDurability(maxDurability)
                .toolType(type)
                .build();
    }

    @Test
    public void testBuilderWithNullName(){
        assertThrows(NullPointerException.class,
                () -> Tool.builder()
                        .name(null)
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testBuilderWithNegativeEfficiency(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(-1)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testBuilderWithZeroEfficiency(){ // allowed
        Tool.builder()
                .name(name)
                .efficiency(0)
                .durability(durability)
                .maxDurability(maxDurability)
                .toolType(type)
                .build();
    }

    @Test
    public void testBuilderWithZeroDurability(){ // allowed
        Tool.builder()
                .name(name)
                .efficiency(efficiency)
                .durability(0)
                .maxDurability(maxDurability)
                .toolType(type)
                .build();
    }

    @Test
    public void testBuilderWithDurabilityBiggerThanMax(){ // allowed
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(maxDurability + 1)
                        .maxDurability(maxDurability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testBuilderWithNegativeMaxDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(-1)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testBuilderWithZeroMaxDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(0)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testConstructorWithNullType(){
        assertThrows(NullPointerException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .toolType(null)
                        .build());
    }

    @Test
    public void testConstructorWithNameNotSet(){
        assertThrows(NullPointerException.class,
                () -> Tool.builder()
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testConstructorWithEfficiencyNotSet(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testConstructorWithDurabilityNotSet(){
        Tool defaultDurabilityTool = Tool.builder()
                                    .efficiency(efficiency)
                                    .name(name)
                                    .maxDurability(maxDurability)
                                    .toolType(type)
                                    .build();
        assertEquals(maxDurability, defaultDurabilityTool.getDurability());
    }

    @Test
    public void testConstructorWithMaxDurabilityNotSet(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .toolType(type)
                        .build());
    }

    @Test
    public void testConstructorWithToolTypeNotSet(){
        assertThrows(NullPointerException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .maxDurability(maxDurability)
                        .build());
    }

    @Test
    public void testGetEfficiency(){
        assertEquals(15, tool.efficiency());
    }

    @Test
    public void testGetDurability(){
        assertEquals(2000, tool.getDurability());
    }

    @Test
    public void testSetDurability(){
        tool.setDurability(1000);
        assertEquals(1000, tool.getDurability());
    }

    @Test
    public void testSetDurabilityToNegative(){
        assertThrows(IllegalArgumentException.class,
                () -> tool.setDurability(-1));
    }

    @Test
    public void testSetDurabilityToZero(){ // allowed
        tool.setDurability(0);
    }

    @Test
    public void testSetDurabilityToBiggerThanMaxDurability(){
        assertThrows(IllegalArgumentException.class,
                () -> tool.setDurability(maxDurability + 1));
    }

    @Test
    public void testGetMaxDurability(){
        assertEquals(maxDurability, tool.maxDurability());
    }

    @Test
    public void testGetToolType(){
        assertEquals(type, tool.type());
    }

    @Test
    public void testCopy(){
        Tool copy = tool.copy();
        assertEquals(copy, tool);
        copy.setDurability(durability/2);
        assertNotEquals(copy.getDurability(), tool.getDurability());
    }
    
    @Test
    public void testEquals(){ // equal if the same name
        Tool t1 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool t2 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool t3 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        // reflexive
        assertEquals(t1, t1);
        // symmetrical
        assertEquals(t1, t2);
        assertEquals(t2, t1);
        // transitive
        assertEquals(t1, t2);
        assertEquals(t2, t3);
        assertEquals(t1, t3);

        // not equals
        Tool diffName = Tool.builder().name("diff").efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffEfficiency = Tool.builder().name(name).efficiency(2*efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffDurability = Tool.builder().name(name).efficiency(efficiency).durability(durability+1)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffMaxDurability = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(2*maxDurability).toolType(type).build();
        Tool diffToolType = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(ToolType.AXE).build();
        assertNotEquals(t1, diffName);
        // equal again, same name
        assertEquals(t1, diffEfficiency);
        assertEquals(t1, diffDurability);
        assertEquals(t1, diffMaxDurability);
        assertEquals(t1, diffToolType);
        // more not equals
        assertNotEquals(t1, new Object());
        assertNotEquals(t1, null);
    }

    @Test
    public void testHashCode(){ // not equals if different name
        Tool t1 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool t2 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool t3 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        // reflexive
        assertEquals(t1.hashCode(), t1.hashCode());
        // symmetrical
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(t2.hashCode(), t1.hashCode());
        // transitive
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(t2.hashCode(), t3.hashCode());
        assertEquals(t1.hashCode(), t3.hashCode());

        // not equals
        Tool diffName = Tool.builder().name("diff").efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffEfficiency = Tool.builder().name(name).efficiency(2*efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffDurability = Tool.builder().name(name).efficiency(efficiency).durability(durability+1)
                .maxDurability(maxDurability).toolType(type).build();
        Tool diffMaxDurability = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(2*maxDurability).toolType(type).build();
        Tool diffToolType = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(ToolType.AXE).build();
        assertNotEquals(t1.hashCode(), diffName.hashCode());
        // equal again, same name
        assertEquals(t1.hashCode(), diffEfficiency.hashCode());
        assertEquals(t1.hashCode(), diffDurability.hashCode());
        assertEquals(t1.hashCode(), diffMaxDurability.hashCode());
        assertEquals(t1.hashCode(), diffToolType.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Tool[name=Pickaxe, efficiency=15, durability=2000, maxDurability=2500, type=PICKAXE]";
        assertEquals(expected, tool.toString());
    }
}
