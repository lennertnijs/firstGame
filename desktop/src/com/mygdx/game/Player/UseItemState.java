package com.mygdx.game.Player;

public final class UseItemState implements PlayerState {

    private final Player player;
    private double delta;

    public UseItemState(Player player){
        this.player = player;
        this.delta = 0;
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
        handleStateChange();
    }

    private void handleStateChange(){
        if(delta >= player.getActiveItem().usageDuration()){
            player.changeState(new IdleState());
        }
    }
}
