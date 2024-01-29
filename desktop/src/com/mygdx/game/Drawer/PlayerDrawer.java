package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.Constants;
import com.mygdx.game.Item.Tool;
import com.mygdx.game.Item.ToolInstance;
import com.mygdx.game.MovementDirection;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.Player;

public class PlayerDrawer {

    MyGame game;

    PlayerTexturesRepo playerTexturesRepo;
    private float elapsed;
    Animation<Texture> animation;

    public PlayerDrawer(MyGame game, PlayerTexturesRepo playerTexturesRepo){
        this.game = game;
        this.playerTexturesRepo = playerTexturesRepo;
    }

    public void drawPlayer(Player player){
        if(player.getActivity() == Activity.IDLING){
            game.batch.draw(playerTexturesRepo.getIdleTexture(player.getMovementDirection()),
                    player.getPosition().getX(), player.getPosition().getY(),
                    Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }
        if(player.getActivity() == Activity.WALKING){
            drawPlayerMovement(player);
        }
    }
    public void drawPlayerMovement(Player player){
        if(!player.getDoingAnimation()){
            player.setDoingAnimation(true);
            elapsed = 0;
            animation = playerTexturesRepo.getMovementAnimation(player.getMovementDirection());
            game.batch.draw(playerTexturesRepo.getIdleTexture(player.getMovementDirection()),
                    player.getPosition().getX(), player.getPosition().getY(),
                    Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }
        System.out.println(elapsed);
        if(elapsed < 1){
            elapsed += Gdx.graphics.getDeltaTime();
            Texture texture = animation.getKeyFrame(elapsed, false);
            game.batch.draw(texture,player.getPosition().getX(), player.getPosition().getY(),
                    Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }
        if(elapsed >= 1){
            player.setActivity(Activity.IDLING);
            elapsed = 0;
            player.setDoingAnimation(false);
            animation = null;
            game.batch.draw(playerTexturesRepo.getIdleTexture(player.getMovementDirection()),
                    player.getPosition().getX(), player.getPosition().getY(),
                    Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }

    }
}
