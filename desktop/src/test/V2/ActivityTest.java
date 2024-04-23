package V2;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import com.mygdx.game.V2.Util.Time;
import com.mygdx.game.V2.WeekSchedule.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private Location location;
    private Time time;
    private ActivityType activityType;
    private Activity activity;

    @BeforeEach
    public void initialise(){
        location = new Location("Map", new Point(5, 10));
        time = new Time(10, 15);
        activityType = ActivityType.RUNNING;

        activity = new Activity(location, time, activityType);
    }
}
