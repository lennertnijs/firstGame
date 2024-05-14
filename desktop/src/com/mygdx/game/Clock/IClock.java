package com.mygdx.game.Clock;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

public interface IClock {

    Day getDay();
    Time getTime();
    boolean isActive();
    void start();
    void pause();
    double update();
    void reset();
}
