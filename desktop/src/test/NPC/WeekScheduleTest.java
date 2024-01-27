package NPC;

import com.mygdx.game.Clock.Day;
import com.mygdx.game.Constants;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WeekScheduleTest {

    @Test
    public void testConstructor(){
        Day day = Day.values()[0];

        Position position = Position.builder().x(500).y(500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y(500).build();
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .startTimeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule1 = DaySchedule.builder()
                .activities(activities)
                .build();

        DaySchedule daySchedule2 = DaySchedule.builder()
                .activities(activities)
                .build();

        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        daySchedules.put(day, daySchedule1);
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.getDaySchedules(), daySchedules)
        );
    }

    @Test
    public void testConstructorInvalid(){
        WeekSchedule.Builder builder = WeekSchedule.builder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.daySchedules(null).build())
        );
    }

    @Test
    public void testNext(){
        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];

        Position position = Position.builder().x(500).y(500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y(500).build();
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .startTimeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule1 = DaySchedule.builder()
                .activities(activities)
                .build();

        DaySchedule daySchedule2 = DaySchedule.builder()
                .activities(activities)
                .build();

        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        daySchedules.put(day1, daySchedule1);
        daySchedules.put(day2, daySchedule2);
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.next(day1), daySchedule2)
        );
    }

    @Test
    public void testNextInvalid(){
        Day day1 = Day.values()[0];

        Position position = Position.builder().x(500).y(500).build();
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(500).y(500).build();
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .startTimeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        DaySchedule daySchedule1 = DaySchedule.builder()
                .activities(new ArrayList<>(Arrays.asList(activityInstance, activityInstance2)))
                .build();

        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        daySchedules.put(day1, daySchedule1);

    }
}
