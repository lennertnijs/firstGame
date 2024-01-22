package com.mygdx.game.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.MyGame;

import java.util.ArrayList;

import static com.mygdx.game.Constants.NPC_PNG_PATHNAME;
public class NPCController {

    ArrayList<NPC> npcList;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController() {
        npcList = NPCDAO.readNPCS();
    }

    /**
     * Draws all the npcs at their current location.
     * @param game The game object
     */
    public void drawNPCS(MyGame game){
        for(NPC npc: npcList){
            String pathName = NPC_PNG_PATHNAME + npc.getPng();
            Texture texture = new Texture(Gdx.files.internal(pathName));
            game.batch.draw(texture, npc.getX(), npc.getY());
        }
    }

    public void moveNPCS(){
        for(NPC npc: npcList){
            int delta = (int) (70*Gdx.graphics.getDeltaTime());
            boolean right = npc.getX() < npc.getGoalX();
            boolean up = npc.getY() < npc.getGoalY();

            int newX = newPosition(npc.getX(), delta, npc.getGoalX(), right);
            int newY = newPosition(npc.getY(), delta, npc.getGoalY(), up);
            npc.setX(newX);
            npc.setY(newY);
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
            DaySchedule daySchedule = weekSchedule.getDaySchedules().get(Day.MONDAY);
            // still need the day somewhere!
        }

    }




}
