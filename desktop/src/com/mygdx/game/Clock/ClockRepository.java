package com.mygdx.game.Clock;

import com.mygdx.game.DAO.ClockDAO;

public class ClockRepository {

    private final Clock clock;

    public ClockRepository(){
        this.clock = new ClockDAO().readClock();
    }

    public Clock getClock(){
        return clock;
    }
}
