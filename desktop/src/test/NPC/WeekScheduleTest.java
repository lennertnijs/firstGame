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
        HashMap<Day, DaySchedule> daySchedules = generateValidMapOfSchedules();
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.getDaySchedules(), daySchedules)
        );
    }

    @Test
    public void testConstructorInvalid(){
        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        WeekSchedule.Builder builder = WeekSchedule.builder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, builder::build),

                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.daySchedules(null).build()),

                () -> daySchedules.put(null, null),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.daySchedules(daySchedules).build()),
                () -> daySchedules.remove(null),

                () -> daySchedules.put(Day.values()[0], null),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.daySchedules(daySchedules).build())
        );
    }

    @Test
    public void testGetDaySchedule(){
        DaySchedule daySchedule1 = generateValidDaySchedule(250);
        DaySchedule daySchedule2 = generateValidDaySchedule(500);
        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];
        Day dayLast = Day.values()[Day.values().length-1];

        HashMap<Day, DaySchedule> mapping = new HashMap<Day, DaySchedule>(){{
            put(day1, daySchedule1);
            put(day2, daySchedule2);
        }};

        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(mapping).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(daySchedule1, weekSchedule.getDaySchedule(day1)),
                () -> Assertions.assertEquals(daySchedule2, weekSchedule.getDaySchedule(day2)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> weekSchedule.getDaySchedule(dayLast))
        );
    }

    @Test
    public void testGetDayScheduleInvalid(){
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(generateValidMapOfSchedules()).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> weekSchedule.getDaySchedule(null))
        );
    }

    @Test
    public void testNext(){
        DaySchedule daySchedule1 = generateValidDaySchedule(500);
        DaySchedule daySchedule2 = generateValidDaySchedule(250);
        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];
        HashMap<Day, DaySchedule> mapping = new HashMap<Day, DaySchedule>(){{
            put(day1, daySchedule1);
            put(day2, daySchedule2);
        }};
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(mapping).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule.next(day1), daySchedule2),
                () -> Assertions.assertThrows(NullPointerException.class, () ->  weekSchedule.next(day2)),
                () -> Assertions.assertEquals(weekSchedule.next(Day.values()[Day.values().length-1]), daySchedule1)
        );
    }

    @Test
    public void testNextInvalid(){
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(generateValidMapOfSchedules()).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> weekSchedule.next(null))
        );
    }

    @Test
    public void testEquals(){
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(generateValidMapOfSchedules()).build();
        WeekSchedule weekSchedule2 = WeekSchedule.builder().daySchedules(generateValidMapOfSchedules()).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(weekSchedule, weekSchedule2),
                () -> Assertions.assertNotEquals(weekSchedule, null),
                () -> Assertions.assertNotEquals(weekSchedule, new ArrayList<>()),
                () -> Assertions.assertEquals(weekSchedule.hashCode(), weekSchedule2.hashCode())
        );
    }



    private HashMap<Day, DaySchedule> generateValidMapOfSchedules(){
        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Position position = Position.builder().x(500).y(500).build();
        Map map = Map.values()[0];

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

        return new HashMap<Day, DaySchedule>(){{
            put(day1, daySchedule1);
            put(day2, daySchedule2);
        }};
    }

    private DaySchedule generateValidDaySchedule(int x){
        int timeInMin = Constants.MINUTES_PER_DAY/2;
        Position position = Position.builder().x(x).y(500).build();
        Map map = Map.values()[0];

        Activity activity = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position)
                .startTimeInMinutes(timeInMin)
                .map(map)
                .activity(activity)
                .build();

        Position position2 = Position.builder().x(x).y(500).build();
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

        return DaySchedule.builder()
                .activities(activities)
                .build();
    }
}
