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

    public PlayerDrawer(MyGame game, PlayerController playerController){
        this.game = game;
        this.playerController = playerController;
    }

    /**
     * Draws the player character in the appropriate animation.
     */
    public void drawPlayer(){
        Activity activity = playerController.getPlayer().getActivity();
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
            TextureRegion texture = player.getTextureRepository().getMovingAnimation(direction).getKeyFrame(timeElapsed, false);
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
            draw(texture);
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
     * Checks if the player is not in an animation already.
     * If they're freed up, initiates an animation sequence.
     */
    private void initiateAnimationIfNecessary(){
        if(!(playerInAnimation)){
            playerInAnimation = true;
            timeElapsed = 0;
        }
    }

    /**
     * Ends the player's animation sequence.
     */
    private void endAnimation(){
        playerController.setActivity(Activity.IDLING);
        timeElapsed = 0;
    }
}
