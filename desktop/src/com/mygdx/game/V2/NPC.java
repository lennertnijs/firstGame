package com.mygdx.game.V2;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private final INavigationData graph;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final NPCStats stats;

    private NPC(String map, Sprite sprite, String name, ITextureSelector selector, IWeekSchedule weekSchedule,
                INavigationData graph, IDialogueData dialogueData, NPCStats stats){
        super(sprite);
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
            graph.calculateAndStoreRoute(getCurrentLocation(), activity.location());
        }
        if(graph.isMoving()){
            Location next = graph.calculateNextLocation(getCurrentLocation(), delta * stats.getSpeed());
            setLocation(next);
        }
    }

    public void updateTexture(){
        TextureRegion t = selector.getTexture();
        setTexture(t);
    }
}
