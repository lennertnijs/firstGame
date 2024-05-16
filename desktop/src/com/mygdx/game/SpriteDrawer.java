package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.General.GameObject;

public class SpriteDrawer {

    private final MyGame game;
    public SpriteDrawer(MyGame game){
        this.game = game;
    }

    public void draw(GameObject o){
        TextureRegion texture = o.getTexture();
        int x = o.getPosition().x();
        int y = o.getPosition().y();
        int width = o.getDimensions().width();
        int height = o.getDimensions().height();
        game.batch.draw(texture, x, y, width, height);
    }
}
