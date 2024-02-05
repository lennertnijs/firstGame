package com.mygdx.game.Clock;

import com.mygdx.game.DAO.ClockDAO;

public class ClockRepository {

    private final Clock clock;

    /**
     * The repository in which the game's clock will be stored.
     * The method is protected, because only the clock service class should use this constructor.
     */
    protected ClockRepository(){
        this.clock = new ClockDAO().readClock();
    }

    /**
     * @return The clock in the repository.
     */
    protected Clock getClock(){
        return clock;
    }
}
