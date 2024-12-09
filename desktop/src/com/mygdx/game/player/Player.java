package com.mygdx.game.player;

import com.mygdx.game.effect.Stats;
import com.mygdx.game.player.states.IdleState;
import com.mygdx.game.player.states.PlayerState;
import com.mygdx.game.game_object.renderer.Renderer;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.inventory.item_visitor.ItemVisitor;
import com.mygdx.game.r_tree.GameListener;
import com.mygdx.game.r_tree.Listener;

import java.util.List;
import java.util.Objects;

public final class Player extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeItemIndex;
    private final Stats stats;
    private final GameListener gameListener;
    private PlayerState state;

    public Player(Transform transform, Renderer renderer, String map,
                  String name,
                  Inventory inventory, int activeItemIndex,
                  Stats stats,
                  GameListener gameListener){
        super(transform, renderer, map);
        this.name = Objects.requireNonNull(name);
        this.inventory = Objects.requireNonNull(inventory);
        if(activeItemIndex < 0 || activeItemIndex >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeItemIndex = activeItemIndex;
        this.stats = Objects.requireNonNull(stats);
        this.gameListener = Objects.requireNonNull(gameListener);
        this.state = new IdleState();
    }

    public String name(){
        return name;
    }

    public void setActiveItemIndex(int index){
        if(index < 0 || index >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeItemIndex = index;
    }

    public void incrementActiveItemIndex(){
        this.activeItemIndex = (activeItemIndex + 1) % inventory.size();
    }

    public void decrementActiveIndex(){
        this.activeItemIndex = (activeItemIndex - 1 + inventory.size()) % inventory.size();
    }

    public Item getActiveItem(){
        return inventory.getItem(activeItemIndex);
    }

    public void useActiveItem(List<GameObject> gameObjects){
        Objects.requireNonNull(gameObjects);
        Item activeItem = inventory.getItem(activeItemIndex);
        activeItem.use(this);
        for(GameObject gameObject : gameObjects){
            Objects.requireNonNull(gameObject);
            activeItem.useOn(gameObject);
        }
    }

    public Stats getStats(){
        return stats;
    }

    public Listener getGameListener(){
        return gameListener;
    }

    public PlayerState getState(){
        return state;
    }

    public void changeState(PlayerState state){
        this.state = Objects.requireNonNull(state);
        super.renderer.setActivity(state.getName());
    }

    public void update(double delta){
        renderer.update(delta);
        inventory.getItem(activeItemIndex).update(delta);
        state.update(delta);
    }

    public void accept(ItemVisitor visitor){
        Objects.requireNonNull(visitor).visit(this);
    }
}
