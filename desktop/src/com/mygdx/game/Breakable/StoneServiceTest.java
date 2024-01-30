package com.mygdx.game.Breakable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;
import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StoneServiceTest {

    @Test
    public void testGetStoneAmount(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        int stoneAmount = stoneService.getStoneAmount();
        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneAmount, 4)
        );
    }

    @Test
    public void testGetStones(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        List<Stone> expectedStones = generateListOfStones();
        List<Stone> stones = stoneService.getStones();

        Assertions.assertAll(
                () -> Assertions.assertEquals(stones.get(0), expectedStones.get(0)),
                () -> Assertions.assertEquals(stones.get(1), expectedStones.get(1)),
                () -> Assertions.assertEquals(stones.get(2), expectedStones.get(2)),
                () -> Assertions.assertEquals(stones.get(3), expectedStones.get(3))
        );
    }


    @Test
    public void testAddStone(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        Stone stone = Stone.builder().position(Position.builder().x(5).y(5).build())
                .healthPoints(25).hardness(100).build();

        List<Stone> stones = stoneService.getStones();
        stoneService.addStone(stone);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stones.size(), 5),
                () -> Assertions.assertEquals(stones.get(4), stone)
        );
    }


    @Test
    public void testRemoveStone(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        List<Stone> stones = stoneService.getStones();
        List<Stone> expectedStones = generateListOfStones();

        stoneService.removeStone(expectedStones.get(2));
        Assertions.assertAll(
                () -> Assertions.assertEquals(stones.size(), 3),
                () -> Assertions.assertEquals(stones.get(2), expectedStones.get(3))
        );
    }


    @Test
    public void testMine(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        List<Stone> stones = stoneService.getStones();

        stoneService.mine(stones.get(0), 17);
        stoneService.mine(stones.get(2), 5);
        stoneService.mine(stones.get(3), 25); // removed because hp = 0
        Assertions.assertAll(
                () -> Assertions.assertEquals(stones.get(0).getHealthPoints(), 8),
                () -> Assertions.assertEquals(stones.get(1).getHealthPoints(), 25),
                () -> Assertions.assertEquals(stones.get(2).getHealthPoints(), 20),
                () -> Assertions.assertEquals(stones.size(), 3)
        );
    }


    @Test
    public void testCollidesWithStone(){
        Gdx.files = new Lwjgl3Files();
        StoneService stoneService = new StoneService();

        List<Stone> stones = stoneService.getStones();

        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneService.collidesWithStone(1232, 1232), stones.get(0)),
                () -> Assertions.assertEquals(stoneService.collidesWithStone(720, 1232), stones.get(2)),
                () -> Assertions.assertEquals(stoneService.collidesWithStone(700, 700), stones.get(1)),
                () -> Assertions.assertNull(stoneService.collidesWithStone(699, 700)),
                () -> Assertions.assertNull(stoneService.collidesWithStone(1233, 1232))

        );
    }




    private List<Stone> generateListOfStones(){
        List<Stone> stones = new ArrayList<>();
        Stone stone1 = Stone.builder().position(Position.builder().x(1200).y(1200).build())
                .healthPoints(25).hardness(100).build();
        stones.add(stone1);
        Stone stone2 = Stone.builder().position(Position.builder().x(700).y(700).build())
                .healthPoints(25).hardness(100).build();
        stones.add(stone2);
        Stone stone3 = Stone.builder().position(Position.builder().x(700).y(1200).build())
                .healthPoints(25).hardness(100).build();
        stones.add(stone3);
        Stone stone4 = Stone.builder().position(Position.builder().x(1200).y(700).build())
                .healthPoints(25).hardness(100).build();
        stones.add(stone4);
        return stones;
    }
}
