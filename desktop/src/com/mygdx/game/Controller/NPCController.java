package com.mygdx.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.DaySchedule;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.NPC.WeekSchedule;

import java.util.ArrayList;

import static com.mygdx.game.Constants.JSON_PATHNAME;
public class NPCController {

    ArrayList<NPC> npcList;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController() {
        //npcList = NPCDAO.readNPCS();
    }

    /**
     * Draws all the npcs at their current location.
     * @param game The game object
     */
    public void drawNPCS(MyGame game){
        for(NPC npc: npcList){
            String pathName = JSON_PATHNAME + npc.getSpritePath();
            Texture texture = new Texture(Gdx.files.internal(pathName));
            game.batch.draw(texture, npc.getPosition().getX(), npc.getPosition().getY());
        }
    }

    public void moveNPCS(){
        for(NPC npc: npcList){
            int delta = (int) (70*Gdx.graphics.getDeltaTime());
            boolean right = npc.getPosition().getX() < npc.getMovementPath().get(0).getX();
            boolean up = npc.getPosition().getY() < npc.getMovementPath().get(0).getY();

            int newX = newPosition(npc.getPosition().getX(), delta, npc.getMovementPath().get(0).getX(), right);
            int newY = newPosition(npc.getPosition().getY(), delta, npc.getMovementPath().get(0).getY(), up);
            npc.getPosition().setX(newX);
            npc.getPosition().setY(newY);
        }
    }

    private int newPosition(int oldPosition, int delta, int goal, boolean positiveDirection){
        if(positiveDirection){
            int newPosition = (oldPosition + delta) < goal ? oldPosition + delta : goal;
            return newPosition;
        }
        int newPosition = goal < (oldPosition - delta) ? oldPosition - delta : goal;
        return newPosition;
    }

    public void updateNPCGoal(){
        for(NPC npc: npcList){
            WeekSchedule weekSchedule = npc.getWeekSchedule();
            // DaySchedule daySchedule = weekSchedule.getDaySchedules().get(Day.SUNDAY);
            // still need the day somewhere!
        }

    }




}
