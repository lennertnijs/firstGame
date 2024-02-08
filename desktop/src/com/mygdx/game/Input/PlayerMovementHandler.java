package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Direction;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerService;

public class PlayerMovementHandler {

    private final PlayerService playerService;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private boolean left = false;
    private final double sqrtTwo = 1/Math.sqrt(2);

    public PlayerMovementHandler(PlayerService playerService){
        this.playerService = playerService;
    }

    public void up(boolean movingUp){
        this.up = movingUp;
    }

    public void right(boolean movingRight){
        this.right = movingRight;
    }

    public void down(boolean movingDown){
        this.down = movingDown;
        System.out.println(movingDown);
    }

    public void left(boolean movingLeft){
        this.left = movingLeft;
    }

    public void movePlayer(){
        if(up || right || down || left){
            System.out.println(up + " - " + right + " - " + down + " - " + left);
        }


        if(up && right && down && left){
            playerService.setActivity(Activity.IDLING);
            return;
        }
        // 3
        if(up && right && down){
            playerService.setActivity(Activity.WALKING);
            moveRight();
            return;
        }
        if(right && down && left){
            playerService.setActivity(Activity.WALKING);
            moveDown();
            return;
        }
        if(down && left && up){
            playerService.setActivity(Activity.WALKING);
            moveLeft();
            return;
        }
        if(left && up && right){
            playerService.setActivity(Activity.WALKING);
            moveUp();
            return;
        }
        if(up && down || right && left){
            playerService.setActivity(Activity.IDLING);
            return;
        }
        // 2
        if(up && right){
            playerService.setActivity(Activity.WALKING);
            moveUpRight();
            return;
        }
        if(right && down){
            playerService.setActivity(Activity.WALKING);
            moveRightDown();
            return;
        }
        if(down && left){
            playerService.setActivity(Activity.WALKING);
            moveDownLeft();
            return;
        }
        if(left && up){
            playerService.setActivity(Activity.WALKING);
            moveLeftUp();
            return;
        }
        //1
        if(up){
            playerService.setActivity(Activity.WALKING);
            moveUp();
            return;
        }
        if(right){
            playerService.setActivity(Activity.WALKING);
            moveRight();
            return ;
        }
        if(down){
            playerService.setActivity(Activity.WALKING);
            moveDown();
            return;
        }
        if(left){
            playerService.setActivity(Activity.WALKING);
            moveLeft();
            return;
        }
        playerService.setActivity(Activity.IDLING);
    }


    private void moveUpRight(){
        playerService.setDirection(Direction.UP);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int)(Math.ceil(200*Gdx.graphics.getDeltaTime())*sqrtTwo);
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX + movement, currentY + movement);
        }
    }

    private void moveRightDown(){
        playerService.setDirection(Direction.DOWN);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int)(Math.ceil(200*Gdx.graphics.getDeltaTime())*sqrtTwo);
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX + movement, currentY - movement);
        }
    }

    private void moveDownLeft(){
        playerService.setDirection(Direction.DOWN);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int)(Math.ceil(200*Gdx.graphics.getDeltaTime())*sqrtTwo);
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX - movement, currentY - movement);
        }
    }

    private void moveLeftUp(){
        playerService.setDirection(Direction.UP);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int)(Math.ceil(200*Gdx.graphics.getDeltaTime())*sqrtTwo);
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX - movement, currentY + movement);
        }
    }

    private void moveUp(){
        playerService.setDirection(Direction.UP);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200* Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX, currentY + movement);
        }
    }

    private void moveRight(){
        playerService.setDirection(Direction.RIGHT);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200* Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX + movement, currentY);
        }
    }

    private void moveDown(){
        playerService.setDirection(Direction.DOWN);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200* Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX, currentY - movement);
        }
    }

    private void moveLeft(){
        playerService.setDirection(Direction.LEFT);
        Player player = playerService.getPlayer();
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();
        int movement = (int) Math.ceil(200* Gdx.graphics.getDeltaTime());
        boolean collision = anyCollisions(player);
        if(!collision){
            playerService.setPosition(currentX - movement, currentY);
        }
    }

    private boolean anyCollisions(Player player){
        boolean collidesWithNPC = false;
        boolean collidesWithStone = false;
        return collidesWithNPC && collidesWithStone;
    }
}
