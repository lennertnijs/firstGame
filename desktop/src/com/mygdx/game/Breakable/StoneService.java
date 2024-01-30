package com.mygdx.game.Breakable;

import com.mygdx.game.Item.ToolInstance;
import com.mygdx.game.Item.ToolType;

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

    public void interact(Stone stone, ToolInstance toolInstance){
        if(toolInstance.getToolType() == ToolType.PICKAXE){
            stone.setHealthPoints(1);
        }
    }
}
