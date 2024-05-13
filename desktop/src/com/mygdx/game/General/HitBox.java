package com.mygdx.game.General;

import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public final class HitBox {

    private final Point position;
    private final Dimensions dimensions;

    public HitBox(Point position, Dimensions dimensions){
        this.position = Objects.requireNonNull(position, "Position is null.");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions is null.");
    }

    public Point getPosition(){
        return position;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    public boolean overlaps(HitBox other){
        Objects.requireNonNull(other);
        return true; // do the overlapping check
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof HitBox))
            return false;
        HitBox hitBox = (HitBox) other;
        return position.equals(hitBox.position) && dimensions.equals(hitBox.dimensions);
    }

    @Override
    public int hashCode(){
        return 31 * position.hashCode() + dimensions.hashCode();
    }

    @Override
    public String toString(){
        return String.format("HitBox[position=%s, dimensions=%s]", position, dimensions);
    }
}
