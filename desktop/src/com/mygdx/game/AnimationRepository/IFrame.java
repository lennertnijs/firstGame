package com.mygdx.game.AnimationRepository;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Vector;

public interface IFrame {

    TextureRegion textureRegion();
    Vector translation();
    Dimensions dimensions();
}
