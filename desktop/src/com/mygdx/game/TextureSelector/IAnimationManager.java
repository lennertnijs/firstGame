package com.mygdx.game.TextureSelector;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.WeekSchedule.Action;

public interface IAnimationManager {

    /**
     * @return The appropriate {@link Frame}.
     */
    Frame getFrame();
    void setDirection(Direction direction);
    void pushAction(Action action);
    Action getActiveAction();
    void popActiveAction();
    void increaseDelta(double delta);
}
