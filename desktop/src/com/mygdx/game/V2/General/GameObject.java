package com.mygdx.game.V2.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.Location;

public class GameObject {

    private Sprite sprite;

    public GameObject(Sprite sprite){
        this.sprite = sprite;
    }

    public Location getCurrentLocation(){
        return sprite.getLocation();
    }

    public void setTexture(TextureRegion texture){
        sprite.setTexture(texture);
    }

    public void setLocation(Location location){
        sprite.setLocation(location);
    }
}
