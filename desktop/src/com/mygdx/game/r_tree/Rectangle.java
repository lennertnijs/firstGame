package com.mygdx.game.r_tree;

import com.mygdx.game.util.Vec2;

import java.util.List;
import java.util.Objects;

public record Rectangle(int x, int y, int width, int height) {

    public static Rectangle createMinimumBounding(List<Rectangle> rectangles){
        Objects.requireNonNull(rectangles);
        if(rectangles.size() == 0){
            return new Rectangle(0, 0, 0, 0);
        }
        int min_x = Integer.MAX_VALUE;
        int min_y = Integer.MAX_VALUE;
        int max_x = Integer.MIN_VALUE;
        int max_y = Integer.MIN_VALUE;
        for(Rectangle rectangle : rectangles){
            Objects.requireNonNull(rectangle);
            min_x = Math.min(min_x, rectangle.x);
            min_y = Math.min(min_y, rectangle.y);
            max_x = Math.max(max_x, rectangle.x + rectangle.width);
            max_y = Math.max(max_y, rectangle.y + rectangle.height);
        }
        return new Rectangle(min_x, min_y, max_x - min_x, max_y - min_y);
    }

    public static Rectangle createMinimumBoundingRectangle(Rectangle r1, Rectangle r2){
        int min_x = Math.min(r1.x, r2.x);
        int min_y = Math.min(r1.y, r2.y);
        int max_x = Math.max(r1.x + r1.width, r2.x + r2.width);
        int max_y = Math.max(r1.y + r1.height, r2.y + r2.height);
        return new Rectangle(min_x, min_y, max_x - min_x, max_y - min_y);
    }

    public int perimeter(){
        return 2 * height + 2 * width;
    }

    public int area(){
        return width * height;
    }

    public Vec2 center(){
        return new Vec2(x + width/2, y + height/2);
    }

    public int overlapWith(Rectangle r){
        Objects.requireNonNull(r);
        int min_x = Math.max(x, r.x);
        int min_y = Math.max(y, r.y);
        int max_x = Math.min(x + width, r.x + r.width);
        int max_y = Math.min(y + height, r.y + r.height);
        return (max_x - min_x) * (max_y - min_y);
    }
}
