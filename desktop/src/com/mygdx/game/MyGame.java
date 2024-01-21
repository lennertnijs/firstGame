package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// The class that is the actual game.
// Contains components that are globally used for the game, like the sprite batch and a bitmap font.
public class MyGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        setScreen(new MainScreenMenu(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
