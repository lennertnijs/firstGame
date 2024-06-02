package com.mygdx.game.NPC;

import com.mygdx.game.Character;
import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.WeekSchedule.IWeekSchedule;

import java.util.Deque;
import java.util.Objects;


public final class NPC extends Character {

    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final Stats stats;

    private NPC(Builder b){
        super(b.position, b.dimensions, b.map,
                b.animationRepository, b.direction, b.activityTypes, b.d,
                b.name, b.inventoryManager);
        this.weekSchedule = b.weekSchedule;
        this.navigationData = b.navigationData;
        this.dialogueData = b.dialogueData;
        this.stats = b.stats;
    }

    public void update(Day day, Time time, double delta){
        updateSchedule(day, time);
        super.increaseAnimationDelta(delta);
    }

    public void move(int deltaInMillis){
        if(super.getActivityType() != ActivityType.WALKING){
            return;
        }
        int movement = deltaInMillis * stats.getSpeed();
        Location current = new Location(getMap(), getPosition());
        Location next = navigationData.calculateNextLocation(current, movement);
        setPosition(next.position());
        setMap(next.mapName());
        // set the direction appropriately
        if(super.getActivityType() == ActivityType.WALKING){
            // pop it off
        }
    }

    public void updateSchedule(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time) || super.getActivityType() == ActivityType.WALKING)
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        Location current = new Location(getMap(), getPosition());
        navigationData.calculateAndStoreRoute(current, activity.location());
        while(super.getActivityType() != ActivityType.IDLING){
            // remove the activity type (wipe the stack)
        }
        // add the activity at the end, then WALKING
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }


    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private Point position;
        private Dimensions dimensions;
        private String map;
        private AnimationRepository animationRepository;
        private Direction direction;
        private Deque<ActivityType> activityTypes;
        private double d = -1;
        private String name;
        private IInventoryManager inventoryManager;
        private INavigationData navigationData;
        private IWeekSchedule weekSchedule;
        private IDialogueData dialogueData;
        private Stats stats;

        private Builder(){
        }

        public Builder position(Point position){
            this.position = position;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Builder map(String map){
            this.map = map;
            return this;
        }

        public Builder animationRepo(AnimationRepository animationRepository){
            this.animationRepository = animationRepository;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
            return this;
        }

        public Builder activityStack(Deque<ActivityType> activityTypes){
            this.activityTypes = activityTypes;
            return this;
        }

        public Builder delta(double d){
            this.d = d;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder inventoryManager(IInventoryManager inventoryManager){
            this.inventoryManager = inventoryManager;
            return this;
        }

        public Builder navigationData(INavigationData navigationData){
            this.navigationData = navigationData;
            return this;
        }

        public Builder weekSchedule(IWeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder dialogueData(IDialogueData dialogueData){
            this.dialogueData = dialogueData;
            return this;
        }

        public Builder stats(Stats stats){
            this.stats = stats;
            return this;
        }

        public NPC build(){
            Objects.requireNonNull(position, "Position is null.");
            Objects.requireNonNull(dimensions, "Dimensions is null.");
            Objects.requireNonNull(map, "Map is null.");
            Objects.requireNonNull(animationRepository, "AnimationRepository is null.");
            Objects.requireNonNull(direction, "Direction is null.");
            Objects.requireNonNull(activityTypes);
            if(d < 0){
                throw new IllegalArgumentException("Delta is negative.");
            }
            Objects.requireNonNull(name);
            Objects.requireNonNull(inventoryManager);
            Objects.requireNonNull(navigationData);
            Objects.requireNonNull(weekSchedule);
            Objects.requireNonNull(dialogueData);
            Objects.requireNonNull(stats);
            return new NPC(this);
        }
    }
}
