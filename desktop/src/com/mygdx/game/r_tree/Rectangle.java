package com.mygdx.game.r_tree;

import java.util.Objects;

public record Rectangle(int x, int y, int width, int height) {

    public static Rectangle createMinimumBoundingRectangle(Rectangle[] rectangles){
        Objects.requireNonNull(rectangles);
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
}
