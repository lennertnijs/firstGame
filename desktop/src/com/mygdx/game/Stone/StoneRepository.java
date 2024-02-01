package com.mygdx.game.Stone;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoneRepository {

    private final List<Stone> stones;

    protected StoneRepository(){
        this.stones = new StoneDAO().loadStones().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected List<Stone> getStones(){
        return stones;
    }

    protected int getRepositorySize(){
        return stones.size();
    }

    protected void add(Stone stone){
        stones.add(stone);
    }

    protected void remove(Stone stone){
        stones.remove(stone);
    }
}
