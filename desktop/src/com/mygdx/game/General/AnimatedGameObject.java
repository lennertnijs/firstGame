package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Rectangle;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public class AnimatedGameObject extends GameObject{

    private Vector translation;

    public AnimatedGameObject(TextureRegion textureRegion, Point position, Dimensions dimensions,
                              String map, Vector translation){
        super(textureRegion, position, dimensions, map);
        this.translation = Objects.requireNonNull(translation, "Translation is null.");
    }

    @Override
    public Point getPosition(){
        int translatedX = super.getPosition().x() + translation.x();
        int translatedY = super.getPosition().y() + translation.y();
        return new Point(translatedX, translatedY);
    }

    @Override
    public Rectangle getHitBox(){
        return new Rectangle(this.getPosition(), super.getDimensions());
    }

    public void setTranslation(Vector translation){
        this.translation = Objects.requireNonNull(translation, "Translation vector is null.");
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimatedGameObject))
            return false;
        if(!super.equals(other))
            return false;
        AnimatedGameObject object = (AnimatedGameObject) other;
        return translation.equals(object.translation);
    }

    @Override
    public int hashCode(){
        return super.hashCode() * 31 + translation.hashCode();
    }

    @Override
    public String toString(){
        return String.format("AnimatedGameObject[textureRegion=%s, position=%s, dimensions=%s, map=%s, translation=%s]",
                super.getTexture(), super.getPosition(), super.getDimensions(), super.getMap(), translation);
    }
}
