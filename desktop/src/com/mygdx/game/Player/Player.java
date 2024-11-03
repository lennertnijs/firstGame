package com.mygdx.game.Player;

import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.renderer.Renderer;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.inventory.ItemVisitor;

import java.util.List;
import java.util.Objects;

public final class Player extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;
    private final Stats stats;
    private PlayerState playerState = new IdleState();

    public Player(Transform transform, Renderer renderer, String map, String name, Inventory inventory, int activeIndex, Stats stats){
        super(transform, renderer, map);
        this.name = Objects.requireNonNull(name);
        this.inventory = Objects.requireNonNull(inventory);
        if(activeIndex < 0 || activeIndex >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeIndex = activeIndex;
        this.stats = Objects.requireNonNull(stats);
    }

    public String name(){
        return name;
    }

    public Inventory inventory(){
        return inventory;
    }

    public int activeIndex(){
        return activeIndex;
    }

    public void incrementActiveIndex(){
        this.activeIndex = (activeIndex + 1) % inventory.size();
    }

    public void decrementActiveIndex(){
        this.activeIndex = (activeIndex - 1) % inventory.size();
    }

    public Item getActiveItem(){
        return inventory.getItem(activeIndex);
    }

    public void useActiveItem(List<GameObject> gameObjects){
        gameObjects.add(this);
        for(GameObject gameObject : gameObjects){
            inventory.getItem(activeIndex).use(gameObject);
        }
    }

    public void changeState(PlayerState state){
        Objects.requireNonNull(state);
        this.playerState = state;
    }

    public Stats getStats(){
        return stats;
    }

    public void update(double delta){
        renderer.update(delta);
        inventory.getItem(activeIndex).update(delta);
        playerState.update(delta);
    }

    public void accept(ItemVisitor visitor){
        Objects.requireNonNull(visitor);
        visitor.visit(this);
    }
}
