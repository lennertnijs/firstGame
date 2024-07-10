package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Animation.Frame;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.Bat.BatKey;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class BatLoader {

    public static Bat create(){
        Point position = new Point(1250, 750);
        Dimensions dimensions = new Dimensions(64, 64);
        Stats stats = Stats.builder().offense(50).defense(50).health(120).speed(50).build();

        Map<AnimationKey, Animation> animationMap = new HashMap<>();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("bat/batpack.atlas"));
        Frame frame1 = Frame.builder().textureRegion(atlas.findRegion("idle_down", 1)).dimensions(dimensions).build();
        Frame frame2 = Frame.builder().textureRegion(atlas.findRegion("idle_down", 2)).dimensions(dimensions).build();
        Frame frame3 = Frame.builder().textureRegion(atlas.findRegion("idle_down", 3)).dimensions(dimensions).build();
        Frame frame4 = Frame.builder().textureRegion(atlas.findRegion("idle_down", 2)).dimensions(dimensions).build();
        Animation animation = new Animation(Arrays.asList(frame1, frame2, frame3, frame4), 4);
        animationMap.put(new BatKey("idle"), animation);
        animationMap.put(new BatKey("move"), animation);
        animationMap.put(new BatKey("attack"), animation);
        animationMap.put(new BatKey("reposition"), animation);
        return Bat.builder().position(position).dimensions(dimensions).map("main").direction(Direction.RIGHT).delta(0)
                .stats(stats).animationMap(animationMap).build();
    }
}
