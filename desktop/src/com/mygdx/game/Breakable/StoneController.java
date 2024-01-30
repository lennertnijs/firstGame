package com.mygdx.game.Breakable;

import com.mygdx.game.Entity.Position;

import java.util.List;
import java.util.Objects;

public class StoneController {

    private final StoneService stoneService;

    public StoneController(StoneService stoneService){
        Objects.requireNonNull(stoneService, "Cannot create a stone controller with a null stone service");
        this.stoneService = stoneService;
    }

    public List<Stone> getAllStones(){
        return stoneService.getStones();
    }

    public int getStoneAmount(){
        return stoneService.getStoneAmount();
    }

    public void addStone(Stone stone){
        stoneService.addStone(stone);
    }

    public void removeStone(Stone stone){
        stoneService.removeStone(stone);
    }

    public void mineStone(Stone stone, float damage){
        stoneService.mine(stone, damage);
    }

    public Stone pointCollidesWithStone(Position position){
        return stoneService.collidesWithStone(position.getX(), position.getY());
    }
}
