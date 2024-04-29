package Dialogue;

import com.mygdx.game.V2.Action.Action;
import com.mygdx.game.V2.Dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ResponseDataTest {

    private String response1;
    private String response2;
    private String response3;
    private List<String> newInputs1;
    private List<String> newInputs2;
    private List<String> newInputs3;
    private List<Action> actionList;
    private ResponseData responseData1;
    private ResponseData responseData2;
    private ResponseData responseData3;
    @BeforeEach
    public void initialise(){
        response1 = "Response";
        response2 = "Response2";
        response3 = "Response";
        String input1 = "Input1";
        String input2 = "Input2";
        newInputs1 = new ArrayList<>(Arrays.asList(input1, input2));
        newInputs2 = new ArrayList<>(Collections.singletonList(input2));
        newInputs3 = new ArrayList<>(Arrays.asList(input1, input2));
        actionList = new ArrayList<>();
        responseData1 = new ResponseData(response1 , newInputs1, actionList);
        responseData2 = new ResponseData(response2 ,newInputs2, actionList);
        responseData3 = new ResponseData(response3 ,newInputs3, actionList);
    }

    @Test
    public void testConstructorWithNullResponse(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(null, newInputs1, actionList));
    }

    @Test
    public void testConstructorWithNullNewInputsList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response1, null, actionList));
    }

    @Test
    public void testConstructorWithNullInNewInputList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response1, new ArrayList<>(Arrays.asList(response1, null)), actionList));
    }

    @Test
    public void testConstructorWithNullActionsList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response1, newInputs1, null));
    }

    @Test
    public void testConstructorWithNullInActionList(){
        List<Action> actions = new ArrayList<>();
        actions.add(null);
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response1, newInputs1, actions));
    }

    @Test
    public void testGetResponse(){
        assertEquals(response1, responseData1.getResponse());
        assertEquals(response2, responseData2.getResponse());
        assertEquals(response3, responseData3.getResponse());
    }

    @Test
    public void testGetNewInputs(){
        assertEquals(newInputs1, responseData1.getNewInputs());
        assertEquals(newInputs2, responseData2.getNewInputs());
        assertEquals(newInputs3, responseData3.getNewInputs());
    }

    @Test
    public void testGetActions(){
        assertEquals(actionList, responseData1.getActions());
        assertEquals(actionList, responseData2.getActions());
        assertEquals(actionList, responseData3.getActions());
    }

    @Test
    public void testEquals(){
        assertEquals(responseData1, responseData3);
        assertNotEquals(responseData1, responseData2);
        assertNotEquals(responseData1, new Object());
    }

    @Test
    public void testHashCode(){
        assertEquals(responseData1.hashCode(), responseData3.hashCode());
        assertNotEquals(responseData1.hashCode(), responseData2.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "ResponseData[Response=Response, NewInputs=[], Actions=[Input1, Input2]]";
        assertEquals(expected, responseData1.toString());
    }
}
