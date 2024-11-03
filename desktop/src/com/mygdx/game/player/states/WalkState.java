package com.mygdx.game.player.states;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.PlayerState;
import com.mygdx.game.util.Vec2;
import com.mygdx.game.util.MovementUtilMethods;

import java.util.Objects;

public class WalkState implements PlayerState {

    private final Player player;

    public WalkState(Player player){
        this.player = Objects.requireNonNull(player);
    }

    public void update(double delta){
        int amount = 5 * player.getStats().getSpeed();
        Vec2 movement = MovementUtilMethods.calculateNextPosition(player.getPosition(), player.getDirection(), amount);
        player.setPosition(movement);
    }
}
