package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.updatedGameObject.Renderer;
import com.mygdx.game.updatedGameObject.StaticRenderer;
import com.mygdx.game.Stats;
import com.mygdx.game.updatedGameObject.Frame;

public final class BatLoader {

    public static Bat create(){
        Stats stats = Stats.builder().offense(50).defense(50).health(120).speed(50).build();
        TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("bat/batpack.png")));
        Frame frame = Frame.builder().texture(textureRegion).build();
        Renderer renderer = new StaticRenderer(frame);

        return Bat.builder().renderer(renderer).map("main").stats(stats).build();
    }
}
