package com.mygdx.game.V2;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.TextureSelector.ITextureSelector;

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

    public TextureRegion getTexture(){
        return textureSelector.getTexture();
    }
}
