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
        ArrayList<NPC> npcs = new ArrayList<>();
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/NPC.json"));
        for(JsonValue npcJSON: file){
            String name = npcJSON.getString("name");
            int x = npcJSON.getInt("x");
            int y = npcJSON.getInt("y");
            Position position = Position.builder().x(x).y(y).build();
            Activity activity = Activity.valueOf(npcJSON.getString("activity"));
            String spritePath = npcJSON.getString("spritePath");

            JsonValue weekScheduleJSON = npcJSON.get("weekSchedule");
            ArrayList<DaySchedule> daySchedules = new ArrayList<>();
            for(JsonValue dayScheduleJSON : weekScheduleJSON){
                Day day = Day.valueOf(dayScheduleJSON.getString("day"));
                ArrayList<ActivityInstance> activities = readDayActivities(dayScheduleJSON.get("daySchedule"));
                DaySchedule daySchedule = DaySchedule.builder().day(day).activities(activities).build();
                daySchedules.add(daySchedule);
            }
            WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

            JsonValue movementGraphJSON = npcJSON.get("movementGraph");
            HashMap<Position, ArrayList<Position>> network = new HashMap<>();
            for(JsonValue node : movementGraphJSON){
                int nodeX = node.getInt("x");
                int nodeY = node.getInt("y");
                Position nodePosition = Position.builder().x(nodeX).y(nodeY).build();
                ArrayList<Position> nextNodePositions = readNextNodes(node.get("connected"));
                network.put(nodePosition, nextNodePositions);
            }

            MovementGraph movementGraph = MovementGraph.builder().movementGraph(network).build();

            JsonValue dialogueOptionsJSON = npcJSON.get("dialogueOptions");
            ArrayList<Integer> dialogueOptions = new ArrayList<>();
            for(JsonValue dialogueOptionJSON : dialogueOptionsJSON){
                int dialogueIndex = dialogueOptionJSON.getInt("index");
                dialogueOptions.add(dialogueIndex);
            }

            NPC npc = NPC.builder()
                    .position(position)
                    .spritePath(NPC_PATHNAME + spritePath)
                    .name(name)
                    .activity(activity)
                    .weekSchedule(weekSchedule)
                    .movementPath(new ArrayList<>())
                    .movementGraph(movementGraph)
                    .dialogueOptions(dialogueOptions)
                    .build();
            npcs.add(npc);

        }
        return npcs;
    }

    private ArrayList<ActivityInstance> readDayActivities(JsonValue dayScheduleJSON){
        ArrayList<ActivityInstance> activityInstances = new ArrayList<>();
        for(JsonValue activityJSON: dayScheduleJSON){
            final String time = activityJSON.getString("time");
            final int timeInMinutes = timeStringToMinutes(time);
            final int x = activityJSON.getInt("x");
            final int y = activityJSON.getInt("y");
            final Position position = Position.builder().x(x).y(y).build();
            final Activity activity = Activity.valueOf(activityJSON.getString("activity"));
            final Map map = Map.valueOf(activityJSON.getString("map"));
            final ActivityInstance a = ActivityInstance.builder()
                            .position(position)
                            .timeInMinutes(timeInMinutes)
                            .activity(activity)
                            .map(map)
                            .build();
            activityInstances.add(a);
        }
        return activityInstances;
    }

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
     * Converts a time string HH:MM to an integer (the time in minutes, to be precise)
     */
    private int timeStringToMinutes(String time){
        String[] array = time.split(":");
        int hours = parseInt(array[0]);
        int minutes = parseInt(array[1]);
        return hours*MINUTES_PER_HOUR + minutes;
    }
}
