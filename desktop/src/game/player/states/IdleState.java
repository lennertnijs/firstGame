package game.player.states;

import game.player.Player;
import game.util.Vec2;

public final class IdleState implements PlayerState {

    private final Player player;

    public IdleState(Player player){
        this.player = player;
    }

    public String getName(){
        return "idle";
    }

    public Vec2 getNextPosition(){
        return player.getPosition();
    }
    public void update(double delta){

    }
}
