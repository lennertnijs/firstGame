package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Entity;
import com.mygdx.game.Entity.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NPC extends Entity{

    /**
     * Dialogue tree -> HashMap<Integer, x>
     *     needs player's text, npc response, and some quest checker?
     * NPC description list (to unlock)
     */

    private final String name;
    private final WeekSchedule weekSchedule;
    private List<Position> movementPath;
    private Activity activity;
    private final MovementGraph movementGraph;
    private final List<Integer> dialogueOptions;

    public NPC(Builder builder){
        super(builder.position, builder.spritePath);
        this.name = builder.name;
        this.weekSchedule = builder.weekSchedule;
        this.movementPath = builder.movementPath;
        this.activity = builder.activity;
        this.movementGraph = builder.movementGraph;
        this.dialogueOptions = builder.dialogueOptions;
    }

    public String getName(){
        return name;
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public List<Position> getMovementPath(){
        return this.movementPath;
    }

    public void setMovementPath(List<Position> movementPath){
        Objects.requireNonNull(movementPath, "Cannot set the movement path of an npc to null");
        for(Position point : movementPath){
            Objects.requireNonNull(point, "Cannot set the movement path of an npc to a list with a null value");
        }
        this.movementPath = movementPath;
    }

    public Activity getActivity(){
        return this.activity;
    }

    public void setActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot set the activity of an npc to null");
        this.activity = activity;
    }

    public MovementGraph getMovementGraph(){
        return this.movementGraph;
    }

    public List<Integer> getDialogueOptions(){
        return dialogueOptions;
    }

    public void interact(){
        int x = 0;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof NPC)){
            return false;
        }
        NPC npc = (NPC) o;
        return getPosition().equals(npc.getPosition()) &&
                getSpritePath().equals(npc.getSpritePath()) &&
                name.equals(npc.name) &&
                weekSchedule.equals(npc.weekSchedule) &&
                movementPath.equals(npc.movementPath) &&
                activity.equals(npc.activity) &&
                movementGraph.equals(npc.movementGraph) &&
                dialogueOptions.equals(npc.dialogueOptions);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getPosition(), getSpritePath(), name, weekSchedule,
                movementPath, activity, movementGraph, dialogueOptions);
    }



    public static Builder builder(){
        return new NPC.Builder();
    }

    public static class Builder{

        // Entity fields
        private Position position;
        private String spritePath;

        // NPC fields
        private String name;
        private WeekSchedule weekSchedule;
        private List<Position> movementPath = new ArrayList<>();
        private Activity activity;
        private MovementGraph movementGraph;
        private List<Integer> dialogueOptions = new ArrayList<>();


        private Builder(){

        }

        public Builder position(Position position){
            this.position = position;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        // NPC fields
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder weekSchedule(WeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder movementPath(List<Position> movementPath){
            this.movementPath = movementPath;
            return this;
        }

        public Builder activity(Activity activity){
            this.activity = activity;
            return this;
        }

        public Builder movementGraph(MovementGraph movementGraph){
            this.movementGraph = movementGraph;
            return this;
        }

        public Builder dialogueOptions(List<Integer> dialogueOptions){
            this.dialogueOptions = dialogueOptions;
            return this;
        }

        public NPC build(){
            Objects.requireNonNull(position, "An npc's location must not be null");
            Objects.requireNonNull(spritePath, "An npc's sprite path must not be null");
            Objects.requireNonNull(name, "An npc's name must not be null");
            Objects.requireNonNull(weekSchedule, "An npc's week schedule must not be null");
            Objects.requireNonNull(movementPath, "An npc's movement path must not be null");
            for(Position movingToPoint : movementPath){
                Objects.requireNonNull(movingToPoint, "An npc's movement path must not contain null");
            }
            Objects.requireNonNull(activity, "An npc's activity must not be null");
            Objects.requireNonNull(movementGraph, "An npc's movement graph must not be null");
            Objects.requireNonNull(dialogueOptions, "An npc's dialogue option list must not be null");
            for(Integer i: dialogueOptions){
                Objects.requireNonNull(i, "An npc's current dialogue option index must not be null");
            }
            return new NPC(this);
        }
    }
}
