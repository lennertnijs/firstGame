package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.DroppedItem;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.HitBox.HitBox;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final PlayerController playerController;
    private final List<DroppedItem> droppedItems;

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, SpriteDrawer spriteDrawer, PlayerController playerController){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = spriteDrawer;
        this.playerController = playerController;
        droppedItems = new ArrayList<>();
    }

    public void update(){
        double delta = clock.update();
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
        playerController.update(delta, getSnapShot());
        checkDroppedItems();
        drawer.draw(gameObjectRepository.getMaps().get(0));
        for(GameObject o : gameObjectRepository.getMiscObjects()){
            drawer.draw(o);
        }
        for(Breakable b : gameObjectRepository.getBreakables()){
            drawer.draw(b);
        }
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
        for(GameObject o : droppedItems){
            drawer.draw(o);
        }
        drawer.draw(playerController.getPlayer());
    }

    // todo static objects dont need new snapshots, dynamic snapshots need updating
    private HitBoxSnapShot getSnapShot(){
        List<HitBox> hitBoxes = new ArrayList<>();
        for(GameObject o : gameObjectRepository.getMiscObjects()){
            hitBoxes.add(o.getHitBox());
        }
        for(GameObject o : gameObjectRepository.getNpcs()){
            hitBoxes.add(o.getHitBox());
        }
        for(GameObject o : gameObjectRepository.getBreakables()){
            hitBoxes.add(o.getHitBox());
        }
        return new HitBoxSnapShot(hitBoxes);
    }

    public void playerUseActiveItem(){
        Point position = playerController.getPlayer().getPosition();
        Point hit = new Point(position.x() + 30, position.y());
        List<Breakable> broken = new ArrayList<>();
        // if player frame has damage hit box -> check colisions
        for(Breakable breakable : gameObjectRepository.getBreakables()){
            if(breakable.getHitBox().contains(hit)){
                broken.add(breakable);
            }
        }
        Breakable b = broken.size() > 0 ? broken.get(0) : null;
        playerController.useActiveItem(b);
        for(Breakable breakable : broken){
            gameObjectRepository.getBreakables().remove(breakable);
            Loot loot = breakable.getDrops();
            TextureRegion t = new TextureRegion(new Texture(Gdx.files.internal("images/stone.png")));
            droppedItems.add(new DroppedItem(t, breakable.getPosition(), new Dimensions(20, 20), "main", loot.name(), loot.amount()));
        }
    }

    public void checkDroppedItems(){
        //playerController.getPlayer().getInventory().add(droppedItem.name(), droppedItem.amount());
        droppedItems.removeIf(droppedItem -> droppedItem.getHitBox().contains(playerController.getPlayer().getPosition()));
    }
}
