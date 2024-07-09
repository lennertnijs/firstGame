package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.*;

public abstract class Entity extends AnimatedGameObject{

    private Direction direction;

    public Entity(Point position, Dimensions dimensions, String map,
                  Map<AnimationKey, Animation> animationMap, double delta,
                  Direction direction){
        super(position, dimensions, map, animationMap, delta);
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    /**
     * @return The entity key.
     */
    public abstract EntityKey generateEntityKey();

    @Override
    public TextureRegion getTexture(){
        return getFrame(generateEntityKey()).textureRegion();
    }

    @Override
    public Point getPosition(){
        return super.getPosition().add(getFrame(generateEntityKey()).textureTranslation());
    }

    @Override
    public Dimensions getDimensions(){
        return getFrame(generateEntityKey()).dimensions();
    }
}
