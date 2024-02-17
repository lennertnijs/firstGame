package com.mygdx.game.Stone;

import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTest {

    @Test
    public void testCreation(){
        Position position = Position.builder().x(500).y(500).build();
        Tree tree = Tree.create(position, 250, 100);
        Assertions.assertAll(
                () -> Assertions.assertEquals(tree.getPosition(), position),
                () -> Assertions.assertEquals(tree.getHealth(), 250),
                () -> Assertions.assertEquals(tree.getHardness(), 100)
        );
    }

    @Test
    public void testInvalidCreation(){
        Position position = Position.builder().x(500).y(500).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> Tree.create(null, 250, 100)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> Tree.create(position, 0, 100)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> Tree.create(position, 250, 0))
        );
    }

    @Test
    public void testDamageTree(){
        Position position = Position.builder().x(500).y(500).build();
        Tree tree = Tree.create(position, 250, 100);
        Assertions.assertAll(
                () -> tree.damage(20.5f),
                () -> Assertions.assertEquals(tree.getHealth(), 229.5f),
                () -> tree.damage(219),
                () -> Assertions.assertEquals(tree.getHealth(), 10.5f),
                () -> tree.damage(500),
                () -> Assertions.assertEquals(tree.getHealth(), 0),
                () -> tree.damage(10),
                () -> Assertions.assertEquals(tree.getHealth(), 0),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> tree.damage(-1))
        );
    }

    @Test
    public void testIsBroken(){
        Position position = Position.builder().x(500).y(500).build();
        Tree tree = Tree.create(position, 250, 100);
        Assertions.assertAll(
                () -> Assertions.assertFalse(tree.isBroken()),
                () -> tree.damage(250),
                () -> Assertions.assertTrue(tree.isBroken())
        );
    }
}
