package game.npc;

import game.game_object.GameObject;
import game.game_object.GameObjectType;
import game.game_object.Transform;
import game.inventory.Inventory;
import game.game_object.renderer.Renderer;

import game.npc.dialogue.DialogueData;
import game.npc.navigation.NavigationData;
import game.npc.states.IdleState;
import game.npc.states.NPCState;
import game.stats.Stats;
import game.npc.week_schedule.Time;
import game.npc.week_schedule.WeekSchedule;
import game.util.Day;

import java.util.Objects;


public final class NPC extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;
    private final Stats stats;
    private final NavigationData navigationData;
    private final WeekSchedule weekSchedule;
    private final DialogueData dialogueData;
    private NPCState state;

    public NPC(Transform transform, Renderer renderer, String map,
               String name, Inventory inventory, Stats stats,
               WeekSchedule weekSchedule, NavigationData navigationData, DialogueData dialogueData){
        super(transform, renderer, map, GameObjectType.NPC);
        this.name = Objects.requireNonNull(name);
        this.inventory = Objects.requireNonNull(inventory);
        this.activeIndex = 0;
        this.stats = Objects.requireNonNull(stats);
        this.weekSchedule = Objects.requireNonNull(weekSchedule);
        this.navigationData = Objects.requireNonNull(navigationData);
        this.dialogueData = Objects.requireNonNull(dialogueData);
        this.state = new IdleState(this);
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public int getActiveItemIndex(){
        return activeIndex;
    }

    public void setActiveItemIndex(int index){
        if(index < 0 || index >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeIndex = index;
    }

    public Stats getStats(){
        return stats;
    }

    public NavigationData getNavigationData(){
        return navigationData;
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public void changeState(NPCState state){
        this.state = state;
        super.renderer.setActivity(state.getState());
    }

    public void update(Day day, Time time, double delta) {
        renderer.update(delta);
        state.progress(delta); // todo send the day and time to the update
    }
}
