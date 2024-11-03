package com.mygdx.game.player.states;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.IdleState;
import com.mygdx.game.player.states.PlayerState;

public final class UseItemState implements PlayerState {

    private final Player player;
    private final String activity;
    private double delta;

    public UseItemState(Player player, String activity){
        this.player = player;
        this.delta = 0;
        this.activity = activity;
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
        handleStateChange();
    }

    private void handleStateChange(){
        if(delta >= player.getActiveItem().usageDuration()){
            player.changeState(new IdleState());
        }
    }

    public String getActivityName(){
        return activity;
    }
}
