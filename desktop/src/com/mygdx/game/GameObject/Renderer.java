package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Direction;

public interface Renderer {

    TextureRegion texture();
    Vec2 getOffSet();
    int getWidth();
    int getHeight();
    void setDirection(Direction direction);
    void setActivity(String activity);
    void update(double delta);
}
