package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Controller.PlayerController;
import com.mygdx.game.Interactive.Interactive;
import com.mygdx.game.Stone.Stone;
import com.mygdx.game.Stone.StoneController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Interactive.InteractiveController;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Item.ToolInstance;
import com.mygdx.game.NPC.Activity;

import static com.mygdx.game.Constants.NPC_HEIGHT;
import static com.mygdx.game.Constants.NPC_WIDTH;

public class KeyboardInputController {

    private final NPCController npcController;
    private final InteractiveController interactiveController;

    private final StoneController stoneController;
    private final PlayerController playerController;
    private final double sqrtTwo = 1/Math.sqrt(2);

    public KeyboardInputController(StoneController stoneController, NPCController npcController, InteractiveController  interactiveController, PlayerController playerController){
        this.stoneController = stoneController;
        this.npcController = npcController;
        this.interactiveController = interactiveController;
        this.playerController = playerController;

    }

    public void handleMovement(){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        Position newPosition;

        boolean anyMovement = up || right || down || left;
        boolean oppositeDirection = up && down || left && right;
        if(oppositeDirection || !anyMovement){
            handleOtherInputs();
            return;
        }

        boolean twoDirections = up && right || right && down || down && left || left && up;
        if(twoDirections){
            newPosition = handleTwoMovementInputs(up, right, down, left);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                playerController.getPlayer().setPosition(newPosition);
                handleOtherInputs();
                return;
            }
        }

        newPosition = handleOneMovementInput(up, right, down, left);
        boolean collisionWithNPC = npcController.checkCollision(newPosition);
        boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
        if(!collisionWithNPC && !collisionWithStone){
            playerController.getPlayer().setPosition(newPosition);
            handleOtherInputs();
        }
        handleOtherInputs();
    }

    private void handleOtherInputs(){

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            ItemInstance itemInstance = playerController.getPlayer().getCurrentItem();
            if(itemInstance instanceof ToolInstance){
                // call command pattern class
                // run animation
                // check interactive collision
                // command class runs execute() which calls interact() on whatever class necessary
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            playerController.getPlayer().setActivity(Activity.WALKING);
            Interactive interactive = interactiveController.checkInteractions(playerController.getPlayer().getPosition().getX()+120, playerController.getPlayer().getPosition().getY()+50);
            if(interactive instanceof Stone){
                Position position = Position.builder().x(playerController.getPlayer().getPosition().getX()+120).y(playerController.getPlayer().getPosition().getY()+50).build();
                Stone stone = stoneController.getCollisionStoneFromPoint(position);
                if(stone != null){
                    stoneController.mineStone(stone, 30);
                }

            }
        }
    }


    private Position handleTwoMovementInputs(boolean up, boolean right, boolean down, boolean left){
        int currentX = playerController.getPlayer().getPosition().getX();
        int currentY = playerController.getPlayer().getPosition().getY();
        int movement = (int)Math.ceil((200 * Gdx.graphics.getDeltaTime()));
        int diagonalMovement = (int)Math.ceil((movement*sqrtTwo));
        Position newPosition = null;
        if(up && right){
            newPosition = buildNewPosition(currentX + diagonalMovement, currentY + diagonalMovement);
        }
        if(right && down){
            newPosition = buildNewPosition(currentX + diagonalMovement, currentY - diagonalMovement);
        }
        if(down && left){
            newPosition = buildNewPosition(currentX - diagonalMovement, currentY - diagonalMovement);
        }
        if(left && up){
            newPosition = buildNewPosition(currentX - diagonalMovement, currentY + diagonalMovement);
        }
        return newPosition;
    }

    private Position handleOneMovementInput(boolean up, boolean right, boolean down, boolean left){
        int currentX = playerController.getPlayer().getPosition().getX();
        int currentY = playerController.getPlayer().getPosition().getY();
        int movement = (int)Math.ceil((200 * Gdx.graphics.getDeltaTime()));
        int diagonalMovement = (int)Math.ceil((movement*sqrtTwo));
        Position newPosition = null;
        if(up){
            newPosition = buildNewPosition(currentX, currentY + diagonalMovement);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(right){
            newPosition = buildNewPosition(currentX + diagonalMovement, currentY);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(down){
            newPosition = buildNewPosition(currentX , currentY - diagonalMovement);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(left){
            newPosition = buildNewPosition(currentX - diagonalMovement, currentY);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        return newPosition;
    }

    private Position buildNewPosition(int x, int y){
        return Position.builder().x(x).y(y).build();
    }
}
