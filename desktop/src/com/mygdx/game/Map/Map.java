package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    private final TextureRegion textureRegion;

    public GameMap getMap() {
        return map;
    }

    private final GameMap map;

    public Map(TextureRegion textureRegion, GameMap map){
        this.textureRegion = textureRegion;
        this.map = map;
    }
}
