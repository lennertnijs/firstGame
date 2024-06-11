package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Util.Direction;

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
        updateNPCS(delta);
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
        drawer.draw(gameObjectRepository.getPlayer());

    }

    public void movePlayer(Direction direction){
        Player player = gameObjectRepository.getPlayer();
        player.setDirection(direction);
        player.move(clock.getLastDelta());
    }

    public void updateNPCS(double delta){
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
    }

    private boolean checkCollisions(GameObject g){
        return false;
    }
}
