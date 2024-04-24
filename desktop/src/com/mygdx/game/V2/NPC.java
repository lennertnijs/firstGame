package com.mygdx.game.V2;

import com.mygdx.game.V2.Dialogue.IDialogueData;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;

public final class NPC extends Character{

    //todo Probably want to remove the clock from TextureSelector and pass in some arbitrary data from the actual game clock.
    private Route currentRoute;
    private final NavigationGraph graph; //todo Add the current route to the NavigationData class
    private final IWeekSchedule weekSchedule;
    private final IDialogueData dialogueData;
    private final NPCStats stats;

    private NPC(String name, ITextureSelector selector, Route route, IWeekSchedule weekSchedule,
                NavigationGraph graph, IDialogueData dialogueData, NPCStats stats){
        super(name, selector);
        this.currentRoute = route;
        this.weekSchedule = weekSchedule;
        this.graph = graph;
        this.dialogueData = dialogueData;
        this.stats = stats;
    }
}
