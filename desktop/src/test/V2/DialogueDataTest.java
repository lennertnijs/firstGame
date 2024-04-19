package V2;

import com.mygdx.game.V2.Action.Action;
import com.mygdx.game.V2.Action.MockAction;
import com.mygdx.game.V2.Dialogue.*;
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
    private IDialogueRepository dialogueRepository;
    private DialogueData dialogueData;
    @BeforeEach
    public void initialise(){
        input1 = "input1";
        input2 = "input2";

        MockAction mockAction = new MockAction();
        List<Action> actions = new ArrayList<>(Collections.singletonList(mockAction));
        IResponseData responseData1 = new ResponseData("Hi", new ArrayList<>(Collections.singletonList(input2)), actions);
        IResponseData responseData2 = new ResponseData("Hello", new ArrayList<>(Collections.singletonList(input2)), actions);

        activeInputs = new ArrayList<>(Collections.singletonList(input1));
        Map<String, IResponseData> mapping = new HashMap<>();
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
        dialogueData.process(input1);
        assertEquals(new ArrayList<>(Collections.singletonList(input2)), dialogueData.getActiveInputs());

        dialogueData.process(input1);
        assertEquals(new ArrayList<>(Collections.singletonList(input2)), dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessWithInputNotInActive(){
        dialogueData.process(input2);
        assertEquals(activeInputs, dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessInputWithNull(){
        assertThrows(NullPointerException.class, () -> dialogueData.process(null));
    }
}
