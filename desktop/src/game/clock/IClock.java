package game.clock;

import game.util.Day;
import game.npc.week_schedule.Time;

public interface IClock {

    Day getDay();
    Time getTime();
    boolean isActive();
    void start();
    void pause();
    double update();
    void reset();
}
