package com.mygdx.game.Breakable;

import java.util.ArrayList;
import java.util.List;

public class StoneService {

    private final StoneRepository stoneRepository;

    public StoneService(){
        stoneRepository = new StoneRepository(new ArrayList<>());
    }

    public List<Stone> getStones(){
        return stoneRepository.getStones();
    }

    public int getStoneAmount(){
        return stoneRepository.getRepositorySize();
    }

    public void mine(Stone stone, float damage){
        stone.damage(damage);
        if(stone.isBroken()){
            stoneRepository.removeStone(stone);
        }
    }
}
