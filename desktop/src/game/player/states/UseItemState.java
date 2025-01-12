package game.player.states;

import game.player.Player;

import java.util.Objects;

public final class UseItemState implements PlayerState {

    private final Player player;
    private final String activity;
    private final PlayerState fallbackState;
    private final int duration;
    private double passedDelta;

    public UseItemState(Player player, String activity, PlayerState fallbackState){
        this.player = Objects.requireNonNull(player);
        this.activity = Objects.requireNonNull(activity);
        this.fallbackState = Objects.requireNonNull(fallbackState);
        this.duration = 2000;
        this.passedDelta = 0;
    }

    public String getName(){
        return activity;
    }

    public void update(double delta) {
        this.passedDelta += delta;
        if(passedDelta < duration){
            return;
        }
        player.changeState(fallbackState);
    }
}
