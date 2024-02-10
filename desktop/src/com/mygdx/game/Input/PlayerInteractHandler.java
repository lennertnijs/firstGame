package com.mygdx.game.Input;

import com.mygdx.game.NPC.Activity;
import com.mygdx.game.Player.PlayerService;

public class PlayerInteractHandler {

    private boolean doingAction = false;
    private final PlayerService playerService;

    public PlayerInteractHandler(PlayerService playerService){
        this.playerService = playerService;
    }

    protected void isMining(){
        doingAction = true;
        playerService.setActivity(Activity.MINING);
        System.out.println(playerService.getPlayer().getActivity());
    }

    protected void isNotMining(){
        doingAction = false;
    }
}
