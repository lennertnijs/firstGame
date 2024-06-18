package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

public class IdlePlayerState implements PlayerState {

    private final Player player;

    public IdlePlayerState(Player player){
        this.player = player;
    }

    @Override
    public String getState() {
        return "idle";
    }

    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot){

    }
}
