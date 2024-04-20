package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;

public interface ITextureSelector {

    void setActivityType(ActivityType activityType);
    void setDirection(Direction direction);
    Texture getTexture();
}
