package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Map.Map;

public class SpriteDrawer {

    private final MyGame game;
    public SpriteDrawer(MyGame game){
        this.game = game;
    }

    public void draw(GameObject o){
        TextureRegion texture = o.getTexture();
        int x = o.getPosition().getX();
        int y = o.getPosition().getY();
        int width = o.getDimensions().width();
        int height = o.getDimensions().height();
        game.batch.draw(texture, x, y, width, height);
    }

    public void draw(Map map){
        TextureRegion texture = map.getTextureRegion();
        int width = texture.getRegionWidth();
        int height = texture.getRegionHeight();
        game.batch.draw(texture, 0, 0, width, height);
    }
}
