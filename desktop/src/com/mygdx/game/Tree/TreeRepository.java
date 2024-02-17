package com.mygdx.game.Tree;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeRepository {

    private final List<Tree> treeList;

    protected TreeRepository(){
        this.treeList = new TreeDAO().readTrees().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected List<Tree> getTrees(){
        return this.treeList;
    }

    protected int getTreeAmount(){
        return treeList.size();
    }

    protected void add(Tree tree){
        treeList.add(tree);
    }

    protected void remove(Tree tree){
        treeList.remove(tree);
    }
}
