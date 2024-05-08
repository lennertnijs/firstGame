package Dialogue;

import com.mygdx.game.Action.Action;
import com.mygdx.game.Action.MockAction;
import com.mygdx.game.Dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ResponseDataTest {

    private final String response = "Response";
    private final List<String> inputs = Arrays.asList("Input1", "Input2");
    private final List<Action> actions = Collections.singletonList(new MockAction());
    private ResponseData responseData;

    @BeforeEach
    public void initialise(){
        responseData = new ResponseData(response, inputs, actions);
    }

    @Test
    public void testConstructorWithNullResponse(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(null, inputs, actions));
    }

    @Test
    public void testConstructorWithNullNewInputsList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response, null, actions));
    }

    @Test
    public void testConstructorWithNullInNewInputList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response, Arrays.asList(response, null), actions));
    }

    @Test
    public void testConstructorWithNullActionsList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response, inputs, null));
    }

    @Test
    public void testConstructorWithNullInActionList(){
        assertThrows(NullPointerException.class,
                () -> new ResponseData(response, inputs, Arrays.asList(null, new MockAction())));
    }

    @Test
    public void testGetResponse(){
        assertEquals(response, responseData.response());
    }

    @Test
    public void testGetNewInputs(){
        assertEquals(inputs, responseData.newInputs());
    }

    @Test
    public void testGetActions(){
        assertEquals(actions, responseData.actions());
    }

    @Test
    public void testEquals(){
        ResponseData data1 = new ResponseData(response, inputs, actions);
        ResponseData data2 = new ResponseData(response, inputs, actions);
        ResponseData data3 = new ResponseData(response, inputs, actions);
        ResponseData diffResponse = new ResponseData("Diff", inputs, actions);
        ResponseData diffInputs = new ResponseData(response, Arrays.asList("I1", "I2"), actions);
        ResponseData diffActions = new ResponseData(response, inputs, new ArrayList<>());
        // reflexive
        assertEquals(data1, data1);
        // symmetrical
        assertEquals(data1, data2);
        assertEquals(data2, data1);
        // transitive
        assertEquals(data1, data2);
        assertEquals(data2, data3);
        assertEquals(data1, data3);
        // not equals
        assertNotEquals(data1, diffResponse);
        assertNotEquals(data1, diffInputs);
        assertNotEquals(data1, diffActions);
        assertNotEquals(data1, new Object());
        assertNotEquals(data1, null);
    }

    @Test
    public void testHashCode(){
        ResponseData data1 = new ResponseData(response, inputs, actions);
        ResponseData data2 = new ResponseData(response, inputs, actions);
        ResponseData data3 = new ResponseData(response, inputs, actions);
        ResponseData diffResponse = new ResponseData("Diff", inputs, actions);
        ResponseData diffInputs = new ResponseData(response, Arrays.asList("I1", "I2"), actions);
        ResponseData diffActions = new ResponseData(response, inputs, new ArrayList<>());
        // reflexive
        assertEquals(data1.hashCode(), data1.hashCode());
        // symmetrical
        assertEquals(data1.hashCode(), data2.hashCode());
        assertEquals(data2.hashCode(), data1.hashCode());
        // transitive
        assertEquals(data1.hashCode(), data2.hashCode());
        assertEquals(data2.hashCode(), data3.hashCode());
        assertEquals(data1.hashCode(), data3.hashCode());
        // not equals
        assertNotEquals(data1.hashCode(), diffResponse.hashCode());
        assertNotEquals(data1.hashCode(), diffInputs.hashCode());
        assertNotEquals(data1.hashCode(), diffActions.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "ResponseData[" +
                "Response=Response, " +
                "NewInputs=[Mock action], " +
                "Actions=[Input1, Input2]" +
                "]";
        assertEquals(expected, responseData.toString());
    }
}
