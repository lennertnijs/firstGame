package com.mygdx.game.Repository;

import com.mygdx.game.Player.Player;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

public class PlayerRepository {

    private final Player player;

    public PlayerRepository(Player player){
        Objects.requireNonNull(player, "The player must not be null");
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}
