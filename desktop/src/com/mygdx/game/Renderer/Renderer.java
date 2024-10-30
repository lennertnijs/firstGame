package com.mygdx.game.Renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Vector;

public interface Renderer {

    TextureRegion texture();
    Vector getOffSet();
    Dimensions getDimensions();
    void setDirection(Direction direction);
    void setActivity(String activity);
    void update(double delta);
}
