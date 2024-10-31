package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Bat.Bat;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.game_object.Renderer;
import com.mygdx.game.game_object.StaticRenderer;
import com.mygdx.game.game_object.Frame;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.npc.NPC;

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
        for(Bat bat : gameObjectRepository.getBats()){
            bat.update(delta, playerController.getPlayer().getPosition());
        }
        checkDroppedItems();
        drawer.draw(gameObjectRepository.getMaps().get(0));
//        for(GameObject o : gameObjectRepository.getMiscObjects()){
//            drawer.draw(o);
//        }
//        for(Breakable b : gameObjectRepository.getBreakables()){
//            drawer.draw(b);
//        }
        for(Bat bat : gameObjectRepository.getBats()){
            drawer.draw(bat);
        }
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
//        for(GameObject o : droppedItems){
//            drawer.draw(o);
//        }
        drawer.draw(playerController.getPlayer());
    }

    // todo static objects dont need new snapshots, dynamic snapshots need updating
    private HitBoxSnapShot getSnapShot(){
        List<HitBox> hitBoxes = new ArrayList<>();
//        for(GameObject o : gameObjectRepository.getMiscObjects()){
//            hitBoxes.add(o.getHitBox());
//        }
//        for(GameObject o : gameObjectRepository.getNpcs()){
//            hitBoxes.add(o.getHitBox());
//        }
//        for(GameObject o : gameObjectRepository.getBreakables()){
//            hitBoxes.add(o.getHitBox());
//        }
        return new HitBoxSnapShot(hitBoxes);
    }

    public void playerUseActiveItem(){
        Vec2 position = playerController.getPlayer().getPosition();
        Vec2 hit = new Vec2(position.x() + 30, position.y());
        List<Breakable> broken = new ArrayList<>();
        // if player frame has damage hit box -> check colisions
        for(Breakable breakable : gameObjectRepository.getBreakables()){
//            if(breakable.getHitBox().contains(hit)){
//                broken.add(breakable);
//            }
        }
        Breakable b = broken.size() > 0 ? broken.get(0) : null;
        playerController.useActiveItem(b);
        for(Breakable breakable : broken){
            gameObjectRepository.getBreakables().remove(breakable);
            Loot loot = breakable.getDrops();
            TextureRegion t = new TextureRegion(new Texture(Gdx.files.internal("images/stone.png")));
            Frame frame = Frame.builder().texture(t).width(200).height(200).build();
            Renderer renderer = new StaticRenderer(frame);
            Transform transform = new Transform(breakable.getPosition());
            droppedItems.add(new DroppedItem(transform, renderer, "main", loot.name(), loot.amount()));
        }
    }

    public void interact(){
        Vec2 p = new Vec2(playerController.getPlayer().getPosition().x() + 50, playerController.getPlayer().getPosition().y());
        for(NPC npc : gameObjectRepository.getNpcs()){
//            if(npc.getHitBox().contains(p)){
//                drawer.draw(npc.handleInputLine("line"));
//            }
        }
    }

    public void checkDroppedItems(){
        //playerController.getPlayer().getInventory().add(droppedItem.name(), droppedItem.amount());
        //droppedItems.removeIf(droppedItem -> droppedItem.getHitBox().contains(playerController.getPlayer().getPosition()));
    }
}
