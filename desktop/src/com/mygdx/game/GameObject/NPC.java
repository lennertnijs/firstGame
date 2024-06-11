package com.mygdx.game.GameObject;

import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Stats;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Navigation.NavigationData;
import com.mygdx.game.Util.*;
import com.mygdx.game.Util.Activity;
import com.mygdx.game.Keys.NPCActivityType;
import com.mygdx.game.UtilMethods.DirectionCalculator;
import com.mygdx.game.WeekSchedule.WeekSchedule;

import java.util.List;
import java.util.Objects;


public final class NPC extends Character {

    private final NavigationData navigationData;
    private final WeekSchedule weekSchedule;
    private final DialogueData dialogueData;
    private final Stats stats;

    private NPC(Builder b){
        super(b.position, b.dimensions, b.map,
                b.animationRepository, b.d, b.direction, b.NPCActivityTypes,
                b.name, b.inventory);
        this.weekSchedule = b.weekSchedule;
        this.navigationData = b.navigationData;
        this.dialogueData = b.dialogueData;
        this.stats = b.stats;
    }

    public void update(Day day, Time time, double delta){
        super.increaseAnimationDelta(delta);
        if(getCurrentActivityType() == NPCActivityType.WALKING){
            move(delta);
        }
        updateSchedule(day, time);
    }

    public void move(double deltaInMillis){
        int movement = (int)deltaInMillis * stats.getSpeed();
        Location current = new Location(getMap(), getPosition());
        Location next = navigationData.calculateNextLocation(current, movement);
        setPosition(next.position());
        setMap(next.mapName());
        super.setDirection(DirectionCalculator.calculate(current.position(), next.position()));
        // check if anything left. if not, pop the WALKING off
    }

    public void updateSchedule(Day day, Time time){
        Activity activity = weekSchedule.getActivity(day, time);
        if(activity == null || super.getCurrentActivityType() == NPCActivityType.WALKING)
            return;
        Location current = new Location(getMap(), position); // take the end of the route, if any still active
        Location next = new Location("temp", activity.position());
        System.out.println(current);
        System.out.println(next);
        navigationData.calculateAndStoreRoute(current, next);
        while(super.getCurrentActivityType() != NPCActivityType.IDLING){
            super.removeCurrentActivityType();
        }
        super.storeActivityType(activity.type());
        super.storeActivityType(NPCActivityType.WALKING);
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
        private List<ActivityType> NPCActivityTypes;
        private double d = -1;
        private String name;
        private Inventory inventory;
        private NavigationData navigationData;
        private WeekSchedule weekSchedule;
        private DialogueData dialogueData;
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

        public Builder activityStack(List<ActivityType> NPCActivityTypes){
            this.NPCActivityTypes = NPCActivityTypes;
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

        public Builder inventory(Inventory inventory){
            this.inventory = inventory;
            return this;
        }

        public Builder navigationData(NavigationData navigationData){
            this.navigationData = navigationData;
            return this;
        }

        public Builder weekSchedule(WeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder dialogueData(DialogueData dialogueData){
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
            Objects.requireNonNull(NPCActivityTypes);
            if(d < 0){
                throw new IllegalArgumentException("Delta is negative.");
            }
            Objects.requireNonNull(name);
            Objects.requireNonNull(inventory);
            Objects.requireNonNull(navigationData);
            Objects.requireNonNull(weekSchedule);
            Objects.requireNonNull(dialogueData);
            Objects.requireNonNull(stats);
            return new NPC(this);
        }
    }
}
