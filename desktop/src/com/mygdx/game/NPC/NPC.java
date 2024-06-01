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


public final class NPC extends Character {

    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final Stats stats;


    //todo add a damn builder
    public NPC(Point position,
               Dimensions dimensions,
               String map,
               AnimationRepository animationRepository,
               Direction direction,
               Deque<ActivityType> activityTypes,
               double delta,
               String name,
               IInventoryManager manager,
               IWeekSchedule weekSchedule,
               INavigationData navigationData,
               IDialogueData dialogueData,
               Stats stats){
        super(position, dimensions, map, animationRepository, direction, activityTypes, delta, name, manager);
        this.weekSchedule = weekSchedule;
        this.navigationData = navigationData;
        this.dialogueData = dialogueData;
        this.stats = stats;
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
}
