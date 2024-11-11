package Inventory;

import com.mygdx.game.inventory.Tool;
import com.mygdx.game.inventory.ToolType;
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
                .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
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
                .duration(1000)
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
                .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
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
                        .duration(1000)
                        .build());
    }

    @Test
    public void testConstructorWithDurabilityNotSet(){
        Tool defaultDurabilityTool = Tool.builder()
                                    .efficiency(efficiency)
                                    .name(name)
                                    .maxDurability(maxDurability)
                                    .toolType(type)
                .duration(1000)
                                    .build();
        assertEquals(maxDurability, defaultDurabilityTool.currentDurability());
    }

    @Test
    public void testConstructorWithMaxDurabilityNotSet(){
        assertThrows(IllegalArgumentException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .toolType(type)
                        .duration(1000)
                        .build());
    }

    @Test
    public void testConstructorWithToolTypeNotSet(){
        assertThrows(NullPointerException.class,
                () -> Tool.builder()
                        .name(name)
                        .efficiency(efficiency)
                        .durability(durability)
                        .duration(1000)
                        .maxDurability(maxDurability)
                        .build());
    }

    @Test
    public void testGetEfficiency(){
        assertEquals(15, tool.efficiency());
    }

    @Test
    public void testGetDurability(){
        assertEquals(2000, tool.currentDurability());
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
    public void testEquals(){ // equal if the same name
        Tool tool1 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type)                .duration(1000).build();
        Tool tool2 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        // equals
        assertEquals(tool1, tool1);
        //not equals
        assertNotEquals(tool1, tool2);
        assertNotEquals(tool1, new Object());
        assertNotEquals(tool1, null);
    }

    @Test
    public void testHashCode() { // not equals if different name
        Tool tool1 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type)                .duration(1000).build();
        Tool tool2 = Tool.builder().name(name).efficiency(efficiency).durability(durability)
                .maxDurability(maxDurability).toolType(type).build();
        // equals
        assertEquals(tool1, tool1);
        //not equals
        assertNotEquals(tool1, tool2);
        assertNotEquals(tool1, new Object());
        assertNotEquals(tool1, null);
    }

    @Test
    public void testToString(){
        String expected = "Tool[name=Pickaxe, efficiency=15, durability=2000, maxDurability=2500, type=PICKAXE]";
        assertEquals(expected, tool.toString());
    }
}
