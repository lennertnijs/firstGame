package com.mygdx.game.Player;

import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

import java.util.Objects;

public class WalkState implements PlayerState{

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
