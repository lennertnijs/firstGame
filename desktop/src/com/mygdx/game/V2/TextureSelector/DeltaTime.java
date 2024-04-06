package com.mygdx.game.V2.TextureSelector;

public class DeltaTime {

    private float delta;

    private DeltaTime(){
        delta = 0;
    }

    public static DeltaTime create(){
        return new DeltaTime();
    }

    public float getDelta(){
        return delta;
    }
}
