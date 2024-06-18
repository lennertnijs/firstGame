package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.HitBox.HitBox;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final PlayerController playerController;

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, SpriteDrawer spriteDrawer, PlayerController playerController){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = spriteDrawer;
        this.playerController = playerController;
    }

    public void update(){
        double delta = clock.update();
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
        playerController.update(delta, getSnapShot());
        drawer.draw(gameObjectRepository.getMaps().get(0));
        for(GameObject o : gameObjectRepository.getMiscObjects()){
            drawer.draw(o);
        }
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
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
        return new HitBoxSnapShot(hitBoxes);
    }
}
