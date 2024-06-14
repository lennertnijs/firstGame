package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.mygdx.game.Keys.NPCActivityType.WALKING;

public final class Player extends Character{

    // add player stats
    private Player(Builder b){
        super(b.position, b.dimensions, b.map,
                b.animationMap, 0,
                b.direction, Collections.singletonList(b.activityType), b.name, b.inventory);
    }


    public void move(double delta, Direction direction, HitBoxSnapShot snapShot){
        if(super.getCurrentActivityType() != WALKING){
            super.storeActivityType(WALKING);
        }
        int m = (int) (delta * 0.4f);
        Point p = super.getPosition();
        Point next = null;
        switch(direction){
            case UP: next = new Point(p.x(), p.y() + m); break;
            case RIGHT: next = new Point(p.x() + m, p.y()); break;
            case DOWN: next = new Point(p.x(), p.y() - m); break;
            case LEFT: next = new Point(p.x() - m, p.y()); break;
        }
        // todo next not working on npc
        if(snapShot.isFree(next)){
            super.setPosition(next);
        }
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
        private ActivityType activityType;
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

        public Builder activityType(ActivityType activityType){
            this.activityType = activityType;
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
