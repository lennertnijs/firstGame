package NPC;

import com.mygdx.game.Clock.Day;
import com.mygdx.game.Constants;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WeekScheduleTest {

    @Test
    public void testConstructor(){
        Day day = Day.values()[0];

        Position2D position = new Position2D(500, 500);
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position2D position2 = new Position2D(500, 500);
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .timeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule1 = DaySchedule.builder()
                .day(day)
                .addActivity(activityInstance)
                .addActivity(activityInstance2)
                .build();

        DaySchedule daySchedule2 = DaySchedule.builder()
                .day(day)
                .activities(activities)
                .build();

        ArrayList<DaySchedule> daySchedules = new ArrayList<>(Arrays.asList(daySchedule1, daySchedule2));
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.getDaySchedules(), daySchedules)
        );
    }

    @Test
    public void testConstructorInvalid(){
        WeekSchedule.Builder builder = WeekSchedule.builder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.daySchedules(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.addDaySchedule(null).build())
        );
    }

    @Test
    public void testNext(){
        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];

        Position2D position = new Position2D(500, 500);
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position2D position2 = new Position2D(500, 500);
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .timeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        ArrayList<ActivityInstance> activities = new ArrayList<>(Arrays.asList(activityInstance, activityInstance2));

        DaySchedule daySchedule1 = DaySchedule.builder()
                .day(day1)
                .addActivity(activityInstance)
                .addActivity(activityInstance2)
                .build();

        DaySchedule daySchedule2 = DaySchedule.builder()
                .day(day2)
                .activities(activities)
                .build();

        ArrayList<DaySchedule> daySchedules = new ArrayList<>(Arrays.asList(daySchedule1, daySchedule2));
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.next(day1), daySchedule2)
        );
    }

    @Test
    public void testNextInvalid(){
        Day day1 = Day.values()[0];

        Position2D position = new Position2D(500, 500);
        Map map = Map.values()[0];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .timeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position2D position2 = new Position2D(500, 500);
        Map map2 = Map.values()[0];
        int timeInMin2 = Constants.MINUTES_PER_DAY/2;
        Activity activity2 = Activity.values()[0];
        ActivityInstance activityInstance2 = ActivityInstance.builder()
                .position(position2)
                .timeInMinutes(timeInMin2)
                .map(map2)
                .activity(activity2)
                .build();

        DaySchedule daySchedule1 = DaySchedule.builder()
                .day(day1)
                .addActivity(activityInstance)
                .addActivity(activityInstance2)
                .build();

        ArrayList<DaySchedule> daySchedules = new ArrayList<>(Collections.singletonList(daySchedule1));
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> weekSchedule.next(day1)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> weekSchedule.next(null))
        );
    }
}
