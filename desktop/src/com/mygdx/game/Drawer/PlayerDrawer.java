package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.Constants.*;

public class PlayerDrawer {

    private final MyGame game;
    private float timeElapsed = 0;
    private boolean playerInAnimation = false;

    public PlayerDrawer(MyGame game){
        this.game = game;
    }

    /**
     * Draws the player character in the appropriate animation.
     */
    public void drawPlayer(Player player){
        if(player.getActivity() == Activity.IDLING){
            drawPlayerIdle(player);
            return;
        }
        if(player.getActivity() == Activity.WALKING){
            drawPlayerMoving(player);
        }
    }

    /**
     * Handles the drawing of the player if they're idle.
     */
    private void drawPlayerIdle(Player player){
        Texture texture = player.getTextureRepository().getIdleTexture(player.getDirection());
        draw(texture, player);
    }


    /**
     * Handles the drawing of the player if they're mining.
     */
    private void drawPlayerMoving(Player player){
        initiateAnimationIfNecessary();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = player.getDirection();
            Texture texture = player.getTextureRepository().getMovingAnimation(direction).getKeyFrame(timeElapsed, false);
            draw(texture, player);
            return;
        }
        endAnimation(player);
        drawPlayerIdle(player);
    }

    /**
     * Draws the texture at the player location. Helper function.
     */
    private void draw(Texture texture, Player player){
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
    private void endAnimation(Player player){
        player.setActivity(Activity.IDLING);
        timeElapsed = 0;
    }
}
