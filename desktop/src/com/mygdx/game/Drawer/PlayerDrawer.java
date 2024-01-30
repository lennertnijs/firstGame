package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;
import com.mygdx.game.TextureRepository.PlayerTextureRepository;

import static com.mygdx.game.Constants.*;

public class PlayerDrawer {

    private final MyGame game;
    private final Player player;
    private final PlayerTextureRepository playerTextureRepository;
    private float timeElapsed = 0;
    private boolean playerInAnimation = false;

    public PlayerDrawer(MyGame game, Player player, PlayerTextureRepository playerTextureRepository){
        this.game = game;
        this.player = player;
        this.playerTextureRepository = playerTextureRepository;
    }

    /**
     * Draws the player character in the appropriate animation.
     */
    public void drawPlayer(){
        if(player.getActivity() == Activity.IDLING){
            drawPlayerIdle();
            return;
        }
        if(player.getActivity() == Activity.WALKING){
            drawPlayerMoving();
        }
    }

    /**
     * Handles the drawing of the player if they're idle.
     */
    private void drawPlayerIdle(){
        Texture texture = playerTextureRepository.getIdleTexture(player.getDirection());
        draw(texture);
    }


    /**
     * Handles the drawing of the player if they're mining.
     */
    private void drawPlayerMoving(){
        initiateAnimationIfNecessary();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = player.getDirection();
            Texture texture = playerTextureRepository.getMovingAnimation(direction).getKeyFrame(timeElapsed, false);
            draw(texture);
            return;
        }
        endAnimation();
        drawPlayerIdle();
    }

    /**
     * Draws the texture at the player location. Helper function.
     */
    private void draw(Texture texture){
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
        player.setActivity(Activity.IDLING);
        timeElapsed = 0;
    }
}
