package com.mygdx.game.Player;


import com.mygdx.game.DAO.PlayerDAO;


public class PlayerRepository {

    private final Player player;

    public PlayerRepository(){
        this.player = new PlayerDAO().readPlayer();
    }

    public Player getPlayer(){
        return player;
    }
}
