package com.mygdx.game.General;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.EntityKey;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.WeekSchedule.ActivityType;

import java.util.Deque;

public abstract class Entity extends GameObject{

    private final AnimationRepository animationRepository;
    private Direction direction;
    private Deque<ActivityType> activityStack;
    private double animationDelta;

    public Entity(
            Point position,
            Dimensions dimensions,
            String map,
            AnimationRepository animationRepository,
            Direction direction,
            Deque<ActivityType> activityStack,
            double d){
        super(position, dimensions, map);
        this.animationRepository = animationRepository;
        this.direction = direction;
        this.activityStack = activityStack;
        this.animationDelta = d;
    }

    public Direction getDirection(){
        return direction;
    }

    public ActivityType getActivityType(){
        return activityStack.getFirst();
    }

    public double getAnimationDelta(){
        return animationDelta;
    }

    public void increaseAnimationDelta(double incr){
        animationDelta += incr;
    }

    @Override
    public Point getPosition(){
        Point pos  = super.getPosition();
        EntityKey entityKey = new EntityKey(getActivityType(), getDirection());
        Vector translation = animationRepository.get(entityKey).getFrame(getAnimationDelta()).translation();
        return new Point(pos.x() + translation.x(), pos.y() + translation.y());
    }
}
