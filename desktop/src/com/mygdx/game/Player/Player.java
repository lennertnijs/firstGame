package com.mygdx.game.Player;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.GameObject.Character;
import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.Map;
import java.util.Objects;

public final class Player extends Character {

    private PlayerState playerState = new IdlePlayerState(this);


    private Player(Builder b){
        super(b.position, b.dimensions, b.map,
                b.animationMap, 0,
                b.direction,
                b.name, b.inventory);
    }

    @Override
    public AnimationKey generateEntityKey() {
        return new EntityKey(playerState.getName(), super.getDirection());
    }

    public String getState(){
        return playerState.getName();
    }

    public void changeState(PlayerState playerState){
        super.resetAnimationDelta();
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

        private Point position;
        private Dimensions dimensions;
        private String map;
        private Map<AnimationKey, Animation> animationMap;
        private Direction direction;
        private String name;
        private Inventory inventory;

        private Builder(){
        }


        public Builder position(Point position){
            this.position = position;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Builder map(String map){
            this.map = map;
            return this;
        }

        public Builder animationMap(Map<AnimationKey, Animation> animationMap){
            this.animationMap = animationMap;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
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
            Objects.requireNonNull(position);
            return new Player(this);
        }
    }
}
