package com.mygdx.game.r_tree;

import com.mygdx.game.util.Vec2;

public final class GameListener implements Listener{

    private final Repository repository;

    public GameListener(Repository repository){
        this.repository = repository;
    }

    @Override
    public boolean isFree(Vec2 position, int width, int height) {
        return true;
    }
}
