package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.*;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.Renderer.Animator;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Renderer.StaticTexture;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

public final class BatLoader {

    public static Bat create(){
        Point position = new Point(1250, 750);
        Dimensions dimensions = new Dimensions(64, 64);
        Stats stats = Stats.builder().offense(50).defense(50).health(120).speed(50).build();
        TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("bat/batpack.png")));
        Renderer renderer = new StaticTexture(textureRegion);

        return Bat.builder().renderer(renderer).position(position).map("main").stats(stats).build();
    }

    public static AnimationHolder load(String atlasPath){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));

        AnimationFactory animationFactory = new AnimationFactory(atlas);
        Animation idlePack = animationFactory.createWithDirection("idle", "down", 3);
        AnimationPack animationPack = new FourDirectionalAnimationPack(idlePack, idlePack, idlePack, idlePack);

        AnimationHolder animationHolder = new AnimationHolder();
        animationHolder.addAnimation("idle", animationPack);
        animationHolder.addAnimation("mine", animationPack);
        animationHolder.addAnimation("walking", animationPack);
        return animationHolder;
    }
}
