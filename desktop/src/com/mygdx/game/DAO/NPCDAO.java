package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.Direction;
import com.mygdx.game.Drawer.NPCDrawer;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.*;
import com.mygdx.game.TextureRepository.CharacterTextureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mygdx.game.Constants.MINUTES_PER_HOUR;
import static com.mygdx.game.Constants.NPC_PATHNAME;
import static java.lang.Integer.*;

public class NPCDAO {
    public NPCDAO(){
    }

    public List<NPC> readNPCS(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/NPC.json"));

        List<NPC> npcs = new ArrayList<>();
        for(JsonValue npcJSON: file){
            npcs.add(readNPC(npcJSON));
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

        JsonValue idlingJSON = npcJSON.get("idle");
        HashMap<Direction, Texture> idleMap = readNPCIdleTextures(idlingJSON);

        JsonValue movingJSON = npcJSON.get("moving");
        HashMap<Direction, Animation<Texture>> movingMap = readNPCMovingTextures(movingJSON);

        CharacterTextureRepository textureRepository = CharacterTextureRepository.builder().idleTextures(idleMap)
                .movementAnimations(movingMap).build();

        NPC npc =  NPC.builder()
                .position(position)
                .spritePath(NPC_PATHNAME + spritePath)
                .name(name)
                .activity(activity)
                .weekSchedule(weekSchedule)
                .movementPath(new ArrayList<>())
                .movementGraph(movementGraph)
                .dialogueOptions(dialogueOptions)
                .characterTextureRepository(textureRepository)
                .build();
        return npc;
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

    private HashMap<Direction, Texture> readNPCIdleTextures(JsonValue idleTexturesJSON){
        HashMap<Direction, Texture> idleMap = new HashMap<>();
        for(JsonValue value: idleTexturesJSON){
            idleMap.put(Direction.UP, new Texture(value.getString("up")));
            idleMap.put(Direction.RIGHT, new Texture(value.getString("right")));
            idleMap.put(Direction.DOWN, new Texture(value.getString("down")));
            idleMap.put(Direction.LEFT, new Texture(value.getString("left")));
        }
        return idleMap;
    }

    private HashMap<Direction, Animation<Texture>> readNPCMovingTextures(JsonValue movingTexturesJSON){
        HashMap<Direction, Animation<Texture>> movingMap = new HashMap<>();
        for(JsonValue value: movingTexturesJSON){
            movingMap.put(Direction.UP, readNPCAnimation(value.get("up")));
            movingMap.put(Direction.RIGHT, readNPCAnimation(value.get("right")));
            movingMap.put(Direction.DOWN, readNPCAnimation(value.get("down")));
            movingMap.put(Direction.LEFT, readNPCAnimation(value.get("left")));
        }
        return movingMap;

    }

    private Animation<Texture> readNPCAnimation(JsonValue animationJSON){
        int i = 0;
        Texture[] textures = new Texture[8];
        for(JsonValue value: animationJSON){
            textures[0] = new Texture(value.getString("first"));
            textures[1] = new Texture(value.getString("second"));
            textures[2] = new Texture(value.getString("third"));
            textures[3] = new Texture(value.getString("fourth"));
            textures[4] = new Texture(value.getString("fifth"));
            textures[5] = new Texture(value.getString("sixth"));
            textures[6] = new Texture(value.getString("seventh"));
            textures[7] = new Texture(value.getString("eight"));

        }
        Animation<Texture> animation = new Animation<>(0.125F, textures);

        return animation;
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
