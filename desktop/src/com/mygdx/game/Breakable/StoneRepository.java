package com.mygdx.game.Breakable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoneRepository {

    private final List<Stone> stones;

    protected StoneRepository(List<Stone> stones){
        this.stones = stones.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected List<Stone> getStones(){
        return stones;
    }

    protected int getRepositorySize(){
        return stones.size();
    }

    protected void removeStone(Stone stone){
        stones.remove(stone);
    }
}
