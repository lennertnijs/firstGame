package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.NPC;

import static com.mygdx.game.Constants.*;


public class NPCDrawer {

    private final MyGame game;


    public NPCDrawer(MyGame game){
        this.game = game;
    }


    public void drawNPC(NPC npc){
        if(npc.getActivity() == Activity.IDLING){
            drawNPCIdle(npc);
            return;
        }
        if(npc.getActivity() == Activity.WALKING){
            drawNPCMoving(npc);
        }
    }

    private void drawNPCIdle(NPC npc){
        TextureRegion texture = npc.getTextureRepository().getIdleTexture(npc.getDirection());
        draw(texture, npc);
    }

    private void draw(TextureRegion texture, NPC npc){
        game.batch.draw(texture, npc.getPosition().getX(), npc.getPosition().getY(), NPC_WIDTH, NPC_HEIGHT);
    }

    private void drawNPCMoving(NPC npc){
        initiateAnimationIfNecessary(npc);
        float timeElapsed = npc.getTextureRepository().getTimeElapsed();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = npc.getDirection();
            TextureRegion texture = npc.getTextureRepository().getMovingAnimation(direction).getKeyFrame(timeElapsed, true);
            draw(texture, npc);
            npc.getTextureRepository().setTimeElapsed(timeElapsed);
            return;
        }
        endAnimation(npc);
        drawNPCIdle(npc);
    }

    /**
     * Checks if the npc is not in an animation already.
     * If they're freed up, initiates an animation sequence.
     */
    private void initiateAnimationIfNecessary(NPC npc){
        if(!(npc.getTextureRepository().getInAnimation())){
             npc.getTextureRepository().setInAnimation(true);
            npc.getTextureRepository().setTimeElapsed(0);
        }
    }

    /**
     * Ends the npc's animation sequence.
     */
    private void endAnimation(NPC npc){
        npc.getTextureRepository().setTimeElapsed(0);
    }
}
