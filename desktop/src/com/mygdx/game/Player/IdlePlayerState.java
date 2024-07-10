package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public class IdlePlayerState implements PlayerState {

    private final Player player;

    public IdlePlayerState(Player player){
        this.player = Objects.requireNonNull(player, "Player is null.");
    }

    @Override
    public String getName() {
        return "idle";
    }

    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot){

    }
}
