package V2;

import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private Location location1;
    private Location location2;
    private Location location3;

    @BeforeEach
    public void initialise(){
        location1 = new Location("map1", new Point(0,0));
        location2 = new Location("map2", new Point(0,0));
        location3 = new Location("map1", new Point(0,0));
    }
}
