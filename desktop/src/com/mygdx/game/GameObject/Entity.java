package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Entity extends AnimatedGameObject{

    private Direction direction;
    private final Deque<ActivityType> activityStack;

    public Entity(Point position, Dimensions dimensions, String map,
                  AnimationRepository animationRepository, double delta,
                  Direction direction, List<ActivityType> activityTypes){
        super(position, dimensions, map, animationRepository, delta);
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
        Objects.requireNonNull(activityTypes, "List is null.");
        if(activityTypes.contains(null)){
            throw new NullPointerException("List contains null.");
        }
        this.activityStack = new LinkedList<>(activityTypes);
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    // update direction using current and next position

    public ActivityType getCurrentActivityType(){
        return activityStack.getLast();
    }

    // check this
    public void removeCurrentActivityType(){
        activityStack.removeLast();
    }

    public void storeActivityType(ActivityType activityType){
        activityStack.addLast(activityType);
    }

    @Override
    public TextureRegion getTexture(){
        EntityKey key = new EntityKey(getCurrentActivityType(), direction);
        return super.getFrame(key).textureRegion();
    }

    @Override
    public Point getPosition(){
        Point pos  = super.getPosition();
        EntityKey entityKey = new EntityKey(getCurrentActivityType(), direction);
        Vector translation = getFrame(entityKey).translation();
        return pos.add(translation);
    }

    @Override
    public Dimensions getDimensions(){
        return new Dimensions(getTexture().getRegionWidth(), getTexture().getRegionHeight());
    }
}
