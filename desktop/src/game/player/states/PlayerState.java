package game.player.states;

import game.util.Vec2;

public interface PlayerState {

    String getName();
    Vec2 getNextPosition();
    void update(double delta);
}
