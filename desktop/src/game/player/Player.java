package game.player;

import game.inventory.Inventory;
import game.game_object.GameObject;
import game.game_object.Transform;
import game.game_object.renderer.Renderer;
import game.game_object.GameObjectType;

import game.stats.Stats;
import game.player.states.IdleState;
import game.player.states.PlayerState;

import java.util.Objects;

public final class Player extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeItemIndex;
    private final Stats stats;
    private PlayerState state;

    public Player(Transform transform, Renderer renderer, String map,
                  String name, Inventory inventory, int activeItemIndex, Stats stats){
        super(transform, renderer, map, GameObjectType.PLAYER);
        this.name = Objects.requireNonNull(name);
        this.inventory = Objects.requireNonNull(inventory);
        if(activeItemIndex < 0 || activeItemIndex >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeItemIndex = activeItemIndex;
        this.stats = Objects.requireNonNull(stats);
        this.state = new IdleState(this);
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public int getActiveItemIndex(){
        return activeItemIndex;
    }

    public void setActiveItemIndex(int index){
        if(index < 0 || index >= inventory.size()){
            throw new IndexOutOfBoundsException("Active index cannot be outside inventory bounds.");
        }
        this.activeItemIndex = index;
    }

    public Stats getStats(){
        return stats;
    }

    public PlayerState getState(){
        return state;
    }

    public void changeState(PlayerState state){
        this.state = Objects.requireNonNull(state);
        super.renderer.setActivity(state.getName());
    }

    public void update(double delta){
        state.update(delta);
        super.renderer.update(delta);
    }
}
