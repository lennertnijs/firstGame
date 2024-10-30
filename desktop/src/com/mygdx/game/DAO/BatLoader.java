package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.StaticTexture;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

public final class BatLoader {

    public static Bat create(){
        Point position = new Point(1250, 750);
        Dimensions dimensions = new Dimensions(64, 64);
        Stats stats = Stats.builder().offense(50).defense(50).health(120).speed(50).build();
        TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("bat/batpack.png")));
        Renderer renderer = new StaticTexture(textureRegion);

        return Bat.builder().renderer(renderer).map("main").stats(stats).build();
    }
}
