package com.mygdx.game;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.General.Entity;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.*;
import com.mygdx.game.WeekSchedule.ActivityType;

import java.util.Deque;
import java.util.Objects;

public abstract class Character extends Entity {

    private final String name;
    private final IInventoryManager inventoryManager; // todo probably wanna save item textures globally


    public Character(Point position,
                     Dimensions dimensions,
                     String map,
                     AnimationRepository animationRepository,
                     Direction direction,
                     Deque<ActivityType> activityTypes,
                     double delta,
                     String name,
                     IInventoryManager inventoryManager) {
        super(position, dimensions, map, animationRepository, direction, activityTypes, delta);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.inventoryManager = inventoryManager;
    }

    public String getName(){
        return name;
    }
}
