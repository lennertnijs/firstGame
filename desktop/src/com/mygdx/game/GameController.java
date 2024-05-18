package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.NPC.NPC;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, MyGame game){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = new SpriteDrawer(game);
    }

    public void update(){
        double delta = clock.update();
        moveNPCS(delta);
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }

    }

    public void movePlayer(double delta){
        gameObjectRepository.getPlayer().move(delta);
    }

    public void moveNPCS(double delta){
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.move((int) delta);
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
    }

    private boolean checkCollisions(GameObject g){
        for(GameObject gameObject : gameObjectRepository.getMiscObjects()){
            if(g.equals(gameObject)) continue;
            if(g.getHitBox().overlaps(gameObject.getHitBox()))
                return true;
        }
        return false;
    }
}
