package com.mygdx.game.TextureSelector;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.Deque;
import java.util.Objects;

public final class TextureSelector implements ITextureSelector{

    private final Deque<ActivityType> activityTypeStack;
    private Direction direction;
    private final AnimationRepository repo;
    private final AnimationClock clock;

    public TextureSelector(Deque<ActivityType> activityTypeStack, Direction direction, AnimationRepository repo, AnimationClock clock){
        this.activityTypeStack = Objects.requireNonNull(activityTypeStack, "Activity type is null.");
        if(activityTypeStack.contains(null)){
            throw new NullPointerException("Stack contains null.");
        }
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
        this.repo = Objects.requireNonNull(repo, "Repository is null.");
        this.clock = Objects.requireNonNull(clock, "Clock is null.").copy();
    }

    @Override
    public void setActivityType(ActivityType activityType){
        activityTypeStack.add(Objects.requireNonNull(activityType, "Activity type is null."));
        // reset the clock
    }

    public void popActivityType(){
        if(activityTypeStack.isEmpty())
            return;
        activityTypeStack.removeLast();
        // reset the clock
    }

    @Override
    public void setDirection(Direction direction){
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
        // reset the clock
    }

    @Override
    public Frame getFrame(){
        return repo.get(new Key(activityTypeStack.getLast(), direction)).getFrame(clock.getDeltaInMillis());
    }

    @Override
    public void increaseClock(long increaseInMillis){
        clock.increase(increaseInMillis);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof TextureSelector))
            return false;
        TextureSelector selector = (TextureSelector) other;
        return activityTypeStack.getLast().equals(selector.activityTypeStack.getLast()) && repo.equals(selector.repo) && clock.equals(selector.clock);
    }

    @Override
    public int hashCode(){
        int result = activityTypeStack.getLast().hashCode();
        result = result * 31 + repo.hashCode();
        result = result * 31 + clock.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("TextureSelector[key=%s, repository=%s, clock=%s]", activityTypeStack.getLast(), repo, clock);
    }

    public TextureSelector copy(){
        return new TextureSelector(activityTypeStack, direction, repo, clock.copy());
    }
}
