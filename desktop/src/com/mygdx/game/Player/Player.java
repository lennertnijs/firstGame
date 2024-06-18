package com.mygdx.game.Player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.GameObject.Character;
import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Inventory.Inventory;
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
    public TextureRegion getTexture(){
        EntityKey key = new EntityKey(playerState.getState(), super.getDirection());
        return super.getTexture(key);
    }

    @Override
    public Point getPosition() {
        EntityKey key = new EntityKey(playerState.getState(), super.getDirection());
        return super.getPosition(key);
    }

    public void changeState(PlayerState playerState){
        this.playerState = playerState;
    }

    public void update(double delta, Direction direction, HitBoxSnapShot snapShot){
        playerState.progress(delta, direction, snapShot);
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
