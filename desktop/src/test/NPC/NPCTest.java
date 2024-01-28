package NPC;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Clock.Day;
import com.mygdx.game.Constants;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Map.Map;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

public class NPCTest {

    @Test
    public void testConstructor(){
        Position position = Position.builder().x(500).y( 500).build();
        String spritePath = "npc/stone.png";
        String name = "Bert";

        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];

        Position position1 = Position.builder().x(500).y( 500).build();
        Map map1 = Map.values()[0];
        int timeInMin1 = Constants.MINUTES_PER_DAY/2;
        Activity activity1 = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position1)
                .startTimeInMinutes(timeInMin1)
                .map(map1)
                .activity(activity1)
                .build();

        Position position2 = Position.builder().x(500).y( 500).build();
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

        ArrayList<Position> movementPath = new ArrayList<>(
                Arrays.asList(Position.builder().x(1000).y(500).build(),Position.builder().x(1000).y(1000).build()));

        Activity activity = Activity.values()[0];

        HashMap<Position, ArrayList<Position>> movementNetwork = new HashMap<>();
        movementNetwork.put(Position.builder().x(500).y(500).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(1000).y(500).build(), Position.builder().x(1000).y(1000).build())));
        movementNetwork.put(Position.builder().x(1000).y(500).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(500).y(500).build(), Position.builder().x(1000).y(1000).build())));
        movementNetwork.put(Position.builder().x(1000).y(1000).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(500).y(500).build(), Position.builder().x(1000).y(500).build())));

        MovementGraph graph = MovementGraph.builder().movementGraph(movementNetwork).build();

        ArrayList<Integer> dialogueOptions = new ArrayList<>(Arrays.asList(1, 2, 3));
        NPC npc = NPC.builder()
                .position(position)
                .spritePath(spritePath)
                .name(name)
                .weekSchedule(weekSchedule)
                .movementPath(movementPath)
                .activity(activity)
                .movementGraph(graph)
                .dialogueOptions(dialogueOptions)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(npc.getPosition(), position),
                () -> Assertions.assertEquals(npc.getSpritePath(), spritePath),
                () -> Assertions.assertEquals(npc.getName(), name),
                () -> Assertions.assertEquals(npc.getWeekSchedule(), weekSchedule),
                () -> Assertions.assertEquals(npc.getMovementPath(), movementPath),
                () -> Assertions.assertEquals(npc.getActivity(), activity),
                () -> Assertions.assertEquals(npc.getMovementGraph(), graph),
                () -> Assertions.assertEquals(npc.getDialogueOptions(), dialogueOptions)
        );
    }

    @Test
    public void testConstructorInvalidPosition(){
        NPC.Builder builder1 = getValidNPCBuilder(500);
        NPC.Builder builder2 = getValidNPCBuilder(500);
        NPC.Builder builder3 = getValidNPCBuilder(500);
        NPC.Builder builder4 = getValidNPCBuilder(500);
        NPC.Builder builder5 = getValidNPCBuilder(500);
        NPC.Builder builder6 = getValidNPCBuilder(500);
        NPC.Builder builder7 = getValidNPCBuilder(500);
        NPC.Builder builder8 = getValidNPCBuilder(500);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder1.position(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder2.spritePath(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder3.name(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder4.weekSchedule(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder5.movementPath(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder6.activity(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder7.movementGraph(null).build()),
                () -> Assertions.assertThrows(NullPointerException.class, () -> builder8.dialogueOptions(null).build())

        );
    }

    @Test
    public void testSetActivity(){
        NPC npc = getValidNPCBuilder(500).build();
        npc.setActivity(Activity.WALKING);
        Assertions.assertAll(
                () -> Assertions.assertEquals(Activity.WALKING, npc.getActivity())
        );
    }

    @Test
    public void testSetActivityInvalid(){
        NPC npc = getValidNPCBuilder(500).build();
        npc.setActivity(Activity.WALKING);
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> npc.setActivity(null))
        );
    }

    @Test
    public void testSetMovementPath(){
        NPC npc = getValidNPCBuilder(500).build();
        List<Position> movement = new ArrayList<>(Arrays.asList(
                Position.builder().x(500).y(500).build(),
                Position.builder().x(250).y(250).build()
        ));
        npc.setMovementPath(movement);

        Assertions.assertAll(
                () -> Assertions.assertEquals(npc.getMovementPath(), movement)
        );
    }

    @Test
    public void testSetMovementPathInvalid(){
        NPC npc = getValidNPCBuilder(500).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> npc.setMovementPath(null)),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> npc.setMovementPath(Collections.singletonList(null)))
        );
    }

    @Test
    public void testEquals(){
        NPC npc = getValidNPCBuilder(500).build();
        NPC npc2 = getValidNPCBuilder(500).build();
        NPC npc3 = getValidNPCBuilder(750).build();
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(npc, null),
                () -> Assertions.assertEquals(npc, npc2),
                () -> Assertions.assertNotEquals(npc2, npc3),
                () -> Assertions.assertNotEquals(npc, new ArrayList<>()),
                () -> Assertions.assertEquals(npc.hashCode(), npc2.hashCode()),
                () -> Assertions.assertNotEquals(npc3.hashCode(), npc2.hashCode())
        );
    }

    private NPC.Builder getValidNPCBuilder(int x){
        Position position = Position.builder().x(x).y( 500).build();
        String spritePath = "npc/stone.png";
        String name = "Bert";

        Day day1 = Day.values()[0];
        Day day2 = Day.values()[1];

        Position position1 = Position.builder().x(500).y( 500).build();
        Map map1 = Map.values()[0];
        int timeInMin1 = Constants.MINUTES_PER_DAY/2;
        Activity activity1 = Activity.values()[0];
        ActivityInstance activityInstance = ActivityInstance.builder()
                .position(position1)
                .startTimeInMinutes(timeInMin1)
                .map(map1)
                .activity(activity1)
                .build();

        Position position2 = Position.builder().x(500).y( 500).build();
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

        ArrayList<Position> movementPath = new ArrayList<>(
                Arrays.asList(Position.builder().x(1000).y(500).build(),Position.builder().x(1000).y(1000).build()));

        Activity activity = Activity.values()[0];

        HashMap<Position, ArrayList<Position>> movementNetwork = new HashMap<>();
        movementNetwork.put(Position.builder().x(500).y(500).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(1000).y(500).build(), Position.builder().x(1000).y(1000).build())));
        movementNetwork.put(Position.builder().x(1000).y(500).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(500).y(500).build(), Position.builder().x(1000).y(1000).build())));
        movementNetwork.put(Position.builder().x(1000).y(1000).build(),
                new ArrayList<>(Arrays.asList(Position.builder().x(500).y(500).build(), Position.builder().x(1000).y(500).build())));

        MovementGraph graph = MovementGraph.builder().movementGraph(movementNetwork).build();

        ArrayList<Integer> dialogueOptions = new ArrayList<>(Arrays.asList(1, 2, 3));
        return NPC.builder()
                .position(position)
                .spritePath(spritePath)
                .name(name)
                .weekSchedule(weekSchedule)
                .movementPath(movementPath)
                .activity(activity)
                .movementGraph(graph)
                .dialogueOptions(dialogueOptions);
    }
}
