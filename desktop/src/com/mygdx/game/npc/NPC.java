package com.mygdx.game.npc;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.GameObject.Character;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Navigation.NavigationData;
import com.mygdx.game.Navigation.Route;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.WeekSchedule;

import java.util.Map;
import java.util.Objects;


public final class NPC extends Character {

    private final NavigationData navigationData;
    private final WeekSchedule weekSchedule;
    private final DialogueData dialogueData;
    private final Stats stats;
    private NPCState state = new IdleState(this);
    private final Route route;
    private Activity nextActivity;

    private NPC(Builder b){
        super(b.position, b.dimensions, b.map,
                b.animationMap, b.d, b.direction,
                b.name, b.inventory);
        this.weekSchedule = b.weekSchedule;
        this.navigationData = b.navigationData;
        this.dialogueData = b.dialogueData;
        this.stats = b.stats;
        route = new Route();
    }

    @Override
    public AnimationKey generateEntityKey() {
        return new EntityKey(state.getState(), super.getDirection());
    }

    public void updateRoute(Activity activity){
        Location next = new Location(activity.map(), activity.position());
        route.add(navigationData.generateRoute(getLocation(), next));
    }

    public Stats getStats(){
        return stats;
    }

    public void update(Day day, Time time, double delta){
        super.increaseAnimationDelta(delta);
        state.progress(day, time, delta);
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public Activity getNextActivity(){
        return nextActivity;
    }

    public void setNextActivity(Activity activity){
        this.nextActivity = activity;
    }

    public void changeState(NPCState state){
        this.state = state;
    }

    public String handleInputLine(String line){
        return "response";
    }

    public Route getRoute(){
        return route;
    }


    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private Point position;
        private Dimensions dimensions;
        private String map;
        private Map<AnimationKey, Animation> animationMap;
        private Direction direction;
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

        public Builder animationMap(Map<AnimationKey, Animation> animationMap){
            this.animationMap = animationMap;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
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
            Objects.requireNonNull(animationMap, "Animation is null.");
            Objects.requireNonNull(direction, "Direction is null.");
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
