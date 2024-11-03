package Dialogue;

import com.mygdx.game.npc.dialogue.DialogueRepository;
import com.mygdx.game.npc.dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DialogueRepositoryTest {

    private final String input1 = "Input1";
    private final String input2 = "Input2";
    private final ResponseData responseData1 = new ResponseData("Hi", Arrays.asList("I1", "I2"), new ArrayList<>());
    private final ResponseData responseData2 = new ResponseData("Hello", Arrays.asList("I3", "I4"), new ArrayList<>());
    private final Map<String, ResponseData> map = new HashMap<>();
    private DialogueRepository repository;

    @BeforeEach
    public void initialise(){
        map.put(input1, responseData1);
        map.put(input2, responseData2);
        repository = new DialogueRepository(map);
    }

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class,
                () -> new DialogueRepository(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        map.put(null, responseData2);
        assertThrows(NullPointerException.class,
                () -> new DialogueRepository(map));
    }

    @Test
    public void testConstructorWithNullValue(){
        map.put("Input3", null);
        assertThrows(NullPointerException.class,
                () -> new DialogueRepository(map));
    }

    @Test
    public void testGetResponseData(){
        assertEquals(responseData1, repository.getResponseData(input1));
        assertEquals(responseData2, repository.getResponseData(input2));
    }

    @Test
    public void testGetResponseDataWithNull(){
        assertThrows(NullPointerException.class,
                () -> repository.getResponseData(null));
    }

    @Test
    public void testGetResponseDataNoMapping(){
        assertThrows(NoSuchElementException.class,
                () -> repository.getResponseData("No map"));
    }
}
