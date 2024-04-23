package com.mygdx.game.V2;

import com.mygdx.game.V2.Dialogue.IDialogueRepository;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.WeekSchedule.IWeekSchedule;

public final class NPC extends Character{

    private Route currentRoute;
    private final NavigationGraph graph;
    private final IWeekSchedule weekSchedule;
    private final IDialogueRepository dialogueRepository;
    private final NPCStats stats;

    private NPC(String name, ITextureSelector selector, Route route, IWeekSchedule weekSchedule,
                NavigationGraph graph, IDialogueRepository repository, NPCStats stats){
        super(name, selector);
        this.currentRoute = route;
        this.weekSchedule = weekSchedule;
        this.graph = graph;
        this.dialogueRepository = repository;
        this.stats = stats;
    }
}
