package R_treeTest;

import game.r_tree.Node;
import game.r_tree.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class NodeTest {

    private DummyGameObject gameObject;
    private Node<DummyGameObject> node;

    @BeforeEach
    public void initialise(){
        gameObject = new DummyGameObject(0, 50, 200, 400);
        node = new Node<>();
    }

    @Test
    public void testEmptyNode(){
        assertEquals(new Rectangle(0, 0,0 ,0), node.getRectangle());
        assertNull(node.getParent());
        assertEquals(new ArrayList<>(), node.getObjects());
        assertEquals(new ArrayList<>(), node.getChildren());
        assertEquals(0, node.getArea());
        assertEquals(0, node.getPerimeter());
    }

    @Test
    public void testAddObject(){
        node.addObject(gameObject);
        List<DummyGameObject> expected = new ArrayList<>();
        expected.add(gameObject);
        assertEquals(expected, node.getObjects());
        assertEquals(gameObject.getRectangle(), node.getRectangle());
    }
}
