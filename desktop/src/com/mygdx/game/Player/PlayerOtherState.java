package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

public class PlayerOtherState implements PlayerState{

    private final Player player;
    private double delta;

    public PlayerOtherState(Player player){
        this.player = player;
    }
    @Override
    public String getState() {
        return "mine";
    }

    @Override
    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot) {
        this.delta += delta;
        updateState();
    }

    private void updateState(){
        if(delta >= 1000){
            player.changeState(new IdlePlayerState(player));
        }
    }
}
