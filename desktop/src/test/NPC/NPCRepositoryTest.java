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
import java.util.Collections;
import java.util.HashMap;

public class NPCRepositoryTest {

    @Test
    public void testConstructor(){
        NPCRepository repo = new NPCRepository();
        Assertions.assertAll(
                () -> Assertions.assertEquals(repo.getNpcs(), new ArrayList<>())
        );
    }

    @Test
    public void testAdd(){
        NPCRepository npcRepository = new NPCRepository();
        NPC bart = getValidNPC("Bart");
        NPC jan = getValidNPC("Jan");
        npcRepository.add(bart);
        npcRepository.add(jan);
        Assertions.assertAll(
                () -> Assertions.assertEquals(npcRepository.getNpcs().get(0), bart),
                () -> Assertions.assertEquals(npcRepository.getNpcs().get(1), jan),
                () -> Assertions.assertEquals(npcRepository.getNpcs(), new ArrayList<>(Arrays.asList(bart, jan)))
        );
    }

    @Test
    public void testAddInvalid(){
        NPCRepository npcRepository = new NPCRepository();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> npcRepository.add(null))
        );
    }


    private NPC getValidNPC(String name){
        Position position = Position.builder().x(500).y( 500).build();
        String spritePath = "/resources";
        Day day = Day.values()[0];

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


        ArrayList<ActivityInstance> activities = new ArrayList<>(Collections.singletonList(activityInstance));
        DaySchedule daySchedule = DaySchedule.builder()
                .activities(activities)
                .build();
        HashMap<Day, DaySchedule> daySchedules = new HashMap<>();
        daySchedules.put(day, daySchedule);
        WeekSchedule weekSchedule = WeekSchedule.builder().daySchedules(daySchedules).build();

        MovementGraph movementGraph = MovementGraph.builder().movementGraph(new HashMap<>()).build();

        ArrayList<Integer> dialogueOptions = new ArrayList<>(Arrays.asList(1, 2, 3));
        return NPC.builder()
                .position(position)
                .spritePath(spritePath)
                .name(name)
                .weekSchedule(weekSchedule)
                .movementPath(new ArrayList<>())
                .activity(Activity.WALKING)
                .movementGraph(movementGraph)
                .dialogueOptions(dialogueOptions)
                .build();
    }
}
