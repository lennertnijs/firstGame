package com.mygdx.game.Service;


import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerRepository;

public class PlayerService {

    PlayerRepository playerRepository;
    public PlayerService(){
        this.playerRepository = new PlayerRepository();
    }

    public Player getPlayer(){
        return playerRepository.getPlayer();
    }
}
