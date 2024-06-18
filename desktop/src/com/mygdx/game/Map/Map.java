package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {

    private final TextureRegion textureRegion;

    private final String map;

    public Map(TextureRegion textureRegion, String map){
        this.textureRegion = textureRegion;
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
