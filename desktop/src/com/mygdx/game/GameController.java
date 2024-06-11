package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Keys.ActivityType;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final MovementInputs movementFlags = new MovementInputs();
    private final PlayerController playerController;

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, MyGame game, PlayerController playerController){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = new SpriteDrawer(game);
        this.playerController = playerController;
    }

    public void update(){
        double delta = clock.update();
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
        playerController.update(delta, movementFlags.getCurrentDirection());
        drawer.draw(gameObjectRepository.getMaps().get(0));
        for(GameObject o : gameObjectRepository.getMiscObjects()){
            drawer.draw(o);
        }
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
        drawer.draw(playerController.getPlayer());
    }

    public MovementInputs getMovementFlags(){
        return movementFlags;
    }

    public void setActivityType(ActivityType activityType){
        playerController.setCurrentActivity(activityType);
    }

    public void removeActivityType(){
        playerController.removeCurrentActivity();
    }
}
