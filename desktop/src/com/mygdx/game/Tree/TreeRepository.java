package com.mygdx.game.Tree;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeRepository {

    private final List<Breakable> breakableList;

    protected TreeRepository(){
        this.breakableList = new BreakableDAO().readBreakables().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected List<Breakable> getTrees(){
        return this.breakableList;
    }

    protected int getTreeAmount(){
        return breakableList.size();
    }

    protected void add(Breakable breakable){
        breakableList.add(breakable);
    }

    protected void remove(Breakable breakable){
        breakableList.remove(breakable);
    }
}
