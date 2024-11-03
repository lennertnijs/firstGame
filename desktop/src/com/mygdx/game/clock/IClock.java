package com.mygdx.game.clock;

import com.mygdx.game.npc.week_schedule.Day;
import com.mygdx.game.npc.week_schedule.Time;

public interface IClock {

    Day getDay();
    Time getTime();
    boolean isActive();
    void start();
    void pause();
    double update();
    void reset();
}
