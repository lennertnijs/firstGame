package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Input.MovementFlags;
import com.mygdx.game.Util.Direction;

import static com.mygdx.game.Util.Direction.*;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final MovementFlags movementFlags = new MovementFlags();

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, MyGame game){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = new SpriteDrawer(game);
    }

    public void update(){
        double delta = clock.update();
        updateNPCS(delta);
        movePlayer();
        drawer.draw(gameObjectRepository.getMaps().get(0));
        for(GameObject o : gameObjectRepository.getMiscObjects()){
            drawer.draw(o);
        }
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
        drawer.draw(gameObjectRepository.getPlayer());

    }

    public void movePlayer(){
        Player player = gameObjectRepository.getPlayer();
        Direction direction = movementFlags.getMovementDirection();
        if(direction == null){
            return;
        }
        player.move(clock.getLastDelta(), direction);
        player.setDirection(translate(direction));
    }

    private Direction translate(Direction direction){
        if(direction == UP || direction == RIGHT || direction == DOWN || direction == LEFT){
            return direction;
        }
        if(direction == NORTHEAST || direction == NORTHWEST){
            return UP;
        }
        return DOWN;
    }

    public void updateNPCS(double delta){
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
    }

    private boolean checkCollisions(GameObject g){
        return false;
    }

    public MovementFlags getMovementFlags(){
        return movementFlags;
    }
}
