package Controller;

import com.mygdx.game.Breakable.Stone;
import com.mygdx.game.Breakable.StoneController;
import com.mygdx.game.Entity.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StoneControllerTest {


    @Test
    public void testGetStoneAmount(){
        StoneController stoneController = new StoneController();
        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.getStoneAmount(), 4)
        );
    }

    @Test
    public void testGetStones(){
        StoneController stoneController = new StoneController();
        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.getAllStones(), generateListOfStones())
        );
    }

    @Test
    public void testAddStone(){
        StoneController stoneController = new StoneController();
        Position position = Position.builder().x(500).y(500).build();
        Stone stone = Stone.builder().position(position).healthPoints(50).hardness(50).build();
        stoneController.addStone(stone);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.getStoneAmount(), 5),
                () -> Assertions.assertEquals(stoneController.getAllStones().get(4), stone),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> stoneController.addStone(null))
        );
    }


    @Test
    public void testRemoveStone(){
        StoneController stoneController = new StoneController();
        List<Stone> stones = generateListOfStones();
        stoneController.removeStone(stones.get(0));

        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.getStoneAmount(), 3),
                () -> Assertions.assertEquals(stoneController.getAllStones().get(0), stones.get(1)),
                () -> Assertions.assertEquals(stoneController.getAllStones().get(1), stones.get(2)),
                () -> Assertions.assertEquals(stoneController.getAllStones().get(2), stones.get(3)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> stoneController.removeStone(null))
        );
    }


    @Test
    public void testMineStone(){
        StoneController stoneController = new StoneController();
        List<Stone> stones = stoneController.getAllStones();

        stoneController.mineStone(stones.get(0), 17);
        stoneController.mineStone(stones.get(2), 30);
        stoneController.mineStone(stones.get(1), 30);

        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.getAllStones().get(0).getHealthPoints(), 8),
                () -> Assertions.assertEquals(stoneController.getAllStones().get(1).getHealthPoints(), 25),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> stoneController.mineStone(null, 5)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> stoneController.mineStone(stones.get(0), -1))
        );
    }


    @Test
    public void testPointCollidesWithStone(){
        StoneController stoneController = new StoneController();
        List<Stone> stones = stoneController.getAllStones();

        Position collision = Position.builder().x(732).y(732).build();
        Position noCollision = Position.builder().x(1199).y(1199).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(stoneController.pointCollidesWithStone(collision), stones.get(1)),
                () -> Assertions.assertNull(stoneController.pointCollidesWithStone(noCollision)),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> stoneController.pointCollidesWithStone(null))
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
