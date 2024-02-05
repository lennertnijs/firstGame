package com.mygdx.game.Stone;

import java.util.List;

import static com.mygdx.game.Constants.STONE_HEIGHT;
import static com.mygdx.game.Constants.STONE_WIDTH;

public class StoneService {

    private final StoneRepository stoneRepository;

    public StoneService(){
        stoneRepository = new StoneRepository();
    }

    public int getStoneAmount(){
        return stoneRepository.getRepositorySize();
    }

    public List<Stone> getStones(){
        return stoneRepository.getStones();
    }

    public void addStone(Stone stone){
        stoneRepository.add(stone);
    }

    public void removeStone(Stone stone){
        stoneRepository.remove(stone);
    }

    public void mine(Stone stone, float damage){
        boolean stoneBroken = stone.getHealthPoints() - damage <= 0;
        if(stoneBroken){
            stoneRepository.remove(stone);
        }
        stone.damage(damage);
    }

    /**
     * Checks whether the given point at the given coordinates collides with any existing stones.
     */
    public boolean pointCollidesWithStone(int x, int y){
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
    public boolean hitBoxCollidesWithStone(int x, int y, int width, int height){
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

    /**
     * Finds the colliding stone from the given point, if there is one.
     * @return The stone if colliding, null otherwise
     */
    public Stone getCollidingStoneFromPoint(int x, int y){
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

    /**
     * Finds the colliding stone from the given hit box, if there is one.
     * @return The stone if collision, null otherwise
     */
    public Stone getCollidingStoneFromHitBox(int x, int y, int width, int height){
        for(Stone stone: stoneRepository.getStones()){
            int stoneX = stone.getPosition().getX();
            int stoneY = stone.getPosition().getY();
            boolean collides = x + width >= stoneX && x <= stoneX + STONE_WIDTH &&
                    y + height >= stoneY && y <= stoneY + STONE_HEIGHT;
            if(collides){
                return stone;
            }
        }
        return null;
    }
    // need stone spawning mechanic
}
