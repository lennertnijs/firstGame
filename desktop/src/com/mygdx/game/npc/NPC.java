package com.mygdx.game.npc;

import com.mygdx.game.npc.dialogue.DialogueData;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.inventory.Tool;
import com.mygdx.game.npc.navigation.NavigationData;
import com.mygdx.game.npc.navigation.Route;
import com.mygdx.game.npc.states.IdleState;
import com.mygdx.game.npc.states.NPCState;
import com.mygdx.game.effect.Stats;
import com.mygdx.game.npc.week_schedule.Activity;
import com.mygdx.game.npc.week_schedule.Day;
import com.mygdx.game.npc.navigation.Location;
import com.mygdx.game.npc.week_schedule.Time;
import com.mygdx.game.npc.week_schedule.WeekSchedule;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.game_object.renderer.Renderer;
import com.mygdx.game.game_object.Transform;

import java.util.Objects;


public final class NPC extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;
    private final NavigationData navigationData;
    private final WeekSchedule weekSchedule;
    private final Stats stats;
    private NPCState state = new IdleState(this);
    private final Route route; // remove this
    private Activity nextActivity; // remove this
    private final DialogueData dialogueData;

    private NPC(Builder b){
        super(b.transform, b.renderer, b.map);
        this.name = b.name;
        this.inventory = b.inventory;
        this.activeIndex = 0;
        this.weekSchedule = b.weekSchedule;
        this.navigationData = b.navigationData;
        this.dialogueData = b.dialogueData;
        this.stats = b.stats;
        route = new Route();
    }

    public void updateRoute(Activity activity){
        Location current = new Location(map, getPosition());
        Location next = new Location(activity.map(), activity.position());
        route.add(navigationData.generateRoute(current, next));
    }

    public Stats getStats(){
        return stats;
    }

    public void update(Day day, Time time, double delta){
        renderer.update(delta);
        Activity activity = weekSchedule.getActivity(day, time);
        if(activity != null){
            updateRoute(activity);
            nextActivity = activity;
        }
        state.progress(delta);
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public Activity getNextActivity(){
        return nextActivity;
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


    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Item getActiveItem(){
        return inventory.getItem(activeIndex);
    }

    public boolean hasToolInActive(){
        return inventory.getItem(activeIndex) instanceof Tool;
    }

    public int getActiveIndex(){
        return activeIndex;
    }

    public void incrementActiveIndex(){
        this.activeIndex = (activeIndex + 1) % inventory.size();
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private Transform transform;
        private Renderer renderer;
        private String map;
        private String name;
        private Inventory inventory;
        private NavigationData navigationData;
        private WeekSchedule weekSchedule;
        private DialogueData dialogueData;
        private Stats stats;

        private Builder(){
        }

        public Builder transform(Transform transform){
            this.transform = transform;
            return this;
        }

        public Builder renderer(Renderer renderer){
            this.renderer = renderer;
            return this;
        }


        public Builder map(String map){
            this.map = map;
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
            Objects.requireNonNull(map, "Map is null.");
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
