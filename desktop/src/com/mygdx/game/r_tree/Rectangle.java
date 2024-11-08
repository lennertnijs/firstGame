package com.mygdx.game.r_tree;

public record Rectangle(int x, int y, int width, int height) {

    public int perimeter(){
        return 2 * height + 2 * width;
    }

    public int area(){
        return width * height;
    }
}
