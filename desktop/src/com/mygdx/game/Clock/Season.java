package com.mygdx.game.Clock;

import java.util.Objects;

public class Season {

    private final SeasonName name;
    private final int length;

    private Season(SeasonName name, int length){
        this.name = name;
        this.length = length;
    }

    public static Season create(SeasonName name, int length){
        Objects.requireNonNull(name, "Cannot create a Season with a null SeasonName.");
        if(length < 0)
            throw new IllegalArgumentException("Cannot create a Season with a negative length.");
        return new Season(name, length);
    }

    public SeasonName getName(){
        return name;
    }

    public int getLength(){
        return length;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Season)){
            return false;
        }
        Season season = (Season) o;
        return this.name.equals(season.name) && this.length == season.length;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Season[Name = %s, length = %d]", name, length);
    }
}
