package Dialogue;

import com.mygdx.game.Action.Action;
import com.mygdx.game.Action.MockAction;
import com.mygdx.game.npc.dialogue.DialogueData;
import com.mygdx.game.npc.dialogue.DialogueRepository;
import com.mygdx.game.npc.dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DialogueDataTest {

    private final String input1 = "Good day";
    private final String response1 = "Good day to you, too!";
    private final String input2 = "How are you";
    private final String response2 = "I'm good.";
    private final List<String> activeInputs = Collections.singletonList(input1);
    private final Map<String, ResponseData> map = new HashMap<>();
    private DialogueRepository repository;
    private DialogueData dialogueData;
    @BeforeEach
    public void initialise(){
        List<Action> actions = Arrays.asList(new MockAction(), new MockAction());
        ResponseData responseData1 = new ResponseData(response1, Collections.singletonList(input2), actions);
        ResponseData responseData2 = new ResponseData(response2, Collections.singletonList(input2), actions);
        map.put(input1, responseData1);
        map.put(input2, responseData2);
        repository = new DialogueRepository(map);

        dialogueData = new DialogueData(activeInputs, repository);
    }


    @Test
    public void testConstructorWithNullActiveInputs(){
        assertThrows(NullPointerException.class,
                () -> new DialogueData(null, repository));
    }

    @Test
    public void testConstructorWithNullInActiveInputs(){
        assertThrows(NullPointerException.class,
                () -> new DialogueData(Arrays.asList(input1, null), repository));
    }

    @Test
    public void testConstructorWithNullDialogueRepository(){
        assertThrows(NullPointerException.class,
                () -> new DialogueData(activeInputs, null));
    }

    @Test
    public void testGetActiveInputs(){
        assertEquals(activeInputs, dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessInput(){
        String r1 = dialogueData.processInput(input1);
        assertEquals(Collections.singletonList(input2), dialogueData.getActiveInputs());
        assertEquals(r1, response1);

        String r2 = dialogueData.processInput(input2);
        assertEquals(Collections.singletonList(input2), dialogueData.getActiveInputs());
        assertEquals(r2, response2);
    }

    @Test
    public void testProcessWithInputNotInActive(){
        assertThrows(IllegalStateException.class,
                () -> dialogueData.processInput(input2));
    }

    @Test
    public void testProcessInputWithNull(){
        assertThrows(NullPointerException.class,
                () -> dialogueData.processInput(null));
    }
}
