package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.*;

import java.util.ArrayList;
import java.util.HashMap;

import static com.mygdx.game.Constants.MINUTES_PER_HOUR;
import static com.mygdx.game.Constants.NPC_PATHNAME;
import static java.lang.Integer.*;

public class NPCDAO {
    public NPCDAO(){
    }

    public ArrayList<NPC> readNPCS(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/NPC.json"));

        ArrayList<NPC> npcs = new ArrayList<>();
        for(JsonValue npcJSON: file){
            NPC npc = readNPC(npcJSON);
            npcs.add(npc);
        }
        return npcs;
    }


    /**
     * Reads a npc and returns it
     */
    private NPC readNPC(JsonValue npcJSON){
        String name = npcJSON.getString("name");

        int x = npcJSON.getInt("x");
        int y = npcJSON.getInt("y");
        Position position = Position.builder().x(x).y(y).build();

        String spritePath = npcJSON.getString("spritePath");

        Activity activity = Activity.valueOf(npcJSON.getString("activity"));

        JsonValue weekScheduleJSON = npcJSON.get("weekSchedule");
        WeekSchedule weekSchedule = readWeekSchedule(weekScheduleJSON);

        JsonValue movementGraphJSON = npcJSON.get("movementGraph");
        MovementGraph movementGraph = readMovementGraph(movementGraphJSON);

        JsonValue dialogueOptionsJSON = npcJSON.get("dialogueOptions");
        ArrayList<Integer> dialogueOptions = readDialogueOptions(dialogueOptionsJSON);

        return NPC.builder()
                .position(position)
                .spritePath(NPC_PATHNAME + spritePath)
                .name(name)
                .activity(activity)
                .weekSchedule(weekSchedule)
                .movementPath(new ArrayList<>())
                .movementGraph(movementGraph)
                .dialogueOptions(dialogueOptions)
                .build();
    }

    /**
     * Reads the week schedule
     */
    private WeekSchedule readWeekSchedule(JsonValue weekScheduleJSON){
        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        for(JsonValue dayScheduleJSON : weekScheduleJSON){
            Day day = Day.valueOf(dayScheduleJSON.getString("day"));

            ArrayList<ActivityInstance> activities = readDayActivities(dayScheduleJSON.get("daySchedule"));

            DaySchedule daySchedule = DaySchedule.builder().activities(activities).build();

            daySchedules.put(day, daySchedule);
        }
        return WeekSchedule.builder().daySchedules(daySchedules).build();
    }



    /**
     * Reads all activities of a given day
     */
    private ArrayList<ActivityInstance> readDayActivities(JsonValue dayScheduleActivitiesJSON){
        ArrayList<ActivityInstance> activityInstances = new ArrayList<>();
        for(JsonValue activityJSON: dayScheduleActivitiesJSON){
            final String time = activityJSON.getString("time");
            final int timeInMinutes = timeStringToMinutes(time);
            final int x = activityJSON.getInt("x");
            final int y = activityJSON.getInt("y");
            final Position position = Position.builder().x(x).y(y).build();
            final Activity activity = Activity.valueOf(activityJSON.getString("activity"));
            final Map map = Map.valueOf(activityJSON.getString("map"));
            final ActivityInstance a = ActivityInstance.builder()
                            .position(position)
                            .startTimeInMinutes(timeInMinutes)
                            .activity(activity)
                            .map(map)
                            .build();
            activityInstances.add(a);
        }
        return activityInstances;
    }



    /**
     * Reads the movement graph and returns it
     */
    private MovementGraph readMovementGraph(JsonValue movementGraphJSON){
        HashMap<Position, ArrayList<Position>> network = new HashMap<>();
        for(JsonValue node : movementGraphJSON){
            int nodeX = node.getInt("x");
            int nodeY = node.getInt("y");
            Position nodePosition = Position.builder().x(nodeX).y(nodeY).build();
            ArrayList<Position> nextNodePositions = readNextNodes(node.get("connected"));
            network.put(nodePosition, nextNodePositions);
        }
        return MovementGraph.builder().movementGraph(network).build();
    }


    /**
     * Reads the next nodes of the movement graph and returns them
     */
    private ArrayList<Position> readNextNodes(JsonValue nodeJSON){
        ArrayList<Position> connectedNodes = new ArrayList<>();
        for(JsonValue connectedNode : nodeJSON){
            int nodeX = connectedNode.getInt("x");
            int nodeY = connectedNode.getInt("y");
            Position position = Position.builder().x(nodeX).y(nodeY).build();
            connectedNodes.add(position);
        }
        return connectedNodes;
    }



    /**
     * Reads the dialogue options and returns them
     */
    private ArrayList<Integer> readDialogueOptions(JsonValue dialogueOptionsJSON){
        ArrayList<Integer> dialogueOptions = new ArrayList<>();
        for(JsonValue dialogueOptionJSON : dialogueOptionsJSON){
            int dialogueIndex = dialogueOptionJSON.getInt("index");
            dialogueOptions.add(dialogueIndex);
        }
        return dialogueOptions;
    }



    /**
     * Converts a time string HH:MM to an integer (the time in minutes, to be precise)
     */
    private int timeStringToMinutes(String time){
        String[] array = time.split(":");
        int hours = parseInt(array[0]);
        int minutes = parseInt(array[1]);
        return hours*MINUTES_PER_HOUR + minutes;
    }
}
