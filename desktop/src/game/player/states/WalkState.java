package game.player.states;

import game.player.Player;
import game.util.Vec2;

import java.util.Objects;

public final class WalkState implements PlayerState {

    private final Player player;

    public WalkState(Player player){
        this.player = Objects.requireNonNull(player);
    }

    public String getName(){
        return "walk";
    }

    public void update(double delta){
        Vec2 current = player.getPosition();
        int movement = player.getStats().getSpeed();
        Vec2 next = switch(player.getDirection()){
            case UP -> new Vec2(current.x(), current.y() + movement);
            case RIGHT -> new Vec2(current.x() + movement, current.y());
            case DOWN -> new Vec2(current.x(), current.y() - movement);
            case LEFT -> new Vec2(current.x() - movement, current.y());
        };
        // check for free
        player.setPosition(next);
    }
}
