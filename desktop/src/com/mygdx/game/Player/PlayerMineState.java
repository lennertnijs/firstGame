package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class PlayerMineState implements PlayerState {

    private final Player player;
    private double delta;

    public PlayerMineState(Player player){
        this.player = Objects.requireNonNull(player, "Player is null.");
    }
    @Override
    public String getName() {
        return "mine";
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
