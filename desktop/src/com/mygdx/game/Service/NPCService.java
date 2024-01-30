package com.mygdx.game.Service;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Clock.*;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.Drawer.NPCDrawerRepository;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.*;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Constants.*;

public class NPCService {

    private final NPCDrawerRepository npcDrawerRepository;
    private final ClockService clockService;

    public NPCService(ClockService clockService, NPCDrawerRepository npcDrawerRepository){
        this.clockService = clockService;
        this.npcDrawerRepository = npcDrawerRepository;
    }

    public List<NPC> getAllNPCS(){
        return npcDrawerRepository.getAllNpcs();
    }

    /**
     * Loads all NPC's into memory from the json file.
     * Should only be called once at startup.
     */
    public void loadNPCS(){
        NPCDAO npcdao = new NPCDAO();
        ArrayList<NPC> npcs = npcdao.readNPCS();
        //
    }

    public void updateNPCS(){
        for(NPC npc: npcDrawerRepository.getAllNpcs()){
            boolean isMoving = !npc.getMovementPath().isEmpty();
            if(isMoving){
                move(npc);
            }else{
                checkMove(npc);
            }
        }
    }

    public boolean collides(Position playerPosition){
        int x = playerPosition.getX();
        int y = playerPosition.getY();
        for(NPC npc: npcDrawerRepository.getAllNpcs()){
            int npcX = npc.getPosition().getX();
            int npcY = npc.getPosition().getY();
            boolean collides = x <= npcX + NPC_WIDTH && npcX <= x + PLAYER_WIDTH &&
                               y <= npcY + NPC_HEIGHT && npcY <= y + PLAYER_HEIGHT;
            if(collides){
                return true;
            }
        }
        return false;
    }

    private void checkMove(NPC npc){
        Day day = clockService.getClock().getDay();
        int timeInMinutes = clockService.getClock().getTimeInMinutes();
        DaySchedule daySchedule = npc.getWeekSchedule().getDaySchedule(day);
        ActivityInstance nextActivity = daySchedule.nextActivityAfterTime(timeInMinutes);
        boolean noMoreActivitiesInDay = nextActivity == null;
        if(noMoreActivitiesInDay){
            day = day.next();
            daySchedule = npc.getWeekSchedule().getDaySchedule(day);
            nextActivity = daySchedule.nextActivityAfterTime(0);
        }
        boolean startNextActivity = timeInMinutes >= nextActivity.getStartTimeInMinutes();
        boolean correctDay = day == clockService.getClock().getDay();
        if(startNextActivity && correctDay){
            MovementGraph graph = npc.getMovementGraph();
            npc.setMovementPath(graph.findPath(npc.getPosition(), nextActivity.getPosition()));
        }
    }


    private void move(NPC npc){
        Position current = npc.getPosition();
        Position next = npc.getMovementPath().get(0);
        boolean verticalMovement = (current.getX() == next.getX());
        if(verticalMovement){
            verticalMovement(npc);
        }else{
            horizontalMovement(npc);
        }
    }

    private void verticalMovement(NPC npc){
        int currentY = npc.getPosition().getY();
        int goalY = npc.getMovementPath().get(0).getY();
        int nextY = calculateNextValue(currentY, goalY);
        Position newPosition = Position.builder().x(npc.getPosition().getX()).y(nextY).build();
        npc.setPosition(newPosition);
        if(nextY == goalY){
            removeFirstPositionFromPath(npc);
        }
    }

    private void horizontalMovement(NPC npc){
        int currentX = npc.getPosition().getX();
        int goalX = npc.getMovementPath().get(0).getX();
        int nextX = calculateNextValue(currentX, goalX);
        Position newPosition = Position.builder().x(nextX).y(npc.getPosition().getY()).build();
        npc.setPosition(newPosition);
        if(nextX == goalX){
            removeFirstPositionFromPath(npc);
        }
    }

    private int calculateNextValue(int current, int goal){
        int movement = Math.round(STANDARD_MOVEMENT_SPEED*Gdx.graphics.getDeltaTime());
        boolean movingPositive = current < goal;
        int nextValue;
        if(movingPositive){
            nextValue = Math.min(current + movement, goal);
        }else{
            nextValue = Math.max(current - movement, goal);
        }
        return nextValue;
    }

    private void removeFirstPositionFromPath(NPC npc){
        npc.getMovementPath().remove(0);
    }

    public static int getInt(){
        return 5;
    }
}
