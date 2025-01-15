package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.Breakables.Breakable;
import game.DAO.BreakableLoader;
import game.DAO.ItemLoader;
import game.DAO.NPCLoader;
import game.clock.Clock;
import game.game_object.GameObject;
import game.game_object.GameObjectType;
import game.inventory.Item;
import game.inventory.ItemType;
import game.npc.NPC;
import game.player.Player;
import game.player.states.UseItemState;
import game.player.states.WalkState;
import game.stats.Stat;
import game.util.Direction;
import game.util.Vec2;

import java.util.*;

public class GameController {

    private final Player player;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final Texture map = new Texture(Gdx.files.internal("background.png"));
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final Map<DamageAmpKey, Float> damageAmps = new HashMap<>();
    private final List<NPC> npcs;
    private final Map<ItemType, TextureRegion> itemTextures = ItemLoader.loadItemTextures();
    private final Map<GameObjectType, TextureRegion> breakableTextures = BreakableLoader.loadTextures();
    private final List<Breakable> breakables = BreakableLoader.loadBreakables();
    private boolean drawInventory = false;

    public GameController(Player player, Clock clock, SpriteDrawer spriteDrawer){
        this.player = player;
        this.clock = clock;
        this.drawer = spriteDrawer;
        this.npcs = new ArrayList<>(List.of(NPCLoader.create()));
    }

    public void update(){
        drawer.draw(map);
        double delta = clock.update();
        Vec2 nextPlayerPos = player.getState().getNextPosition();
        if(getCollidingGameObjects(nextPlayerPos, player.getWidth(), player.getHeight()).isEmpty()){
            player.update(delta);
        }
        for(NPC npc : npcs){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
        breakables.removeIf(Breakable::isBroken);

        drawer.draw(player);
        for(NPC npc : npcs){
            drawer.draw(npc);
        }
        for(Breakable breakable : breakables){
            drawer.draw(breakable);
        }
        drawer.drawActiveItem(player.getInventory().getItem(player.getActiveItemIndex()), itemTextures);
        if(drawInventory){
            drawer.drawInventory(player.getInventory(), itemTextures);
        }
        drawer.drawText("temporary text");
    }

    private List<GameObject> getCollidingGameObjects(Vec2 position, int width, int height){
        List<GameObject> collidingObjects = new ArrayList<>();
        for(NPC npc : npcs){
            if(overlaps(position, width, height, npc)){
                collidingObjects.add(npc);
            }
        }
        for(Breakable breakable : breakables){
            if(overlaps(position, width, height, breakable)){
                collidingObjects.add(breakable);
            }
        }
        return collidingObjects;
    }

    private boolean overlaps(Vec2 position, int width, int height, GameObject o2){
        return !(position.x() >= o2.getPosition().x() + o2.getWidth() ||
                position.x() + width <= o2.getPosition().x() ||
                position.y() >= o2.getPosition().y() + o2.getHeight() ||
                position.y() + height <= o2.getPosition().y());
    }

    public void playerUseActiveItem(){
        Item activeItem = player.getInventory().getItem(player.getActiveItemIndex());
        player.changeState(new UseItemState(player, "mine", player.getState()));
        switch(activeItem.type){
            case SWORD, PICKAXE, AXE, BOW: {
                Vec2 hitPosition = new Vec2(player.getPosition().x() + 150, player.getPosition().y() + 64);
                System.out.println("Used active item\n");
                int durability = activeItem.maxDurability;
                if(durability <= 0){
                    return;
                }
                List<GameObject> objects = getCollidingGameObjects(hitPosition, 1, 1);
                for(GameObject object : objects) {
                    if (object.type == GameObjectType.BORING) {
                        continue;
                    }
                    int damage = activeItem.damage;
                    Float amplifier = damageAmps.get(new DamageAmpKey(GameObjectType.PLAYER, activeItem.type));
                    if(amplifier == null){
                        amplifier = 0f;
                    }
                    int offense = player.getStats().getOffense();
                    int finalDamage = (int)(damage * amplifier + offense);
                    object.damage(finalDamage); // (????)
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

    public void moveWithClick(Vec2 goal){
        Vec2 updated = new Vec2(goal.x() - player.getWidth() / 2, goal.y());
        player.changeState(new WalkState(player, updated));
    }

    public void interact(){

    }

    public void drawInventory(){
        this.drawInventory = !drawInventory;
    }
}
