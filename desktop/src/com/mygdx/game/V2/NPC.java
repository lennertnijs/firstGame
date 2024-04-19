package com.mygdx.game.V2;

import com.mygdx.game.NPC.WeekSchedule;
import com.mygdx.game.V2.Dialogue.IDialogueRepository;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import jogamp.graph.font.typecast.ot.table.ID;

import java.util.List;

public final class NPC extends Character{

    private Route currentRoute;
    private final List<String> activeLines;
    private final NavigationGraph graph;
    private final WeekSchedule weekSchedule;
    private final IDialogueRepository dialogueRepository;
    private final NPCStats stats;

    private NPC(String name, ITextureSelector selector, Route route, List<String> activeLines, WeekSchedule weekSchedule, NavigationGraph graph, IDialogueRepository repository, NPCStats stats){
        super(name, selector);
        this.currentRoute = route;
        this.activeLines = activeLines;
        this.weekSchedule = weekSchedule;
        this.graph = graph;
        this.dialogueRepository = repository;
        this.stats = stats;
    }
}
