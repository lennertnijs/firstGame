package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public class StaticGameObject extends GameObject{

    private final TextureRegion textureRegion;

    public StaticGameObject(Point position,
                            Dimensions dimensions,
                            String map,
                            TextureRegion textureRegion){
        super(position, dimensions, map);
        this.textureRegion = Objects.requireNonNull(textureRegion);
    }

    @Override
    public TextureRegion getTexture() {
        return textureRegion;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof StaticGameObject))
            return false;
        if(!super.equals(other))
            return false;
        StaticGameObject object = (StaticGameObject) other;
        return textureRegion.equals(object.textureRegion);
    }

    @Override
    public int hashCode(){
        return super.hashCode() * 31 + textureRegion.hashCode();
    }

    @Override
    public String toString(){
        return String.format("StaticGameObject[texture=%s, %s]",
                textureRegion, super.toString());
    }
}
