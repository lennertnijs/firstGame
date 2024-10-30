package com.mygdx.game.Player;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Inventory.Character;
import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.Transform;
import com.mygdx.game.Util.Direction;

public final class Player extends Character {

    private PlayerState playerState = new IdlePlayerState(this);


    private Player(Builder b){
        super(b.transform, b.renderer, b.map, b.name, b.inventory);
    }

    public String getState(){
        return playerState.getName();
    }

    public void changeState(PlayerState playerState){
        super.setActivity(playerState.getName());
        this.playerState = playerState;
    }

    public int getSpeed(){
        return 50;
    }

    public void update(double delta, Direction direction, HitBoxSnapShot snapShot){
        playerState.progress(delta, direction, snapShot);
    }


    public void useActiveItem(Breakable breakable){
        Item item = super.getInventory().getItem(super.getActiveIndex());
        if(!(item instanceof Tool)){
            return;
        }
        Tool tool = (Tool) item;
        breakable.damage(tool.efficiency());
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Transform transform;
        private Renderer renderer;
        private String map;
        private String name;
        private Inventory inventory;

        private Builder(){
        }

        public Builder transform(Transform transform){
            this.transform = transform;
            return this;
        }

        public Builder renderer(Renderer renderer){
            this.renderer = renderer;
            return this;
        }

        public Builder map(String map){
            this.map = map;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder inventory(Inventory inventory){
            this.inventory = inventory;
            return this;
        }

        public Player build(){
            return new Player(this);
        }
    }
}
