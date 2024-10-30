package com.mygdx.game.Transform;

import java.util.Objects;

public final class Transform {

    private Vec2 position;
    private Vec2 offset;
    int width, height;
    float scaleX, scaleY;
    float rotation;

    private Transform(Builder builder){
        this.position = builder.position;
        this.offset = builder.offset;
        this.width = builder.width;
        this.height = builder.height;
        this.scaleX = builder.scaleX;
        this.scaleY = builder.scaleY;
        this.rotation = builder.rotation;
    }

    public Vec2 getPosition(){
        return position.add(offset);
    }

    public void setPosition(Vec2 updatedPosition){
        this.position = Objects.requireNonNull(updatedPosition);
    }

    public void setOffset(Vec2 updatedOffset){
        this.offset = Objects.requireNonNull(updatedOffset);
    }

    public int getWidth(){
        return (int) (width * scaleX);
    }

    public void setWidth(int updatedWidth){
        if(updatedWidth < 0){
            throw new IllegalArgumentException("Width cannot be negative.");
        }
        this.width = updatedWidth;
    }

    public int getHeight(){
        return (int) (height * scaleY);
    }

    public void setHeight(int updatedHeight){
        if(updatedHeight < 0){
            throw new IllegalArgumentException("Height cannot be negative.");
        }
        this.height = updatedHeight;
    }

    public void setScaleX(float updatedScaleX){
        if(updatedScaleX < 0){
            throw new IllegalArgumentException("ScaleX cannot be negative.");
        }
        this.scaleX = updatedScaleX;
    }

    public void setScaleY(float updatedScaleY){
        if(updatedScaleY < 0){
            throw new IllegalArgumentException("ScaleX cannot be negative.");
        }
        this.scaleY = updatedScaleY;
    }

    public float getRotation(){
        return rotation;
    }

    public void setRotation(float updatedRotation){
        this.rotation = updatedRotation;
    }

    public static Builder builder(){
        return new Builder();
    }

    private static class Builder {

        private Vec2 position = null;
        private Vec2 offset = new Vec2(0, 0);
        int width = -1, height = -1;
        float scaleX = 1, scaleY = 1;
        float rotation = 0;

        public Builder(){

        }

        public Builder position(Vec2 position){
            this.position = position;
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

        public Builder rotation(float rotation){
            this.rotation = rotation;
            return this;
        }

        public Transform build(){
            Objects.requireNonNull(position, "Position cannot be null.");
            Objects.requireNonNull(offset, "Offset cannot be null.");
            if(width < 0 || height < 0){
                throw new IllegalArgumentException("Width and height cannot be negative.");
            }
            if(scaleX < 0 || scaleY < 0){
                throw new IllegalArgumentException("ScaleX and ScaleY cannot be negative.");
            }
            return new Transform(this);
        }
    }
}
