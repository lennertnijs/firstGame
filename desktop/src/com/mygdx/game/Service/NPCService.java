package com.mygdx.game.Service;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Clock.*;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.Entity.Position2D;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.*;

import java.util.ArrayList;
import java.util.Objects;

import static com.mygdx.game.Constants.STANDARD_MOVEMENT_SPEED;

public class NPCService {

    private final NPCRepository repository;
    private final Clock clock;
    public NPCService(NPCRepository npcRepository, Clock clock){
        Objects.requireNonNull(clock, "The clock of the npc service must not be null");
        Objects.requireNonNull(npcRepository, "The npc repository of the npc service must not be null");
        this.repository = npcRepository;
        this.clock = clock;
    }

    public ArrayList<NPC> getAllNPCS(){
        return repository.getNpcs();
    }

    /**
     * Loads all NPC's into memory from the json file.
     * Should only be called once at startup.
     */
    public void loadNPCS(){
        NPCDAO npcdao = new NPCDAO();
        ArrayList<NPC> npcs = npcdao.readNPCS();
        for(NPC npc: npcs){
            repository.add(npc);
        }
    }

    public void updateNPCS(){
        for(NPC npc: repository.getNpcs()){
            boolean isMoving = !npc.getMovementPath().isEmpty();
            if(isMoving){
                move(npc);
            }else{
                checkMove(npc);
            }
        }
    }

    public void checkMove(NPC npc){
        Day day = clock.getDay();
        int timeInMinutes = clock.getTimeInMinutes();
        DaySchedule daySchedule = npc.getWeekSchedule().getDaySchedule(day);
        ActivityInstance nextActivity = daySchedule.nextActivity(timeInMinutes);
        boolean noMorActivitiesToday = nextActivity == null;
        if(noMorActivitiesToday){
            daySchedule = npc.getWeekSchedule().getDaySchedule(day.next());
            nextActivity = daySchedule.nextActivity(0);
        }
        boolean timeToStartActivity = timeInMinutes == nextActivity.getTimeInMinutes();
        if(timeToStartActivity){
            MovementGraph movementGraph = npc.getMovementGraph();
            npc.setMovementPath(movementGraph.findPath(npc.getPosition(), nextActivity.getPosition()));
        }

    }


    public void move(NPC npc){
        Position2D current = npc.getPosition();
        Position2D next = npc.getMovementPath().get(0);
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
        npc.getPosition().setY(nextY);
        if(nextY == goalY){
            removeFirstPositionFromPath(npc);
        }
    }

    private void horizontalMovement(NPC npc){
        int currentX = npc.getPosition().getX();
        int goalX = npc.getMovementPath().get(0).getX();
        int nextX = calculateNextValue(currentX, goalX);
        npc.getPosition().setX(nextX);
        if(nextX == goalX){
            removeFirstPositionFromPath(npc);
        }
    }

    private int calculateNextValue(int current, int goal){
        int movement = Math.round(STANDARD_MOVEMENT_SPEED* Gdx.graphics.getDeltaTime());
        boolean movingPositive = current < goal;
        int nextValue;
        if(movingPositive){
            nextValue = current + movement < goal ? current + movement : goal;
        }else{
            nextValue = current - movement > goal ? current - movement : goal;
        }
        return nextValue;
    }

    private void removeFirstPositionFromPath(NPC npc){
        npc.getMovementPath().remove(0);
    }
}
