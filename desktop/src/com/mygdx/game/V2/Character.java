package com.mygdx.game.V2;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;
import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

import java.util.Objects;

public abstract class Character {

    private final String name;
    private final ITextureSelector textureSelector;

    public Character(String name, ITextureSelector textureSelector){
        this.name = name;
        this.textureSelector = textureSelector;
    }

    public String getName(){
        return name;
    }

    public Texture getTexture(){
        return textureSelector.getTexture();
    }

    public void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Direction is null.");
        textureSelector.setDirection(direction);
    }

    public void setActivity(ActivityType activityType){
        Objects.requireNonNull(activityType, "Activity type is null.");
        textureSelector.setActivityType(activityType);
    }
}
