package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;

import static com.mygdx.game.Constants.PLAYER_HEIGHT;
import static com.mygdx.game.Constants.PLAYER_WIDTH;

public class PlayerDrawer {

    private final MyGame game;
    private final Player player;
    private final PlayerTextureRepository playerTextureRepository;
    private float elapsed = 0;
    private boolean playerInAnimation = false;

    public PlayerDrawer(MyGame game, Player player, PlayerTextureRepository playerTextureRepository){
        this.game = game;
        this.player = player;
        this.playerTextureRepository = playerTextureRepository;
    }

    public void drawPlayer(){
        if(player.getActivity() == Activity.IDLING){
            drawPlayerIdle();
        }
        if(player.getActivity() == Activity.WALKING){
            drawPlayerMoving();
        }
    }

    private void drawPlayerIdle(){
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        Direction direction = player.getMovementDirection();
        Texture texture = playerTextureRepository.getIdleTexture(direction);
        game.batch.draw(texture, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }


    private void drawPlayerMoving(){
        if(!(playerInAnimation)){
            playerInAnimation = true;
            elapsed = 0;
        }
        if(elapsed < 1){
            elapsed += Gdx.graphics.getDeltaTime();
            Texture texture = playerTextureRepository.getMovementAnimation(player.getMovementDirection()).getKeyFrame(elapsed, false);
            game.batch.draw(texture,player.getPosition().getX(), player.getPosition().getY(),
                    PLAYER_WIDTH, PLAYER_HEIGHT);
        }
        if(elapsed >= 1){
            player.setActivity(Activity.IDLING);
            elapsed = 0;
            player.setDoingAnimation(false);
            game.batch.draw(playerTextureRepository.getIdleTexture(player.getMovementDirection()),
                    player.getPosition().getX(), player.getPosition().getY(),
                    PLAYER_WIDTH, PLAYER_HEIGHT);
        }

    }
}
