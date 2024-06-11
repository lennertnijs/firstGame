package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map {

    private final TextureRegion textureRegion;

    private final GameMap map;

    public Map(TextureRegion textureRegion, GameMap map){
        this.textureRegion = textureRegion;
        this.map = map;
    }

    public GameMap getMap() {
        return map;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
