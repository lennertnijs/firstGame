package V2;

import com.mygdx.game.V2.Dialogue.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DialogueDataTest {

    private String input1;
    private String input2;
    private IResponseData responseData1;
    private IResponseData responseData2;
    private List<String> activeInputs;
    private IDialogueRepository dialogueRepository;
    private DialogueData dialogueData;
    @BeforeEach
    public void initialise(){
        input1 = "input1";
        input2 = "input2";

        responseData1 = new ResponseData("Hi", new ArrayList<>(Collections.singletonList(input2)), new ArrayList<>());
        responseData2 = new ResponseData("Hello", new ArrayList<>(Collections.singletonList(input2)), new ArrayList<>());

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
    public void testProcessWithInputInActive(){
        dialogueData.process(input2);
        // no changes
        assertEquals(activeInputs, dialogueData.getActiveInputs());
    }

    @Test
    public void testProcessInputWithNull(){
        assertThrows(NullPointerException.class, () -> dialogueData.process(null));
    }
}
