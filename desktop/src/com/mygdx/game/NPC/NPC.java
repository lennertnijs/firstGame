package com.mygdx.game.NPC;

import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Key;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.WeekSchedule.IWeekSchedule;

import java.util.Objects;

public final class NPC extends GameObject {

    private final String name;
    private final AnimationRepository animationRepository;
    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final IInventoryManager inventoryManager;
    private final NPCStats stats;
    private NPCData metaData;


    //todo add a damn builder
    public NPC(Sprite sprite, String name, AnimationRepository animationRepository, IWeekSchedule weekSchedule,
                INavigationData navigationData, IDialogueData dialogueData, NPCStats stats, IInventoryManager manager){
        super(sprite);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.animationRepository = Objects.requireNonNull(animationRepository, "Animation repository is null.");
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
        metaData.increaseDelta(delta);
    }

    public void move(int deltaInMillis){
        if(!navigationData.isMoving()){
            return;
        }
        int movement = deltaInMillis * stats.getSpeed();
        Location next = navigationData.calculateNextLocation(sprite.getLocation(), movement);
        sprite.setLocation(next);
        // set the direction appropriately
        if(!navigationData.isMoving()){
            metaData.removeAction();
        }
    }

    public void updateSchedule(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time) || navigationData.isMoving())
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        navigationData.calculateAndStoreRoute(sprite.getLocation(), activity.location());
        while(metaData.getActiveAction() != ActivityType.IDLING){
            metaData.removeAction();
        }
        metaData.addAction(activity.type());
        metaData.addAction(ActivityType.WALKING);
    }

    public void updateTexture(){
        Key key = new Key(metaData.getActiveAction(), metaData.getDirection()); // do this in the data class?
        sprite.update(animationRepository.get(key).getFrame(metaData.getDelta()));
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
