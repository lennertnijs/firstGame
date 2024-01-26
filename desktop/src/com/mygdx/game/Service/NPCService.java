package com.mygdx.game.Service;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.DAO.NPCDAO;
import com.mygdx.game.Entity.Position2D;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.NPC.NPCRepository;

import java.util.ArrayList;

import static com.mygdx.game.Constants.STANDARD_MOVEMENT_SPEED;

public class NPCService {

    private final NPCRepository repository;
    public NPCService(NPCRepository npcRepository){
        this.repository = npcRepository;
    }

    // draw npcs
    // update npc schedules
    // all npc logic

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
    public void move(NPC npc){
        boolean isMoving = !npc.getMovementPath().isEmpty();
        if(isMoving){
            Position2D current = npc.getPosition();
            Position2D next = npc.getMovementPath().get(0);
            boolean verticalMovement = (current.getX() == next.getX());
            if(verticalMovement){
                verticalMovement(npc);
            }else{
                horizontalMovement(npc);
            }
        }else{
            // on arrival of final node, update next move & then every frame check the time

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
