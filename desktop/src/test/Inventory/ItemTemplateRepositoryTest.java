package Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Inventory.ItemTemplate;
import com.mygdx.game.Inventory.ItemTemplateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTemplateRepositoryTest {

    private ItemTemplate template1;
    private ItemTemplate template2;
    private List<ItemTemplate> list;
    private ItemTemplateRepository emptyRepository;
    private ItemTemplateRepository repository;

    @BeforeEach
    public void initialise(){
        template1 = new ItemTemplate("Stone", "A stone.", Mockito.mock(Texture.class), 64);
        template2 = new ItemTemplate("Wood", "A wood.", Mockito.mock(Texture.class), 64);
        list = Arrays.asList(template1, template2);

        emptyRepository = new ItemTemplateRepository();
        repository = new ItemTemplateRepository(list);
    }

    @Test
    public void testConstructorWithNullList(){
        assertThrows(NullPointerException.class,
                () -> new ItemTemplateRepository(null));
    }

    @Test
    public void testConstructorWithListContainsNull(){
        assertThrows(NullPointerException.class,
                () -> new ItemTemplateRepository(Arrays.asList(template1, null)));
    }

    @Test
    public void testConstructorWithDuplicateTemplate(){
        assertThrows(IllegalArgumentException.class,
                () -> new ItemTemplateRepository(Arrays.asList(template1, template1)));
    }

    @Test
    public void testGetFromName(){
        assertEquals(template1, repository.getFromName("Stone"));
        assertEquals(template2, repository.getFromName("Wood"));
    }

    @Test
    public void testGetFromNameWithNull(){
        assertThrows(NullPointerException.class,
                () -> repository.getFromName(null));
    }

    @Test
    public void testGetFromNameNoMapping(){
        assertThrows(NoSuchElementException.class,
                () -> repository.getFromName("Sand"));
    }

    @Test
    public void testAdd(){
        ItemTemplate template3 = new ItemTemplate("Sand", "A sand.", Mockito.mock(Texture.class), 32);
        repository.add(template3);
        assertEquals(template3, repository.getFromName("Sand"));
    }

    @Test
    public void testAddWithNull(){
        assertThrows(NullPointerException.class,
                () -> repository.add(null));
    }

    @Test
    public void testAddDuplicate(){
        assertThrows(IllegalArgumentException.class,
                () -> repository.add(template2));
    }
}
