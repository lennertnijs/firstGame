package com.mygdx.game.V2.TextureSelector;

public final class SystemTimeProvider implements TimeProvider{

    public SystemTimeProvider(){
    }

    @Override
    public long getTimeInMillis(){
        return System.currentTimeMillis();
    }

    @Override
    public void reset(){
    }
}
