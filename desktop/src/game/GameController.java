package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import game.clock.Clock;
import game.game_object.GameObject;
import game.inventory.Item;
import game.game_object.GameObjectType;
import game.game_object.renderer.Direction;
import game.npc.NPC;
import game.player.Player;
import game.player.states.IdleState;
import game.player.states.WalkState;
import game.stats.Stat;

import java.util.*;

public class GameController {

    private final Player player;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final Texture map = new Texture(Gdx.files.internal("background.png"));
    private final Deque<Direction> directions = new LinkedList<>();
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final Map<DamageAmpKey, Float> damageAmps = new HashMap<>();
    private final List<NPC> npcs = new ArrayList<>();

    public GameController(Player player, Clock clock, SpriteDrawer spriteDrawer){
        this.player = player;
        this.clock = clock;
        this.drawer = spriteDrawer;
    }

    public void update(){
        drawer.draw(map);
        double delta = clock.update();
        player.update(delta);
        drawer.draw(player);
    }

    private List<GameObject> getCollidingGameObjects(){
        return gameObjects;
    }

    public void playerUseActiveItem(){
        Item activeItem = player.getInventory().getItem(player.getActiveItemIndex());
        switch(activeItem.type){
            case SWORD, PICKAXE, AXE, BOW: {
                int durability = activeItem.maxDurability;
                if(durability <= 0){
                    return;
                }
                List<GameObject> objects = getCollidingGameObjects();
                for(GameObject object : objects) {
                    if (object.type == GameObjectType.BORING) {
                        continue;
                    }
                    int damage = activeItem.damage;
                    Float amplifier = damageAmps.get(new DamageAmpKey(GameObjectType.PLAYER, activeItem.type));
                    if(amplifier == null){
                        continue;
                    }
                    int offense = player.getStats().getOffense();
                    int finalDamage = (int)(damage * amplifier + offense);
                    // object.damage();
                }
            }
            case SPEED_POTION, OFFENSE_POTION: {
                int durability = activeItem.maxDurability;
                if(durability <= 0){
                    return;
                }
                switch(activeItem.type){
                    case OFFENSE_POTION -> player.getStats().applyEffects(1, Stat.OFFENSE);
                    case SPEED_POTION -> player.getStats().applyEffects(1, Stat.SPEED);
                }
            }
        }
    }

    public void changePlayerActiveItemIndex(int index){
        player.setActiveItemIndex(index);
    }

    public void scrollPlayerActiveItemIndex(Direction direction){
        switch(direction){
            case UP -> {
                int nextIndex = (player.getActiveItemIndex() + 1) % player.getInventory().size();
                player.setActiveItemIndex(nextIndex);
            }
            case DOWN -> {
                int nextIndex = (player.getActiveItemIndex() - 1 + player.getInventory().size()) % player.getInventory().size();
                player.setActiveItemIndex(nextIndex);
            }
            default -> throw new IllegalArgumentException("Invalid direction input.");
        }
    }

    public void addDirection(Direction direction){
        if(directions.size() == 0){
            player.changeState(new WalkState(player));
        }
        if(directions.contains(direction)){
            return;
        }
        directions.add(direction);
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction){
        directions.removeIf(d -> d == direction);
        if(directions.size() == 0){
            player.changeState(new IdleState());
            return;
        }
        player.setDirection(directions.getLast());
    }

    public void interact(){

    }
}
