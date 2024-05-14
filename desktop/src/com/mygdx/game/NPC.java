package com.mygdx.game;

import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.TextureSelector.Frame;
import com.mygdx.game.TextureSelector.ITextureSelector;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.WeekSchedule.IWeekSchedule;

import java.util.Deque;
import java.util.LinkedList;

public final class NPC extends GameObject {

    private final String name;
    private final ITextureSelector selector;
    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final IInventoryManager inventoryManager;
    private final NPCStats stats;

    //todo add a damn builder
    public NPC(Sprite sprite, String name, ITextureSelector selector, IWeekSchedule weekSchedule,
                INavigationData navigationData, IDialogueData dialogueData, NPCStats stats, IInventoryManager manager){
        super(sprite);
        this.name = name;
        this.selector = selector;
        this.weekSchedule = weekSchedule;
        this.navigationData = navigationData;
        this.dialogueData = dialogueData;
        this.stats = stats;
        this.inventoryManager = manager;
    }

    public String getName(){
        return name;
    }

    public void update(Day day, Time time, double delta){
        updateSchedule(day, time);
        updateTexture();
        selector.increaseClock((long) delta);
    }

    public void move(int deltaInMillis){
        if(!navigationData.isMoving())
            return;
        int movement = deltaInMillis * stats.getSpeed();
        Location next = navigationData.calculateNextLocation(sprite.getLocation(), movement);
        sprite.setLocation(next);
        if(!navigationData.isMoving()){ // means the route was finished
            selector.popActivityType();
        }
    }

    public void updateSchedule(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time) || navigationData.isMoving())
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        navigationData.calculateAndStoreRoute(sprite.getLocation(), activity.location());
        selector.setActivityType(activity.type()); // pushes to the stack
        selector.setActivityType(ActivityType.WALKING);
    }

    public void updateTexture(){
        Frame f = selector.getFrame();
        sprite.setTexture(f.textureRegion());
        sprite.setDimensions(f.dimensions());
        sprite.setPosition(sprite.getAnchor().add(f.translation()));
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
