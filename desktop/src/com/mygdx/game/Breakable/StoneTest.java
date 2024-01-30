package com.mygdx.game.Breakable;

import com.mygdx.game.Breakable.Stone;
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
        Stone stone = generateStone(500);
        float damage = stone.getHealthPoints();

        Assertions.assertAll(
                () -> Assertions.assertEquals(stone.getHealthPoints(), 25),

                () -> stone.damage(4.5F),
                () -> Assertions.assertEquals(stone.getHealthPoints(), 20.5),

                () -> stone.damage(21),
                () -> Assertions.assertEquals(stone.getHealthPoints(), -0.5)
        );
    }

    @Test
    public void testDamageInvalid(){

    }

    private Stone generateStone(int hardness){
        Position position = Position.builder().x(500).y(500).build();
        return Stone.builder().position(position).hardness(hardness).healthPoints(25).build();
    }
}
