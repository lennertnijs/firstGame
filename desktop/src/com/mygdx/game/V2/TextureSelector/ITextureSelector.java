package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

public interface ITextureSelector {

    void setActivityType(ActivityType activityType);
    void setDirection(Direction direction);
    Texture getTexture();
}
