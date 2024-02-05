package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Direction;
import com.mygdx.game.Interactive.Interactive;
import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.ToolType;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerService;
import com.mygdx.game.Stone.Stone;
import com.mygdx.game.Controller.StoneController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Interactive.InteractiveController;
import com.mygdx.game.NPC.Activity;

import static com.mygdx.game.Constants.*;

public class KeyboardInputController {

    private final NPCController npcController;
    private final InteractiveController interactiveController;

    private final StoneController stoneController;
    private final PlayerService playerService;
    private final double sqrtTwo = 1/Math.sqrt(2);

    public KeyboardInputController(StoneController stoneController, NPCController npcController, InteractiveController  interactiveController, PlayerService playerService){
        this.stoneController = stoneController;
        this.npcController = npcController;
        this.interactiveController = interactiveController;
        this.playerService = playerService;

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
                playerService.setPosition(newPosition);
                handleOtherInputs();
                return;
            }
        }

        newPosition = handleOneMovementInput(up, right, down, left);
        boolean collisionWithNPC = npcController.checkCollision(newPosition);
        boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
        if(!collisionWithNPC && !collisionWithStone){
            playerService.setPosition(newPosition);
            handleOtherInputs();
        }
        handleOtherInputs();
    }

    private void handleOtherInputs(){

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            Item item = playerService.getPlayer().getCurrentItem();
            if(item instanceof Tool && ((Tool) item).getToolType() == ToolType.PICKAXE){
                Stone stone = stoneController.getCollisionStoneFromHitBox(playerService.getPlayer().getPosition(), PLAYER_WIDTH, PLAYER_HEIGHT);
                if(stone != null){
                    stoneController.mineStone(stone, 1);
                }
            }
            playerService.setActivity(Activity.WALKING);
                // call command pattern class
                // run animation
                // check interactive collision
                // command class runs execute() which calls interact() on whatever class necessary
        }
    }


    private Position handleTwoMovementInputs(boolean up, boolean right, boolean down, boolean left){
        int currentX = playerService.getPlayer().getPosition().getX();
        int currentY = playerService.getPlayer().getPosition().getY();
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
        int currentX = playerService.getPlayer().getPosition().getX();
        int currentY = playerService.getPlayer().getPosition().getY();
        int movement = (int)Math.ceil((200 * Gdx.graphics.getDeltaTime()));
        int diagonalMovement = (int)Math.ceil((movement*sqrtTwo));
        Position newPosition = null;
        if(up){
            newPosition = buildNewPosition(currentX, currentY + diagonalMovement);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            playerService.setDirection(Direction.UP);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(right){
            newPosition = buildNewPosition(currentX + diagonalMovement, currentY);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            playerService.setDirection(Direction.RIGHT);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(down){
            newPosition = buildNewPosition(currentX , currentY - diagonalMovement);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            playerService.setDirection(Direction.DOWN);
            if(!collisionWithNPC && !collisionWithStone){
                return newPosition;
            }
        }
        if(left){
            newPosition = buildNewPosition(currentX - diagonalMovement, currentY);
            boolean collisionWithNPC = npcController.checkCollision(newPosition);
            boolean collisionWithStone = stoneController.hitBoxCollidesWithStone(newPosition, NPC_WIDTH, NPC_HEIGHT);
            playerService.setDirection(Direction.LEFT);
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
