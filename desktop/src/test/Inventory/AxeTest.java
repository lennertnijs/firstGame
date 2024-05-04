package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.Inventory.Axe;
import com.mygdx.game.V2.Inventory.ItemTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AxeTest {

    private ItemTemplate template;
    private int efficiency;
    private int durability;
    private Axe axe;

    @BeforeEach
    public void initialise(){
        template = new ItemTemplate("Stone", "A Stone.", Mockito.mock(Texture.class), 64);
        efficiency = 15;
        durability = 2000;

        axe = new Axe(template, efficiency, durability);
    }

    @Test
    public void testEquals(){
        Axe Axe1 = new Axe(template, efficiency, durability);
        Axe Axe2 = new Axe(template, efficiency, durability);
        Axe Axe3 = new Axe(template, efficiency, durability);
        Axe diffAxe = new Axe(template, efficiency, 5);
        // reflexive
        assertEquals(Axe1, Axe1);
        // symmetrical
        assertEquals(Axe1, Axe2);
        assertEquals(Axe2, Axe1);
        // transitive
        assertEquals(Axe1, Axe2);
        assertEquals(Axe2, Axe3);
        assertEquals(Axe1, Axe3);
        // not equals
        assertNotEquals(Axe1, diffAxe);
        assertNotEquals(Axe1, new Object());
        assertNotEquals(Axe1, null);
    }

    @Test
    public void testHashCode(){
        Axe Axe1 = new Axe(template, efficiency, durability);
        Axe Axe2 = new Axe(template, efficiency, durability);
        Axe Axe3 = new Axe(template, efficiency, durability);
        Axe diffAxe = new Axe(template, efficiency, 5);
        // reflexive
        assertEquals(Axe1.hashCode(), Axe1.hashCode());
        // symmetrical
        assertEquals(Axe1.hashCode(), Axe2.hashCode());
        assertEquals(Axe2.hashCode(), Axe1.hashCode());
        // transitive
        assertEquals(Axe1.hashCode(), Axe2.hashCode());
        assertEquals(Axe2.hashCode(), Axe3.hashCode());
        assertEquals(Axe1.hashCode(), Axe3.hashCode());
        // not equals
        assertNotEquals(Axe1.hashCode(), diffAxe.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Axe[amount=1, efficiency=15, durability=2000]";
        assertEquals(expected, axe.toString());
    }

}
