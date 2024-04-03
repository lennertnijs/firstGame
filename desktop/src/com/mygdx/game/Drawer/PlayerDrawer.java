package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerController;

import static com.mygdx.game.Constants.*;

public class PlayerDrawer {

    private final MyGame game;
    private final PlayerController playerController;
    private float timeElapsed = 0;
    private boolean playerInAnimation = false;
    private final InventoryDrawer inventoryDrawer;

    public PlayerDrawer(MyGame game, PlayerController playerController){
        this.game = game;
        this.playerController = playerController;
        this.inventoryDrawer = new InventoryDrawer(game);

    }

    /**
     * Draws the player character in the appropriate animation.
     */
    public void drawPlayer(){
        Activity activity = playerController.getPlayer().getActivity();
        inventoryDrawer.drawInventory(playerController.getPlayer().getInventory());
        if(activity == Activity.IDLING){
            drawPlayerIdle();
            return;
        }
        if(activity == Activity.WALKING){
            drawPlayerMoving();
        }
        if(activity == Activity.MINING){
            drawPlayerMining();
        }
    }

    /**
     * Handles the drawing of the player if they're idle.
     */
    private void drawPlayerIdle(){
        Player player = playerController.getPlayer();
        playerController.setActivity(Activity.IDLING);
        TextureRegion texture = player.getTextureRepository().getIdleTexture(player.getDirection());
        draw(texture);
    }


    /**
     * Handles the drawing of the player if they're mining.
     */
    private void drawPlayerMoving(){
        Player player = playerController.getPlayer();
        initiateAnimationIfNecessary();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = player.getDirection();
            TextureRegion texture = player.getTextureRepository()
                    .getMovingAnimation(direction).getKeyFrame(timeElapsed, false);
            draw(texture);
            return;
        }
        endAnimation();
        drawPlayerIdle();
    }

    private void drawPlayerMining(){
        Player player = playerController.getPlayer();
        initiateAnimationIfNecessary();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = player.getDirection();
            TextureRegion texture = player.getTextureRepository().getMiningAnimation(direction).getKeyFrame(timeElapsed, false);
            drawMining(texture);
            return;
        }
        endAnimation();
        drawPlayerIdle();
    }

    /**
     * Draws the texture at the player location. Helper function.
     */
    private void draw(TextureRegion texture){
        Player player = playerController.getPlayer();
        game.batch.draw(texture, player.getPosition().getX(), player.getPosition().getY(), PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    /**
     * Draws the texture at the player location. Helper function.
     */
    private void drawMining(TextureRegion texture){
        Player player = playerController.getPlayer();
        if(player.getDirection() == Direction.UP || player.getDirection() == Direction.DOWN){
            game.batch.draw(texture, player.getPosition().getX(), player.getPosition().getY(), 31*4, 42*4);
            return;
        }
        game.batch.draw(texture, player.getPosition().getX()-50, player.getPosition().getY(), 42*4, 42*4);
    }

    /**
     * Checks if the player is not in an animation already.
     * If they're freed up, initiates an animation sequence.
     */
    private void initiateAnimationIfNecessary(){
        if(!(playerInAnimation)){
            playerInAnimation = true;
            playerController.setInAnimation(true);
            timeElapsed = 0;
        }
    }

    /**
     * Ends the player's animation sequence.
     */
    private void endAnimation(){
        playerController.setInAnimation(false);
        timeElapsed = 0;
    }
}
