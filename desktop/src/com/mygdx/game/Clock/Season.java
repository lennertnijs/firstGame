package com.mygdx.game.Clock;

// TESTED
public enum Season {

    LIGHT(0),
    DARK(1);

    private final int index;
    Season(int index){
        this.index = index;
    }

    /**
     * Will return the next {@code Season}.
     * Works circularly.
     */
    public Season next(){
        int nextIndex = (this.index + 1) % Season.values().length;
        return values()[nextIndex];
    }
}
