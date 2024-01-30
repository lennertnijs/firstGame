package com.mygdx.game.Breakable;

import com.mygdx.game.Entity.Position;

import java.util.List;
import java.util.Objects;

public class StoneController {

    private final StoneService stoneService;

    public StoneController(){
        this.stoneService = new StoneService();
    }

    public int getStoneAmount(){
        return stoneService.getStoneAmount();
    }

    public List<Stone> getAllStones(){
        return stoneService.getStones();
    }

    public void addStone(Stone stone){
        Objects.requireNonNull(stone, "Cannot add a null stone");
        stoneService.addStone(stone);
    }

    public void removeStone(Stone stone){
        Objects.requireNonNull(stone, "Cannot remove a null stone");
        stoneService.removeStone(stone);
    }

    public void mineStone(Stone stone, float damage){
        Objects.requireNonNull(stone, "Cannot mine a null stone");
        if(damage < 0){
            throw new IllegalArgumentException("The damage to a stone cannot be negative");
        }
        stoneService.mine(stone, damage);
    }

    public Stone pointCollidesWithStone(Position position){
        Objects.requireNonNull(position, "Cannot check collision with a null position");
        return stoneService.collidesWithStone(position.getX(), position.getY());
    }
}
