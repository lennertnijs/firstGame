package game.game_object;

import game.game_object.renderer.Renderer;
import game.util.Vec2;

import java.util.Objects;

public class GameObject {

    protected final Transform transform;
    protected final Renderer renderer;
    protected String map;
    public final GameObjectType type;

    public GameObject(Transform transform, Renderer renderer, String map, GameObjectType type){
        this.transform = Objects.requireNonNull(transform);
        this.renderer = Objects.requireNonNull(renderer);
        this.map = Objects.requireNonNull(map);
        this.type = type;
    }

    public final Vec2 getPosition(){
        return transform.getPosition().add(renderer.getOffSet());
    }

    public final void setPosition(Vec2 position){
        transform.setPosition(position);
    }

    public final Renderer getRenderer(){
        return renderer;
    }

    public final String getMap(){
        return map;
    }

    public final void setMap(String map){
        this.map = Objects.requireNonNull(map);
    }

    public void damage(int damage){

    }
    @Override
    public String toString(){
        return String.format("GameObject[transform=%s, renderer=%s, map=%s]", transform, renderer, map);
    }
}
