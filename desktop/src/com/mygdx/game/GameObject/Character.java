package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Util.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mygdx.game.Keys.NPCActivityType.IDLING;
import static com.mygdx.game.Keys.NPCActivityType.MINING;

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
                     List<ActivityType> activityTypeList,
                     String name,
                     Inventory inventory) {
        super(position, dimensions, map, animationMap, delta, direction, activityTypeList);
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
        if(inventory.getActiveItem(activeIndex) instanceof Tool){
            Tool tool = (Tool)inventory.getActiveItem(activeIndex);
            if(tool.type() == ToolType.PICKAXE)
                super.storeActivityType(MINING);
            if(tool.type() == ToolType.AXE)
                super.storeActivityType(IDLING);
        }
        inventory.use(activeIndex, object);
    }

    public void incrementActiveIndex(){
        this.activeIndex = (this.activeIndex + 1) % inventory.getItems().length;
    }
}
