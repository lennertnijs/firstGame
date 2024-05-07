package Dialogue;

import com.mygdx.game.Action.Action;
import com.mygdx.game.Action.MockAction;
import com.mygdx.game.Dialogue.DialogueData;
import com.mygdx.game.Dialogue.DialogueRepository;
import com.mygdx.game.Dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DialogueDataTest {

    private String input1;
    private String input2;
    private List<String> activeInputs;
    private DialogueRepository dialogueRepository;
    private DialogueData dialogueData;
    @BeforeEach
    public void initialise(){
        input1 = "input1";
        input2 = "input2";

        MockAction mockAction = new MockAction();
        List<Action> actions = new ArrayList<>(Collections.singletonList(mockAction));
        ResponseData responseData1 = new ResponseData("Hi", new ArrayList<>(Collections.singletonList(input2)), actions);
        ResponseData responseData2 = new ResponseData("Hello", new ArrayList<>(Collections.singletonList(input2)), actions);

        activeInputs = new ArrayList<>(Collections.singletonList(input1));
        Map<String, ResponseData> mapping = new HashMap<>();
        mapping.put(input1, responseData1);
        mapping.put(input2, responseData2);
        dialogueRepository = new DialogueRepository(mapping);

        dialogueData = new DialogueData(activeInputs, dialogueRepository);
    }


    @Test
    public void testConstructorWithNullActiveInputs(){
        assertThrows(NullPointerException.class, () -> new DialogueData(null, dialogueRepository));
    }

    @Test
    public void testConstructorWithNullInActiveInputs(){
        List<String> active = new ArrayList<>(Arrays.asList(null, input1));
        assertThrows(NullPointerException.class, () -> new DialogueData(active, dialogueRepository));
    }

    @Test
    public void testConstructorWithNullDialogueRepository(){
        assertThrows(NullPointerException.class, () -> new DialogueData(activeInputs, null));
    }

    @Test
    public void testGetActiveInputs(){
        assertEquals(activeInputs, dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessInput(){
        dialogueData.processInput(input1);
        assertEquals(new ArrayList<>(Collections.singletonList(input2)), dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessWithInputNotInActive(){
        assertThrows(IllegalStateException.class, () -> dialogueData.processInput(input2));
    }

    @Test
    public void testProcessInputWithNull(){
        assertThrows(NullPointerException.class, () -> dialogueData.processInput(null));
    }
}
