package game.npc.states;

import game.npc.week_schedule.Time;
import game.util.Day;

public interface NPCState {

    String getState();
    void progress(double delta, Day day, Time time);
}
