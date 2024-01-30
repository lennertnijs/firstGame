package com.mygdx.game.Stone;

import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoneTest {

    @Test
    public void testConstructor(){
        Position position = Position.builder().x(500).y(500).build();
        Stone stone = Stone.builder().position(position).hardness(2000).healthPoints(25).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(stone.getPosition(), position),
                () -> Assertions.assertEquals(stone.getHealthPoints(), 25),
                () -> Assertions.assertEquals(stone.getHardness(), 2000)
        );
    }



    @Test
    public void testConstructorInvalid(){
        Position position = Position.builder().x(500).y(500).build();
        Stone.Builder stone1 = Stone.builder().position(null).hardness(2000).healthPoints(25);
        Stone.Builder stone2 = Stone.builder().position(position).hardness(0).healthPoints(25);
        Stone.Builder stone3 = Stone.builder().position(position).hardness(2000).healthPoints(0);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, stone1::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, stone2::build),
                () -> Assertions.assertThrows(IllegalArgumentException.class, stone3::build)
        );
    }



    @Test
    public void testDamage(){
        Stone stone = generateStone(25);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stone.getHealthPoints(), 25),

                () -> stone.damage(4.5F),
                () -> Assertions.assertEquals(stone.getHealthPoints(), 20.5),

                () -> stone.damage(21),
                () -> Assertions.assertEquals(stone.getHealthPoints(), -0.5),

                () -> stone.damage(0),
                () -> Assertions.assertEquals(stone.getHealthPoints(), -0.5)
        );
    }



    @Test
    public void testDamageInvalid(){
        Stone stone = generateStone(1);

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> stone.damage(-1))
        );
    }



    @Test
    public void testEquals(){
        Stone stone1 = generateStone(25);
        Stone stone2 = generateStone(50);
        Stone stone3 = generateStone(25);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stone1, stone3),
                () -> Assertions.assertNotEquals(stone1, stone2),
                () -> Assertions.assertEquals(stone1, stone1),
                () -> Assertions.assertNotEquals(stone1, new Object()),
                () -> Assertions.assertEquals(stone1.hashCode(), stone3.hashCode()),
                () -> Assertions.assertNotEquals(stone1.hashCode(), stone2.hashCode())
        );
    }



    @Test
    public void testIsBroken(){
        Stone stone = generateStone(25);

        Assertions.assertAll(
                () -> Assertions.assertFalse(stone.isBroken()),
                () -> stone.damage(25),
                () -> Assertions.assertTrue(stone.isBroken()),
                () -> stone.damage(5),
                () -> Assertions.assertTrue(stone.isBroken())
        );
    }


    private Stone generateStone(int health){
        Position position = Position.builder().x(500).y(500).build();
        return Stone.builder().position(position).hardness(500).healthPoints(health).build();
    }
}
