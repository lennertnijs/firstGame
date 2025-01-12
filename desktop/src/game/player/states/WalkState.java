package game.player.states;

import game.player.Player;
import game.util.Vec2;

import java.util.Objects;

public final class WalkState implements PlayerState{

    private final Player player;
    private final Vec2 goal;

    public WalkState(Player player, Vec2 goal){
        this.player = Objects.requireNonNull(player);
        this.goal = Objects.requireNonNull(goal);
    }

    @Override
    public String getName() {
        return "walk";
    }

    @Override
    public void update(double delta) {
        int movement = player.getStats().getSpeed();
        int xDiff = player.getPosition().x() - goal.x();
        int yDiff = player.getPosition().y() - goal.y();
        if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= movement){
            player.setPosition(goal);
        }else{
            Vec2 movementVector = Vec2.createFromTo(player.getPosition(), goal).scaleToSize(movement);
            player.setPosition(player.getPosition().add(movementVector));
        }
        // check for free
        if(player.getPosition().equals(goal)){
            player.changeState(new IdleState());
        }
    }
}
