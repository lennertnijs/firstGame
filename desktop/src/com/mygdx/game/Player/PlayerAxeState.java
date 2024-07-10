package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

public final class PlayerAxeState implements PlayerState{

    private final Player player;
    private double delta;

    public PlayerAxeState(Player player){
        this.player = player;
        this.delta = 0;
    }

    @Override
    public String getName() {
        return "axe";
    }

    @Override
    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot) {
        this.delta += delta;
        handleStateChange();
    }

    private void handleStateChange(){
        if(delta >= 1000){
            player.changeState(new IdlePlayerState(player));
        }
    }
}
