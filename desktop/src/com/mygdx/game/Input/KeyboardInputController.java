package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.Drawer.PlayerDrawer;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Interactive.Interactive;
import com.mygdx.game.Interactive.InteractiveController;
import com.mygdx.game.Item.ItemInstance;
import com.mygdx.game.Item.ToolInstance;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;

public class KeyboardInputController {

    private final NPCController npcController;
    private final InteractiveController interactiveController;
    private final Player player;
    private final PlayerDrawer playerDrawer;
    private final double sqrtTwo = 1/Math.sqrt(2);

    public KeyboardInputController(Player player, NPCController npcController, InteractiveController  interactiveController, PlayerDrawer playerDrawer){
        this.player = player;
        this.npcController = npcController;
        this.interactiveController = interactiveController;
        this.playerDrawer = playerDrawer;

    }

    public void handleMovement(){
        int movement = (int)Math.ceil((200 * Gdx.graphics.getDeltaTime()));
        int diagonalMovement = (int)Math.ceil((movement*sqrtTwo));
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        int currentX = player.getPosition().getX();
        int currentY = player.getPosition().getY();

        Position newPosition = Position.builder().x(currentX).y(currentY).build();

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            player.setActivity(Activity.WALKING);
            Interactive interactive = interactiveController.checkInteractions(player.getPosition().getX()+50, player.getPosition().getY());
        }

        if(up && down || left && right){
            return;
        }
        if(up){
            newPosition = buildNewPosition(currentX, currentY + movement);
        }
        if(right){
            newPosition = buildNewPosition(currentX + movement, currentY);
        }
        if(down){
            newPosition = buildNewPosition(currentX , currentY - movement);
        }
        if(left){
            newPosition = buildNewPosition(currentX - movement, currentY);
        }
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
        boolean collisionWithNPC = npcController.checkCollision(newPosition);
        if(!collisionWithNPC){
            player.setPosition(newPosition);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            ItemInstance itemInstance = player.getCurrentItem();
            if(itemInstance instanceof ToolInstance){
                // call command pattern class
                // run animation
                // check interactive collision
                // command class runs execute() which calls interact() on whatever class necessary
            }
        }
        playerDrawer.drawPlayer(player);
    }

    private Position buildNewPosition(int x, int y){
        return Position.builder().x(x).y(y).build();
    }
}
