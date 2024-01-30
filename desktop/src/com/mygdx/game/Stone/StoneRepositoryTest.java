package com.mygdx.game.Stone;

import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoneRepositoryTest {

    @Test
    public void testRepository(){
        Position position1 = Position.builder().x(500).y(500).build();
        Position position2 = Position.builder().x(250).y(250).build();

        Stone stone1 = Stone.builder().position(position1).healthPoints(25).hardness(50).build();
        Stone stone2 = Stone.builder().position(position2).healthPoints(50).hardness(100).build();

        List<Stone> stones = new ArrayList<>(Arrays.asList(stone1, stone2, null));

        StoneRepository stoneRepository = new StoneRepository(stones);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneRepository.getRepositorySize(), 2),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(0), stone1),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(1), stone2),

                () -> stoneRepository.add(stone2),
                () -> Assertions.assertEquals(stoneRepository.getRepositorySize(), 3),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(2), stone2),

                () -> stoneRepository.remove(stone2),
                () -> Assertions.assertEquals(stoneRepository.getRepositorySize(), 2),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(0), stone1),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(1), stone2),

                () -> stoneRepository.remove(stone2),
                () -> Assertions.assertEquals(stoneRepository.getRepositorySize(), 1),
                () -> Assertions.assertEquals(stoneRepository.getStones().get(0), stone1)
        );

    }
}
