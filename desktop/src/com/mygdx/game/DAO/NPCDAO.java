package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.NPC.*;
import com.mygdx.game.NPC.LinkedList.Node;

import java.util.ArrayList;

public class NPCDAO {
    public NPCDAO(){
    }

    /**
     * Reads the NPC.json file to initiate all NPCs and their schedules.
     * @return (to be added) each npc. CURRENTLY JUST WEEK SCHEDULE
     */
    public static ArrayList<NPC> readNPCS(){
        ArrayList<NPC> npcs = new ArrayList<>();
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal("resources/NPC.json"));

        WeekSchedule weekSchedule = new WeekSchedule();
        for(JsonValue npcJSON: file){
            String name = npcJSON.getString("name");
            int x = npcJSON.getInt("x");
            int y = npcJSON.getInt("y");

            JsonValue weekScheduleJSON = npcJSON.get("weekSchedule");
            for(JsonValue dayScheduleJSON : weekScheduleJSON){

                Day day = Day.valueOf(dayScheduleJSON.getString("day"));
                JsonValue activityList = dayScheduleJSON.get("daySchedule");
                DaySchedule daySchedule = fillInDaySchedule(new DaySchedule(day), activityList);

                weekSchedule.addDaySchedule(daySchedule);
            }
            NPC npc = NPC.builder().x(x).y(y).weekSchedule(weekSchedule).goalX(0).goalY(0).name(name).build();
            npcs.add(npc);
        }
        return npcs;
    }

    /**
     * Converts the {@code JsonValue} of the daily activities of an {@code NPC} to a {@code DaySchedule}
     * @param daySchedule An empty {@code DaySchedule}
     * @param jsonDaySchedule The {@code JsonValue}
     *
     * @return {@code DaySchedule} object
     */
    private static DaySchedule fillInDaySchedule(DaySchedule daySchedule, JsonValue jsonDaySchedule){
        for(JsonValue activity: jsonDaySchedule){
            String time = activity.getString("time");
            int x = activity.getInt("x");
            int y = activity.getInt("y");
            String animation = activity.getString("animation");
            String map = activity.getString("map");
            final Activity a = Activity.builder().x(x).y(y).animation(animation).screen(map).build();
            daySchedule.addActivity(new Node(time, a));
        }
        return daySchedule;
    }
}
