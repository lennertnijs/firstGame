package com.mygdx.game.player.states;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.IdleState;
import com.mygdx.game.player.states.PlayerState;

public final class UseItemState implements PlayerState {

    private final Player player;
    private double delta;

    public UseItemState(Player player){
        this.player = player;
        this.delta = 0;
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
}
