package NPC;

import com.mygdx.game.Entity.Position2D;
import org.junit.jupiter.api.Test;

public class TestGraphs {

    @Test
    public void testGraphs(){
        Position2D v1 = new Position2D(2000, 2000);
        Position2D v2 = new Position2D(1000,2000);
        Position2D v3 = new Position2D( 1000, 1000);
        Position2D v4 = new Position2D( 1000, 0);
        Position2D v5 = new Position2D( 0, 0);
        Position2D v6 = new Position2D( 0, 1000);
    }
}