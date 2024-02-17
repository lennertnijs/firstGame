package com.mygdx.game.Tree;

import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BreakableTest {

    @Test
    public void testCreation(){
        Position position = Position.builder().x(500).y(500).build();
        Breakable breakable = Breakable.create(position, 250, 100, BreakableType.TREE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(breakable.getPosition(), position),
                () -> Assertions.assertEquals(breakable.getHealth(), 250),
                () -> Assertions.assertEquals(breakable.getHardness(), 100)
        );
    }

    @Test
    public void testInvalidCreation(){
        Position position = Position.builder().x(500).y(500).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> Breakable.create(null, 250, 100, BreakableType.TREE)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> Breakable.create(position, 0, 100, BreakableType.TREE)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> Breakable.create(position, 250, 0, BreakableType.TREE))
        );
    }

    @Test
    public void testDamageTree(){
        Position position = Position.builder().x(500).y(500).build();
        Breakable breakable = Breakable.create(position, 250, 100, BreakableType.TREE);
        Assertions.assertAll(
                () -> breakable.damage(20.5f),
                () -> Assertions.assertEquals(breakable.getHealth(), 229.5f),
                () -> breakable.damage(219),
                () -> Assertions.assertEquals(breakable.getHealth(), 10.5f),
                () -> breakable.damage(500),
                () -> Assertions.assertEquals(breakable.getHealth(), 0),
                () -> breakable.damage(10),
                () -> Assertions.assertEquals(breakable.getHealth(), 0),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> breakable.damage(-1))
        );
    }

    @Test
    public void testIsBroken(){
        Position position = Position.builder().x(500).y(500).build();
        Breakable breakable = Breakable.create(position, 250, 100, BreakableType.TREE);
        Assertions.assertAll(
                () -> Assertions.assertFalse(breakable.isBroken()),
                () -> breakable.damage(250),
                () -> Assertions.assertTrue(breakable.isBroken())
        );
    }

    @Test
    public void testEqualsAndHashCode(){
        Position position1 = Position.builder().x(500).y(500).build();
        Position position2 = Position.builder().x(250).y(250).build();
        Breakable breakable1 = Breakable.create(position1, 250, 100, BreakableType.TREE);
        Breakable breakable2 = Breakable.create(position2, 250, 100, BreakableType.TREE);
        Breakable breakable3 = Breakable.create(position1, 250, 100, BreakableType.TREE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(breakable1, breakable3),
                () -> Assertions.assertEquals(breakable1.hashCode(), breakable3.hashCode()),

                () -> Assertions.assertEquals(breakable1, breakable1),
                () -> Assertions.assertEquals(breakable1.hashCode(), breakable1.hashCode()),

                () -> Assertions.assertNotEquals(breakable1, breakable2),
                () -> Assertions.assertNotEquals(breakable1.hashCode(), breakable2.hashCode()),

                () -> Assertions.assertNotEquals(breakable1, null),
                () -> Assertions.assertNotEquals(breakable1, new Object())
        );
    }
}
