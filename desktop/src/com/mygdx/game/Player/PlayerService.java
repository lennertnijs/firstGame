package com.mygdx.game.Player;

import com.mygdx.game.Direction;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.Activity;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(){
        playerRepository = new PlayerRepository();
    }

    public Player getPlayer(){
        return playerRepository.getPlayer();
    }

    public void setItemIndex(int index){
        playerRepository.getPlayer().setCurrentItemIndex(index);
    }

    public void setActivity(Activity activity){
        playerRepository.getPlayer().setActivity(activity);
    }

    public void setDirection(Direction direction){
        playerRepository.getPlayer().setDirection(direction);
    }

    public void setPosition(Position position){
        playerRepository.getPlayer().setPosition(position);
    }

    public void setDoingAnimation(boolean bool){
        playerRepository.getPlayer().setDoingAnimation(bool);
    }
}
