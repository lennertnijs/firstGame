package Dialogue;

import com.mygdx.game.V2.Dialogue.DialogueRepository;
import com.mygdx.game.V2.Dialogue.ResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DialogueRepositoryTest {

    private String input1;
    private String input2;
    private ResponseData responseData1;
    private ResponseData responseData2;
    private DialogueRepository repository;
    @BeforeEach
    public void initialise(){
        input1 = "input1";
        input2 = "input2";

        responseData1 = new ResponseData("Hi", new ArrayList<>(Collections.singletonList("1")), new ArrayList<>());
        responseData2 = new ResponseData("Hello", new ArrayList<>(Collections.singletonList("2")), new ArrayList<>());

        Map<String, ResponseData> mapping = new HashMap<>();
        mapping.put(input1, responseData1);
        mapping.put(input2, responseData2);
        repository = new DialogueRepository(mapping);
    }

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class, () -> new DialogueRepository(null));
    }

    @Test
    public void testConstructorWithNullKey(){
        Map<String, ResponseData> mapping = new HashMap<>();
        mapping.put(null, responseData1);
        assertThrows(NullPointerException.class, () -> new DialogueRepository(mapping));
    }

    @Test
    public void testConstructorWithNullValue(){
        Map<String, ResponseData> mapping = new HashMap<>();
        mapping.put(input1, null);
        assertThrows(NullPointerException.class, () -> new DialogueRepository(mapping));
    }

    @Test
    public void testGetResponseData(){
        assertEquals(responseData1, repository.getResponseData(input1));
        assertEquals(responseData2, repository.getResponseData(input2));
    }

    @Test
    public void testGetResponseDataNoMapping(){
        assertThrows(NoSuchElementException.class, () -> repository.getResponseData("No map"));
    }

    @Test
    public void testGetResponseDataWithNull(){
        assertThrows(NullPointerException.class, () -> repository.getResponseData(null));
    }
}
