package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.TextureRepository.CharacterTextureRepository;

import static com.mygdx.game.Constants.*;


public class NPCDrawer {

    private final MyGame game;
    private final NPC npc;
    private final CharacterTextureRepository textureRepository;

    private int timeElapsed = 0;

    private boolean npcInAnimation = false;

    public NPCDrawer(MyGame game, NPC npc, CharacterTextureRepository textureRepository){
        this.game = game;
        this.npc = npc;
        this.textureRepository = textureRepository;
    }

    public NPC getNpc(){
        return npc;
    }

    public void drawNPC(){
        if(npc.getActivity() == Activity.IDLING){
            drawNPCIdle();
            return;
        }
        if(npc.getActivity() == Activity.WALKING){
            drawNPCMoving();
        }
    }

    private void drawNPCIdle(){
        Texture texture = textureRepository.getIdleTexture(npc.getDirection());
        draw(texture);
    }

    private void draw(Texture texture){
        game.batch.draw(texture, npc.getPosition().getX(), npc.getPosition().getY(), NPC_WIDTH, NPC_HEIGHT);
    }

    private void drawNPCMoving(){
        initiateAnimationIfNecessary();
        boolean animationInProgress = timeElapsed < ANIMATION_LENGTH;
        if(animationInProgress){
            timeElapsed += Gdx.graphics.getDeltaTime();
            Direction direction = npc.getDirection();
            Texture texture = textureRepository.getMovingAnimation(direction).getKeyFrame(timeElapsed, false);
            draw(texture);
        }
        endAnimation();
        drawNPCIdle();
    }

    /**
     * Checks if the npc is not in an animation already.
     * If they're freed up, initiates an animation sequence.
     */
    private void initiateAnimationIfNecessary(){
        if(!(npcInAnimation)){
            npcInAnimation = true;
            timeElapsed = 0;
        }
    }

    /**
     * Ends the npc's animation sequence.
     */
    private void endAnimation(){
        npc.setActivity(Activity.IDLING);
        timeElapsed = 0;
    }
}
