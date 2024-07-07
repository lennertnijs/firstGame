package com.mygdx.game.HitBox;

import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public final class Rectangle implements HitBox {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Rectangle(Point position, Dimensions dimensions){
        Objects.requireNonNull(position, "Position is null.");
        Objects.requireNonNull(dimensions, "Dimensions is null.");
        this.x = position.x();
        this.y = position.y();
        this.width = dimensions.width();
        this.height = dimensions.height();
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    public boolean overlaps(Rectangle r){
        Objects.requireNonNull(r, "Rectangle is null.");
        return x < r.x + r.width && r.x < x + width && y < r.y + r.height && r.y < y + height;
    }

    public boolean contains(Point point){
        Objects.requireNonNull(point, "Point is null.");
        return x <= point.x() && point.x() < x + width && y <= point.y() && point.y() < y + height;
    }

    public boolean overlaps(HitBox hitBox){
        Point bottomRight = new Point(x + width, y);
        Point bottomLeft = new Point(x, y);
        Point topLeft = new Point(x, y + height);
        Point topRight = new Point(x + width, y + height);
        return hitBox.contains(bottomLeft) || hitBox.contains(bottomRight) || hitBox.contains(topLeft) || hitBox.contains(topRight);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Rectangle))
            return false;
        Rectangle r = (Rectangle) other;
        return x == r.x && y == r.y && width == r.width && height == r.height;
    }

    @Override
    public int hashCode(){
        int result = x;
        result = result * 31 + y;
        result = result * 31 + width;
        result = result * 31 + height;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Rectangle[x=%d, y=%d, width=%d, height=%d]", x, y, width, height);
    }
}
