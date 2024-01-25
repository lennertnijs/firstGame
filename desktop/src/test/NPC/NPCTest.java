package NPC;

import com.mygdx.game.Clock.Day;
import com.mygdx.game.Constants;
import com.mygdx.game.Entity.Position2D;
import com.mygdx.game.Map.Map;
import com.mygdx.game.NPC.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class NPCTest {
    @Test
    public void testConstructor(){
        Position2D position = new Position2D(500,500);
        String spritePath = "/resources";
        String name = "Bert";

        Day day = Day.values()[0];

        Position2D position1 = new Position2D(500, 500);
        Map map1 = Map.values()[0];
        int timeInMin1 = Constants.MINUTES_PER_DAY/2;
        Activity activity1 = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position1)
                .timeInMinutes(timeInMin1)
                .map(map1)
                .activity(activity1)
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

        ArrayList<Position2D> movementPath = new ArrayList<>(
                Arrays.asList(new Position2D(1000,500), new Position2D(1000,1000)));

        Activity activity = Activity.values()[0];

        HashMap<Position2D, ArrayList<Position2D>> movementNetwork = new HashMap<>();
        movementNetwork.put(new Position2D(500,500),
                new ArrayList<>(Arrays.asList(new Position2D(1000,500), new Position2D(1000,1000))));
        movementNetwork.put(new Position2D(1000,500),
                new ArrayList<>(Arrays.asList(new Position2D(500,500), new Position2D(1000,1000))));
        movementNetwork.put(new Position2D(1000,1000),
                new ArrayList<>(Arrays.asList(new Position2D(500,500), new Position2D(1000,500))));

        ArrayList<Integer> dialogueOptions = new ArrayList<>(Arrays.asList(1, 2, 3));
        NPC npc = NPC.builder()
                .position(position)
                .spritePath(spritePath)
                .name(name)
                .weekSchedule(weekSchedule)
                .movementPath(movementPath)
                .activity(activity)
                .movementNetwork(movementNetwork)
                .dialogueOptions(dialogueOptions)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(npc.getPosition(), position),
                () -> Assertions.assertEquals(npc.getSpritePath(), spritePath),
                () -> Assertions.assertEquals(npc.getName(), name),
                () -> Assertions.assertEquals(npc.getWeekSchedule(), weekSchedule),
                () -> Assertions.assertEquals(npc.getMovementPath(), movementPath),
                () -> Assertions.assertEquals(npc.getActivity(), activity),
                () -> Assertions.assertEquals(npc.getMovementNetwork(), movementNetwork),
                () -> Assertions.assertEquals(npc.getDialogueOptions(), dialogueOptions)
        );
    }

    @Test
    public void testConstructorInvalidPosition(){
        NPC.Builder builder = getValidNPCBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.position(null).build())
        );
    }

    @Test
    public void testConstructorInvalidSpritePath(){
        NPC.Builder builder = getValidNPCBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.spritePath(null).build())
        );
    }

    @Test
    public void testConstructorInvalidName(){
        NPC.Builder builder = getValidNPCBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.name(null).build())
        );
    }

    @Test
    public void testConstructorInvalidWeekSchedule(){
        NPC.Builder builder = getValidNPCBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.weekSchedule(null).build())
        );
    }

    @Test
    public void testConstructorInvalidMovementPath(){
        NPC.Builder builder = getValidNPCBuilder();

        Position2D point = new Position2D(500, 500);

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.movementPath(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.addToMovementPath(point).addToMovementPath(null).build())
        );
    }

    @Test
    public void testConstructorInvalidActivity(){
        NPC.Builder builder = getValidNPCBuilder();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.activity(null).build())
        );
    }

    @Test
    public void testConstructorInvalidMovementNetwork(){
        NPC.Builder builder = getValidNPCBuilder();

        HashMap<Position2D, ArrayList<Position2D>> nullKey = new HashMap<>();
        nullKey.put(null, new ArrayList<>(Collections.singletonList(new Position2D(500, 500))));

        HashMap<Position2D, ArrayList<Position2D>> nullValue = new HashMap<>();
        nullValue.put(new Position2D(500, 500), null);

        HashMap<Position2D, ArrayList<Position2D>> nullValueInValueList = new HashMap<>();
        nullValueInValueList.put(new Position2D(500, 500), new ArrayList<>(Collections.singletonList(null)));

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.movementNetwork(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.movementNetwork(nullKey).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.movementNetwork(nullValue).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.movementNetwork(nullValueInValueList).build())

        );
    }

    @Test
    public void testConstructorInvalidDialogueOptions(){
        NPC.Builder builder = getValidNPCBuilder();

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder.dialogueOptions(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> builder.addToDialogueOptions(5).addToDialogueOptions(null).build())
        );
    }

    private NPC.Builder getValidNPCBuilder(){
        Position2D position = new Position2D(500,500);
        String spritePath = "/resources";
        String name = "Bert";

        Day day = Day.values()[0];

        Position2D position1 = new Position2D(500, 500);
        Map map1 = Map.values()[0];
        int timeInMin1 = Constants.MINUTES_PER_DAY/2;
        Activity activity1 = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position1)
                .timeInMinutes(timeInMin1)
                .map(map1)
                .activity(activity1)
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

        ArrayList<Position2D> movementPath = new ArrayList<>(
                Arrays.asList(new Position2D(1000,500), new Position2D(1000,1000)));

        Activity activity = Activity.values()[0];

        HashMap<Position2D, ArrayList<Position2D>> movementNetwork = new HashMap<>();
        movementNetwork.put(new Position2D(500,500),
                new ArrayList<>(Arrays.asList(new Position2D(1000,500), new Position2D(1000,1000))));
        movementNetwork.put(new Position2D(1000,500),
                new ArrayList<>(Arrays.asList(new Position2D(500,500), new Position2D(1000,1000))));
        movementNetwork.put(new Position2D(1000,1000),
                new ArrayList<>(Arrays.asList(new Position2D(500,500), new Position2D(1000,500))));

        ArrayList<Integer> dialogueOptions = new ArrayList<>(Arrays.asList(1, 2, 3));
        return NPC.builder()
                .position(position)
                .spritePath(spritePath)
                .name(name)
                .weekSchedule(weekSchedule)
                .movementPath(movementPath)
                .activity(activity)
                .movementNetwork(movementNetwork)
                .dialogueOptions(dialogueOptions);
    }
}
