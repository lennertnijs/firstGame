package com.mygdx.game.Controller;

import com.mygdx.game.Player.Player;
import com.mygdx.game.Service.PlayerService;

public class PlayerController {


    private final PlayerService playerService;
    public PlayerController(){
        this.playerService = new PlayerService();
    }

    public Player getPlayer(){
        return playerService.getPlayer();
    }
}
