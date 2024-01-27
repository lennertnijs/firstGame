package com.mygdx.game.Service;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Clock.*;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.*;

import java.util.ArrayList;
import java.util.Objects;

import static com.mygdx.game.Constants.STANDARD_MOVEMENT_SPEED;

public class NPCService {

    private final NPCRepository repository;
    private final Clock clock;
    public NPCService(Clock clock){
        Objects.requireNonNull(clock, "The clock of the npc service must not be null");
        this.repository = new NPCRepository();
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
        ActivityInstance nextActivity = daySchedule.nextActivityAfterTime(timeInMinutes);
        boolean noMoreActivitiesInDay = nextActivity == null;
        if(noMoreActivitiesInDay){
            day = day.next();
            daySchedule = npc.getWeekSchedule().getDaySchedule(day);
            nextActivity = daySchedule.nextActivityAfterTime(0);
        }
        boolean startNextActivity = timeInMinutes >= nextActivity.getStartTimeInMinutes();
        boolean correctDay = day == clock.getDay();
        if(startNextActivity && correctDay){
            MovementGraph graph = npc.getMovementGraph();
            npc.setMovementPath(graph.findPath(npc.getPosition(), nextActivity.getPosition()));
        }
    }


    public void move(NPC npc){
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
}
