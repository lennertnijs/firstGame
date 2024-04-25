package com.mygdx.game.V2;

import com.mygdx.game.V2.Dialogue.IDialogueData;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.Util.Day;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.Activity;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;

public final class NPC{

    private final String name;
    private final ITextureSelector selector;
    private final NavigationGraph graph;
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final NPCStats stats;

    private NPC(String name, ITextureSelector selector, IWeekSchedule weekSchedule,
                NavigationGraph graph, IDialogueData dialogueData, NPCStats stats){
        this.name = name;
        this.selector = selector;
        this.weekSchedule = weekSchedule;
        this.graph = graph;
        this.dialogueData = dialogueData;
        this.stats = stats;
    }

    public void update(Day day, Time time){
        if(weekSchedule.hasActivity(day, time)){
            Activity activity = weekSchedule.getActivity(day, time);
        }
    }
}
