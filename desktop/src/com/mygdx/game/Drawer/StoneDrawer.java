package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Stone.Stone;
import com.mygdx.game.MyGame;

import static com.mygdx.game.Constants.STONE_HEIGHT;
import static com.mygdx.game.Constants.STONE_WIDTH;

public class StoneDrawer {

    Texture texture = new Texture(Gdx.files.internal("npc/stone.png"));
    private final MyGame game;
    public StoneDrawer(MyGame game){
        this.game = game;
    }

    public void drawStone(Stone stone){
        game.batch.draw(texture, stone.getPosition().getX(), stone.getPosition().getY(), STONE_WIDTH, STONE_HEIGHT);
    }
}
