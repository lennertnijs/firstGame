package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.GameObject.Entity;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Util.*;

import java.util.List;
import java.util.Objects;

public abstract class Character extends Entity {

    private final String name;
    private final IInventoryManager inventoryManager; // todo probably wanna save item textures globally


    public Character(Point position,
                     Dimensions dimensions,
                     String map,
                     AnimationRepository animationRepository,
                     double delta,
                     Direction direction,
                     List<ActivityType> activityTypeList,
                     String name,
                     IInventoryManager inventoryManager) {
        super(position, dimensions, map, animationRepository, delta, direction, activityTypeList);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.inventoryManager = inventoryManager;
    }

    public String getName(){
        return name;
    }
}
