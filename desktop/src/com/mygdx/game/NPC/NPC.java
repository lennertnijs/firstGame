package com.mygdx.game.NPC;

import com.mygdx.game.Dialogue.IDialogueData;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Navigation.INavigationData;
import com.mygdx.game.TextureSelector.AnimationRepository;
import com.mygdx.game.TextureSelector.Frame;
import com.mygdx.game.TextureSelector.Key;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.WeekSchedule.Activity;
import com.mygdx.game.WeekSchedule.Action;
import com.mygdx.game.WeekSchedule.IWeekSchedule;

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
        this.name = name;
        this.animationRepository = animationRepository;
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
        while(metaData.getActiveAction() != Action.IDLING){
            metaData.removeAction();
        }
        metaData.addAction(activity.type());
        metaData.addAction(Action.WALKING);
    }

    public void updateTexture(){
        Key key = new Key(metaData.getActiveAction(), metaData.getDirection());
        Frame f = animationRepository.get(key).getFrame(metaData.getDelta());
        sprite.setTexture(f.textureRegion());
        sprite.setDimensions(f.dimensions());
        sprite.setPosition(sprite.getAnchor().add(f.translation()));
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
