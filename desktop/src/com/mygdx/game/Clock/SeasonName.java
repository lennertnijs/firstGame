package com.mygdx.game.Clock;

public enum SeasonName {

    LIGHT(0),
    DARK(1);

    private final int index;
    SeasonName(int index){
        this.index = index;
    }

    /**
     * Will return the next {@code Season}.
     * Works circularly.
     */
    public SeasonName next(){
        int nextIndex = (this.index + 1) % SeasonName.values().length;
        return values()[nextIndex];
    }
}
