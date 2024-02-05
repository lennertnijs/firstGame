package com.mygdx.game.Controller;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Stone.Stone;
import com.mygdx.game.Stone.StoneService;

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

    public boolean pointCollidesWithStone(Position position){
        Objects.requireNonNull(position, "Cannot check collision with a null position");
        return stoneService.pointCollidesWithStone(position.getX(), position.getY());
    }

    public boolean hitBoxCollidesWithStone(Position position, int width, int height){
        Objects.requireNonNull(position, "Cannot check collision with a null position");
        if(width < 0 || height < 0){
            throw new IllegalArgumentException("Cannot check collision because the measurements are negative");
        }
        return stoneService.hitBoxCollidesWithStone(position.getX(), position.getY(), width, height);
    }

    public Stone getCollisionStoneFromPoint(Position position){
        Objects.requireNonNull(position, "Cannot check collision with a null position");
        return stoneService.getCollidingStoneFromPoint(position.getX(), position.getY());
    }

    public Stone getCollisionStoneFromHitBox(Position position, int width, int height){
        Objects.requireNonNull(position, "Cannot check collision with a null position");
        if(width < 0 || height < 0){
            throw new IllegalArgumentException("Cannot check collision because the measurements are negative");
        }
        return stoneService.getCollidingStoneFromHitBox(position.getX(), position.getY(), width, height);
    }
}
