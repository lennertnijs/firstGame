package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.game_object.GameObject;

public class SpriteDrawer {

    private final MyGame game;
    private String text;
    private Texture texture = new Texture(Gdx.files.internal("maps/map.png"));
    public SpriteDrawer(MyGame game){
        this.game = game;
    }

    public void draw(GameObject o){
        TextureRegion texture = o.getTexture();
        int x = o.getPosition().x();
        int y = o.getPosition().y();
        int width = o.getWidth();
        int height = o.getHeight();
        game.batch.draw(texture, x, y, width, height);
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
