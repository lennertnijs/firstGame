package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Animation.*;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

public final class BatLoader {

    public static Bat create(){
        Point position = new Point(1250, 750);
        Dimensions dimensions = new Dimensions(64, 64);
        Stats stats = Stats.builder().offense(50).defense(50).health(120).speed(50).build();

        return Bat.builder().position(position).dimensions(dimensions).map("main").direction(Direction.RIGHT).delta(0)
                .stats(stats).animationHolder(load("bat/batpack.atlas")).build();
    }

    public static AnimationHolder load(String atlasPath){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));

        AnimationFactory animationFactory = new AnimationFactory(atlas);
        AnimationPack idlePack = animationFactory.create4Directional("idle", 4);

        AnimationHolder animationHolder = new AnimationHolder();
        animationHolder.addAnimation("idle", idlePack);
        animationHolder.addAnimation("mine", idlePack);
        animationHolder.addAnimation("walking", idlePack);
        return animationHolder;
    }
}
