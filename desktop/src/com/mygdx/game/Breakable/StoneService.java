package com.mygdx.game.Breakable;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Constants.STONE_HEIGHT;
import static com.mygdx.game.Constants.STONE_WIDTH;

public class StoneService {

    private final StoneRepository stoneRepository;

    public StoneService(){
        // INITIALISE REPOSITORY!!!!
        stoneRepository = new StoneRepository(new ArrayList<>());
    }

    protected List<Stone> getStones(){
        return stoneRepository.getStones();
    }

    protected void addStone(Stone stone){
        stoneRepository.addStone(stone);
    }

    protected void removeStone(Stone stone){
        stoneRepository.removeStone(stone);
    }

    protected int getStoneAmount(){
        return stoneRepository.getRepositorySize();
    }

    protected void mine(Stone stone, float damage){
        stone.damage(damage);
        if(stone.isBroken()){
            stoneRepository.removeStone(stone);
        }
    }

    /**
     * Checks whether the given point at the given coordinates collides with any existing stones.
     * @return The stone if collision, null otherwise.
     */
    protected Stone collidesWithStone(int x, int y){
        for(Stone stone: stoneRepository.getStones()){
            int stoneX = stone.getPosition().getX();
            int stoneY = stone.getPosition().getY();
            boolean collides = x >= stoneX && x <= stoneX + STONE_WIDTH && y >= stoneY && y <= stoneY + STONE_HEIGHT;
            if(collides){
                return stone;
            }
        }
        return null;
    }

    // need stone spawning mechanic
}
