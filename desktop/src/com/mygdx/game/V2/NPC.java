package com.mygdx.game.V2;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Dialogue.IDialogueData;
import com.mygdx.game.V2.Navigation.INavigationData;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.Util.Day;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.Activity;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;

public final class NPC{

    private final String name;
    private final ITextureSelector selector;
    private final INavigationData graph;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final NPCStats stats;

    private NPC(String name, ITextureSelector selector, IWeekSchedule weekSchedule,
                INavigationData graph, IDialogueData dialogueData, NPCStats stats){
        this.name = name;
        this.selector = selector;
        this.weekSchedule = weekSchedule;
        this.graph = graph;
        this.dialogueData = dialogueData;
        this.stats = stats;
    }

    public void update(Day day, Time time, int delta){
        if(weekSchedule.hasActivity(day, time)){
            Activity activity = weekSchedule.getActivity(day, time);
            graph.calculateAndStoreRoute(null, activity.location()); // should hold THIS.location
        }
        if(graph.isMoving()){
            Location next = graph.calculateNextLocation(null, delta * stats.getSpeed());
            // set this location to the next one
        }
    }

    public void updateTexture(){
        TextureRegion t = selector.getTexture();
        // set this sprite's texture to the given.
    }
}
