package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UpdatedUtil.Vec2;

import java.util.Objects;

public final class Frame {

    private final TextureRegion texture;
    private final Vec2 offset;
    private final int width, height;
    private final float scaleX, scaleY;

    private Frame(Builder builder){
        this.texture = builder.texture;
        this.offset = builder.offset;
        this.width = builder.width;
        this.height = builder.height;
        this.scaleX = builder.scaleX;
        this.scaleY = builder.scaleY;
    }

    public TextureRegion texture(){
        return texture;
    }

    public Vec2 offset(){
        return offset;
    }

    public int width(){
        return width;
    }

    public int scaledWidth(){
        return (int) (width * scaleX);
    }

    public int height(){
        return height;
    }

    public int scaledHeight(){
        return (int) (height * scaleY);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private TextureRegion texture = null;
        private Vec2 offset = new Vec2(0, 0);
        private int width = -1, height = -1;
        private float scaleX = 1, scaleY = 1;

        public Builder(){

        }

        public Builder texture(TextureRegion texture){
            this.texture = texture;
            return this;
        }

        public Builder offset(Vec2 offset){
            this.offset = offset;
            return this;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public Builder height(int height){
            this.height = height;
            return this;
        }

        public Builder scaleX(float scaleX){
            this.scaleX = scaleX;
            return this;
        }

        public Builder scaleY(float scaleY){
            this.scaleY = scaleY;
            return this;
        }

        public Frame build(){
            Objects.requireNonNull(texture);
            if(width < 0 || height < 0){
                throw new IllegalArgumentException("The width or height cannot be negative.");
            }
            if(scaleX < 0 || scaleY < 0){
                throw new IllegalArgumentException("The scaleX or scaleY cannot be negative.");
            }
            return new Frame(this);
        }
    }
}
