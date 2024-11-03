package Loot;

import com.mygdx.game.loot.Loot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LootTest {

    private final String name = "Wood";
    private final int amount = 32;
    private final Loot loot = new Loot(name, amount);

    @Test
    public void testConstructorWithNullName(){
        assertThrows(NullPointerException.class,
                () -> new Loot(null, amount));
    }

    @Test
    public void testConstructorWithNegativeAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Loot(name, -1));
    }

    @Test
    public void testConstructorWithZeroAmount(){
        assertThrows(IllegalArgumentException.class,
                () -> new Loot(name, 0));
    }

    @Test
    public void testGetName(){
        assertEquals(name, loot.name());
    }

    @Test
    public void testGetAmount(){
        assertEquals(amount, loot.amount());
    }

    @Test
    public void testEquals(){
        Loot loot1 = new Loot(name, amount);
        Loot loot2 = new Loot(name, amount);
        Loot loot3 = new Loot(name, amount);
        // reflexive
        assertEquals(loot1, loot1);
        // symmetrical
        assertEquals(loot1, loot2);
        assertEquals(loot2, loot1);
        // transitive
        assertEquals(loot1, loot2);
        assertEquals(loot2, loot3);
        assertEquals(loot1, loot3);

        // not equals
        Loot diffName = new Loot("Diff name", amount);
        Loot diffAmount = new Loot(name, 16);
        assertNotEquals(loot1, diffName);
        assertNotEquals(loot1, diffAmount);
        assertNotEquals(loot1, new Object());
        assertNotEquals(loot1, null);
    }

    @Test
    public void testHashCode(){
        Loot loot1 = new Loot(name, amount);
        Loot loot2 = new Loot(name, amount);
        Loot loot3 = new Loot(name, amount);
        // reflexive
        assertEquals(loot1.hashCode(), loot1.hashCode());
        // symmetrical
        assertEquals(loot1.hashCode(), loot2.hashCode());
        assertEquals(loot2.hashCode(), loot1.hashCode());
        // transitive
        assertEquals(loot1.hashCode(), loot2.hashCode());
        assertEquals(loot2.hashCode(), loot3.hashCode());
        assertEquals(loot1.hashCode(), loot3.hashCode());

        // not equals
        Loot diffName = new Loot("Diff name", amount);
        Loot diffAmount = new Loot(name, 16);
        assertNotEquals(loot1.hashCode(), diffName.hashCode());
        assertNotEquals(loot1.hashCode(), diffAmount.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "loot[name=Wood, amount=32]";
        assertEquals(expected, loot.toString());
    }
}
