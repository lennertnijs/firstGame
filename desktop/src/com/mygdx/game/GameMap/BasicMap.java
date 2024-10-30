package com.mygdx.game.GameMap;

public class BasicMap implements GameMap{


    private String name;

    public BasicMap(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
