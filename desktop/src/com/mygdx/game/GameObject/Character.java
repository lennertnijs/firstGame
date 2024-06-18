package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Util.*;

import java.util.Map;
import java.util.Objects;

public abstract class Character extends Entity {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;

    public Character(Point position,
                     Dimensions dimensions,
                     String map,
                     Map<AnimationKey, Animation> animationMap,
                     double delta,
                     Direction direction,
                     String name,
                     Inventory inventory) {
        super(position, dimensions, map, animationMap, delta, direction);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.inventory = inventory;
    }

    public String getName(){
        return name;
    }

    public void useActiveItem(GameObject object){
//        if(super.getAnimationDelta() < 1000f){
//            return; SHOULD ONLY GO ONCE!
//        }
        inventory.use(activeIndex, object);
    }

    public void incrementActiveIndex(){
        this.activeIndex = (this.activeIndex + 1) % inventory.getItems().length;
    }
}
