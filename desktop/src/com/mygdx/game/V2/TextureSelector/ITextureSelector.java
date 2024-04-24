package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

public interface ITextureSelector {

    void setActivityType(ActivityType activityType);
    void setDirection(Direction direction);
    TextureRegion getTexture();
}
