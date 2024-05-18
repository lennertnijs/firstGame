package com.mygdx.game.NPC;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Character;
import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.WeekSchedule.IWeekSchedule;


public final class NPC extends Character {

    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final Stats stats;
    private NPCData metaData;


    //todo add a damn builder
    public NPC(TextureRegion textureRegion, Point position, Dimensions dimensions, String map, String name,
               Vector translation, AnimationRepository animationRepository, IWeekSchedule weekSchedule,
               INavigationData navigationData, IDialogueData dialogueData, Stats stats, IInventoryManager manager){
        super(textureRegion, position, dimensions, map, translation, name, animationRepository, manager);
        this.weekSchedule = weekSchedule;
        this.navigationData = navigationData;
        this.dialogueData = dialogueData;
        this.stats = stats;
    }

    public void update(Day day, Time time, double delta){
        updateSchedule(day, time);
        updateTexture(metaData.getActiveAction(), metaData.getDirection(), metaData.getDelta());
        metaData.increaseDelta(delta);
    }

    public void move(int deltaInMillis){
        if(!navigationData.isMoving()){
            return;
        }
        int movement = deltaInMillis * stats.getSpeed();
        Location current = new Location(getMap(), getPosition());
        Location next = navigationData.calculateNextLocation(current, movement);
        setPosition(next.position());
        setMap(next.mapName());
        // set the direction appropriately
        if(!navigationData.isMoving()){
            metaData.removeAction();
        }
    }

    public void updateSchedule(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time) || navigationData.isMoving())
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        Location current = new Location(getMap(), getPosition());
        navigationData.calculateAndStoreRoute(current, activity.location());
        while(metaData.getActiveAction() != ActivityType.IDLING){
            metaData.removeAction();
        }
        metaData.addAction(activity.type());
        metaData.addAction(ActivityType.WALKING);
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
