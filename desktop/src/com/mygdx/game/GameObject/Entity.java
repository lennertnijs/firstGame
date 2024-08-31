package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.AnimationHolder;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.*;

public abstract class Entity extends AnimatedGameObject{

    private Direction direction;

    public Entity(Point position, Dimensions dimensions, String map,
                  AnimationHolder animationHolder, double delta,
                  Direction direction){
        super(position, dimensions, map, animationHolder, delta);
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    @Override
    public TextureRegion getTexture(){
        return getFrame("temp", direction).textureRegion();
    }

    @Override
    public Point getPosition(){
        return super.getPosition().add(getFrame("temp", direction).textureTranslation());
    }

    @Override
    public Dimensions getDimensions(){
        return getFrame("temp", direction).dimensions();
    }
}
