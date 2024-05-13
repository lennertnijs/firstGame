package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;

public interface ISprite {

    TextureRegion getTexture();
    void setTexture(TextureRegion t);
    Point getPosition();
    void setPosition(Point p);
    Dimensions getDimensions();
    void setDimensions(Dimensions dimensions);
    Point getAnchor();
    Location getLocation();
    void setLocation(Location location);
}
