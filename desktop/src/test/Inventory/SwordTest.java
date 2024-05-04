package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import com.mygdx.game.V2.Inventory.Pickaxe;
import com.mygdx.game.V2.Inventory.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SwordTest {

    private ItemTemplate template;
    private int efficiency;
    private int durability;
    private Sword sword;

    @BeforeEach
    public void initialise(){
        template = new ItemTemplate("Stone", "A Stone.", Mockito.mock(Texture.class), 64);
        efficiency = 15;
        durability = 2000;

        sword = new Sword(template, efficiency, durability);
    }

    @Test
    public void testEquals(){
        Sword sword1 = new Sword(template, efficiency, durability);
        Sword sword2 = new Sword(template, efficiency, durability);
        Sword sword3 = new Sword(template, efficiency, durability);
        Sword diffSword = new Sword(template, efficiency, 5);
        // reflexive
        assertEquals(sword1, sword1);
        // symmetrical
        assertEquals(sword1, sword2);
        assertEquals(sword2, sword1);
        // transitive
        assertEquals(sword1, sword2);
        assertEquals(sword2, sword3);
        assertEquals(sword1, sword3);
        // not equals
        assertNotEquals(sword1, diffSword);
        assertNotEquals(sword1, new Object());
        assertNotEquals(sword1, null);
    }

    @Test
    public void testHashCode(){
        Sword sword1 = new Sword(template, efficiency, durability);
        Sword sword2 = new Sword(template, efficiency, durability);
        Sword sword3 = new Sword(template, efficiency, durability);
        Sword diffSword = new Sword(template, efficiency, 5);
        // reflexive
        assertEquals(sword1.hashCode(), sword1.hashCode());
        // symmetrical
        assertEquals(sword1.hashCode(), sword2.hashCode());
        assertEquals(sword2.hashCode(), sword1.hashCode());
        // transitive
        assertEquals(sword1.hashCode(), sword2.hashCode());
        assertEquals(sword2.hashCode(), sword3.hashCode());
        assertEquals(sword1.hashCode(), sword3.hashCode());
        // not equals
        assertNotEquals(sword1.hashCode(), diffSword.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Sword[amount=1, efficiency=15, durability=2000]";
        assertEquals(expected, sword.toString());
    }
}
