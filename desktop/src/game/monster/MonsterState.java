package game.monster;

import game.util.Vec2;

public interface MonsterState {
    /**
     * @return The state's name.
     */
    String getName();

    /**
     * Handles the update.
     */
    void update(double delta, Vec2 playerPosition);
}
