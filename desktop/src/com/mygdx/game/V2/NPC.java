package com.mygdx.game.V2;

import com.mygdx.game.V2.Dialogue.IDialogueData;
import com.mygdx.game.V2.General.GameObject;
import com.mygdx.game.V2.General.Sprite;
import com.mygdx.game.V2.Navigation.INavigationData;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.Util.Day;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.Activity;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;

public final class NPC extends GameObject {

    private final String name;
    private final ITextureSelector selector;
    private final INavigationData navigationData;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final NPCStats stats;

    //todo add a damn builder
    private NPC(Sprite sprite, String name, ITextureSelector selector, IWeekSchedule weekSchedule,
                INavigationData navigationData, IDialogueData dialogueData, NPCStats stats){
        super(sprite);
        this.name = name;
        this.selector = selector;
        this.weekSchedule = weekSchedule;
        this.navigationData = navigationData;
        this.dialogueData = dialogueData;
        this.stats = stats;
    }

    public String getName(){
        return name;
    }

    public void update(Day day, Time time){
        if(!weekSchedule.hasActivity(day, time))
            return;
        Activity activity = weekSchedule.getActivity(day, time);
        navigationData.calculateAndStoreRoute(getCurrentLocation(), activity.location());
    }

    public void move(int deltaInMillis){
        if(!navigationData.isMoving())
            return;
        int movement = deltaInMillis * stats.getSpeed();
        Location next = navigationData.calculateNextLocation(getCurrentLocation(), movement);
        setLocation(next);
    }

    public void updateTexture(){
        setTexture(selector.getTexture());
    }

    public void handleInputLine(String line){
        dialogueData.processInput(line);
    }
}
