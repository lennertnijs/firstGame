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
     */
    protected boolean pointCollidesWithStone(int x, int y){
        for(Stone stone: stoneRepository.getStones()){
            int stoneX = stone.getPosition().getX();
            int stoneY = stone.getPosition().getY();
            boolean collides = x >= stoneX && x <= stoneX + STONE_WIDTH && y >= stoneY && y <= stoneY + STONE_HEIGHT;
            if(collides){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given hit box collides with any existing stones.
     * @return True if collision, false otherwise
     */
    protected boolean hitBoxCollidesWithStone(int x, int y, int width, int height){
        for(Stone stone: stoneRepository.getStones()){
            int stoneX = stone.getPosition().getX();
            int stoneY = stone.getPosition().getY();
            boolean collides = x + width >= stoneX && x <= stoneX + STONE_WIDTH &&
                    y + height >= stoneY && y <= stoneY + STONE_HEIGHT;
            if(collides){
                return true;
            }
        }
        return false;
    }

    // need stone spawning mechanic
}
