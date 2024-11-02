package com.mygdx.game.Player;

import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.inventory.Tool;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.renderer.Renderer;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.inventory.ItemVisitor;

public final class Player extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;
    private PlayerState playerState = new IdlePlayerState(this);


    private Player(Builder b){
        super(b.transform, b.renderer, b.map);
        this.name = b.name;
        this.inventory = b.inventory;
        this.activeIndex = 0;
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

    public void update(double delta, Direction direction){
        playerState.progress(delta, direction);
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Item getActiveItem(){
        return inventory.getItem(activeIndex);
    }

    public boolean hasToolInActive(){
        return inventory.getItem(activeIndex) instanceof Tool;
    }

    public int getActiveIndex(){
        return activeIndex;
    }

    public void incrementActiveIndex(){
        this.activeIndex = (activeIndex + 1) % inventory.size();
    }

    public void accept(ItemVisitor visitor){
        visitor.visit(this);
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
