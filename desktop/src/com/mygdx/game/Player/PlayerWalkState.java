package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.DirectionCalculator;

public class PlayerWalkState implements PlayerState{

    private final Player player;

    public PlayerWalkState(Player player){
        this.player = player;
    }

    @Override
    public String getState() {
        return "walk";
    }

    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot){
        int m = (int) (10 * 0.4f);
        Point p = player.getPosition();
        Point next = null;
        switch(direction){
            case UP: next = new Point(p.x(), p.y() + m); break;
            case RIGHT: next = new Point(p.x() + m, p.y()); break;
            case DOWN: next = new Point(p.x(), p.y() - m); break;
            case LEFT: next = new Point(p.x() - m, p.y()); break;
        }
        // todo next not working on npc
        if(snapShot.isFree(next)){
            player.setPosition(next);
        }
        player.setDirection(DirectionCalculator.calculate(p, next));
    }
}
