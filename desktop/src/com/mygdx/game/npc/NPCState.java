package com.mygdx.game.npc;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

public interface NPCState {

    String getState();
    void progress(Day day, Time time, double delta);
}
