package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Direction;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerService;

import java.util.ArrayList;

public class PlayerMovementHandler {

    private final PlayerService playerService;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;
    private final ArrayList<Direction> inputs = new ArrayList<>();

    public PlayerMovementHandler(PlayerService playerService){
        this.playerService = playerService;
    }

    protected void movingUp(){
        this.up = true;
        inputs.add(Direction.UP);
    }

    protected void notMovingUp(){
        this.up = false;
        inputs.remove(Direction.UP);
    }

    protected void movingRight(){
        this.right = true;
        inputs.add(Direction.RIGHT);
    }

    protected void notMovingRight(){
        this.right = false;
        inputs.remove(Direction.RIGHT);
    }

    protected void movingDown(){
        this.down = true;
        inputs.add(Direction.DOWN);
    }

    protected void notMovingDown(){
        this.down = false;
        inputs.remove(Direction.DOWN);
    }

    protected void movingLeft(){
        this.left = true;
        inputs.add(Direction.LEFT);
    }

    protected void notMovingLeft(){
        this.left = false;
        inputs.remove(Direction.LEFT);
    }


    public void movePlayer(){
        boolean fourMovementInputs = up && right && down && left;
        if(fourMovementInputs){
            //playerService.setActivity(Activity.IDLING);
            return;
        }

        boolean threeMovementInputs = (up && right && down) || (right && down && left)
                                    || (down && left && up) || (left && up && right);
        if(threeMovementInputs){
            handleThreeMovementInputs();
            return;
        }

        boolean twoOppositeInputs = up && down || left && right;
        if(twoOppositeInputs){
            //playerService.setActivity(Activity.IDLING);
            return;
        }

        boolean twoMovementInput = (up && right) || (right && down) || (down && left) || (left && up);
        if(twoMovementInput){
            handleTwoMovementInputs();
            return;
        }

        boolean oneMovementInput = up || right || down || left;
        if(oneMovementInput){
            handleOneMovementInput();
            return;
        }
        //playerService.setActivity(Activity.IDLING);
    }

    private void handleTwoMovementInputs(){
        Direction lastInputDirection = inputs.get(1);
        switch (lastInputDirection){
            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

    private void handleThreeMovementInputs(){
        playerService.setActivity(Activity.WALKING);
        if(up && right && down){
            moveRight();
            return;
        }
        if(right && down && left){
            moveDown();
            return;
        }
        if(down && left && up){
            moveLeft();
            return;
        }
        if(left && up && right){
            moveUp();
        }
    }

    private void handleOneMovementInput(){
        playerService.setActivity(Activity.WALKING);
        if(up){
            moveUp();
            return;
        }
        if(right){
            moveRight();
            return ;
        }
        if(down){
            moveDown();
            return;
        }
        if(left){
            moveLeft();
        }
    }

    private void moveUp(){
        playerService.setDirection(Direction.UP);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200 * Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions();
        if(!collision){
            playerService.setPosition(currentX, currentY + movement);
        }
    }

    private void moveRight(){
        playerService.setDirection(Direction.RIGHT);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200 * Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions();
        if(!collision){
            playerService.setPosition(currentX + movement, currentY);
        }
    }

    private void moveDown(){
        playerService.setDirection(Direction.DOWN);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200 * Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions();
        if(!collision){
            playerService.setPosition(currentX, currentY - movement);
        }
    }

    private void moveLeft(){
        playerService.setDirection(Direction.LEFT);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200 * Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions();
        if(!collision){
            playerService.setPosition(currentX - movement, currentY);
        }
    }

    private boolean anyCollisions(){
        boolean collidesWithNPC = false;
        boolean collidesWithStone = false;
        return collidesWithNPC && collidesWithStone;
    }
}
