package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Util.GameMap;

public class SpriteDrawer {

    private final MyGame game;
    private String text;
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
        drawText();
    }

    public void draw(GameMap gameMap){
        Texture texture = gameMap.texture();
        int width = texture.getWidth();
        int height = texture.getHeight();
        game.batch.draw(texture, 0, 0, width, height);
        drawText();
    }

    private void drawText(){
        if(text != null){
            game.font.draw(game.batch, text, 50, 50);
        }
    }

    public void draw(String text){
        this.text = text;
    }
}
