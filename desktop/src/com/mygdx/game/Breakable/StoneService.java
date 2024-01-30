package com.mygdx.game.Breakable;

import java.util.List;

import static com.mygdx.game.Constants.STONE_HEIGHT;
import static com.mygdx.game.Constants.STONE_WIDTH;

public class StoneService {

    private final StoneRepository stoneRepository;

    public StoneService(){
        stoneRepository = new StoneRepository(new StoneDAO().loadStones());
    }

    protected int getStoneAmount(){
        return stoneRepository.getRepositorySize();
    }

    protected List<Stone> getStones(){
        return stoneRepository.getStones();
    }

    protected void addStone(Stone stone){
        stoneRepository.add(stone);
    }

    protected void removeStone(Stone stone){
        stoneRepository.remove(stone);
    }

    protected void mine(Stone stone, float damage){
        boolean stoneBroken = stone.getHealthPoints() - damage <= 0;
        if(stoneBroken){
            stoneRepository.remove(stone);
        }
        stone.damage(damage);
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
