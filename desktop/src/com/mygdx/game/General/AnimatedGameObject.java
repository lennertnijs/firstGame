package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Rectangle;
import com.mygdx.game.Util.Vector;

public class AnimatedGameObject extends GameObject{

    private Vector translation;

    public AnimatedGameObject(TextureRegion t, Point p, Dimensions d, String map, Vector vector){
        super(t,p,d, map);
        this.translation = vector;
    }


    @Override
    public Point getPosition(){
        return new Point(super.getPosition().x() + translation.x(), super.getPosition().y() + translation.y());
    }

    @Override
    public Rectangle getHitBox(){
        return new Rectangle(getPosition(), getDimensions());
    }
}
