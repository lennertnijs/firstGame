package game.game_object.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.util.Vec2;

import java.util.Map;
import java.util.Objects;

public final class AnimatedRenderer implements Renderer {

    private final Map<Key, Animation> animations;
    private final Key key;
    private double delta;

    public AnimatedRenderer(Map<Key, Animation> animations, Key key, double delta){
        Objects.requireNonNull(animations);
        for(Key k : animations.keySet()){
            Objects.requireNonNull(k);
        }
        for(Animation animation : animations.values()){
            Objects.requireNonNull(animation);
        }
        this.animations = animations;
        this.key = Objects.requireNonNull(key);
        if(delta < 0){
            throw new IllegalArgumentException("Delta cannot be negative.");
        }
        this.delta = delta;
        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Key is not mapped.");
        }
    }

    @Override
    public TextureRegion getTexture() {
        return animations.get(key).getFrame(delta).texture();
    }

    @Override
    public Vec2 getOffSet() {
        return animations.get(key).getFrame(delta).offset();
    }

    @Override
    public int getWidth() {
        return animations.get(key).getFrame(delta).scaledWidth();
    }

    @Override
    public int getHeight(){
        return animations.get(key).getFrame(delta).scaledHeight();
    }

    @Override
    public Direction getDirection() {
        return key.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        key.setDirection(direction);
        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Updated key is not mapped.");
        }
        this.delta = 0;
    }

    @Override
    public String getActivity() {
        return key.getActivity();
    }

    @Override
    public void setActivity(String activity) {
        key.setActivity(activity);

        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Updated key is not mapped.");
        }
        this.delta = 0;
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
    }
}
