package com.mygdx.game;

import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.TextureSelector.ITextureSelector;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.IWeekSchedule;

public final class NPC extends GameObject {

    private final String name;
    private final ITextureSelector selector;
    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final IInventoryManager inventoryManager;
    private final NPCStats stats;

    //todo add a damn builder
    private NPC(Sprite sprite, String name, ITextureSelector selector, IWeekSchedule weekSchedule,
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

    public void update(Day day, Time time){
        updateSchedule(day, time);
        updateTexture();
    }

    public void move(int deltaInMillis){
        if(!navigationData.isMoving())
            return;
        int movement = deltaInMillis * stats.getSpeed();
        Location next = navigationData.calculateNextLocation(getCurrentLocation(), movement);
        setLocation(next);
    }

    public void updateSchedule(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time))
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        navigationData.calculateAndStoreRoute(getCurrentLocation(), activity.location());
    }

    public void updateTexture(){
        setTexture(selector.getTexture());
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
