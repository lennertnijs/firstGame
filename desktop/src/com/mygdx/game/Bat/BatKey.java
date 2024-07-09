package com.mygdx.game.Bat;

import com.mygdx.game.Animation.AnimationKey;

public final class BatKey implements AnimationKey {

    private final String state;
    public BatKey(String state){
        this.state = state;
    }

    public String state(){
        return state;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BatKey))
            return false;
        BatKey key = (BatKey) other;
        return state.equals(key.state);
    }

    @Override
    public int hashCode(){
        return state.hashCode();
    }
}
