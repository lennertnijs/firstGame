package com.mygdx.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.NPC.WeekSchedule;

import java.util.ArrayList;

public class NPCController {

    final Clock clock;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(Clock clock) {
        this.clock = clock;
    }

    /**
     * Draws all the npcs at their current location.
     * @param game The game object
     */
    public void drawNPCS(MyGame game){
//        for(NPC npc: npcList){
//            String pathName = "images/" + npc.getSpritePath();
//            Texture texture = new Texture(Gdx.files.internal(pathName));
//            game.batch.draw(texture, npc.getPosition().getX(), npc.getPosition().getY());
//        }
    }

    public void moveNPCS(){
//        for(NPC npc: npcList){
//            NPCMover.move(npc);
//        }
    }


    public void updateNPCGoal(){
//        for(NPC npc: npcList){
//            WeekSchedule weekSchedule = npc.getWeekSchedule();
//            // DaySchedule daySchedule = weekSchedule.getDaySchedules().get(Day.SUNDAY);
//            // still need the day somewhere!
//        }

    }




}
