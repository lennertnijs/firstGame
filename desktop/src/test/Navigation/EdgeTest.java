package Navigation;

import com.mygdx.game.npc.navigation.graph.Edge;
import com.mygdx.game.npc.navigation.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {


    private Vertex<String> start;
    private Vertex<String> end;
    private int weight;
    private Edge<String> edge;
    @BeforeEach
    public void initialise(){
        start = new Vertex<>("Start");
        end = new Vertex<>("End");
        weight = 10;
        edge = new Edge<>(start, end, 10);
    }

    @Test
    public void testConstructorWithNullStart(){
        assertThrows(NullPointerException.class, () -> new Edge<>(null, end, weight));
    }

    @Test
    public void testConstructorWithNullEnd(){
        assertThrows(NullPointerException.class, () -> new Edge<>(start, null, weight));
    }

    @Test
    public void testConstructorWithNegativeWeight(){
        assertThrows(IllegalArgumentException.class, () -> new Edge<>(start, end, -1));
    }

    @Test
    public void testGetStart(){
        assertEquals(start, edge.getStart());
    }

    @Test
    public void testGetEnd(){
        assertEquals(end, edge.getEnd());
    }

    @Test
    public void testGetWeight(){
        assertEquals(weight, edge.getWeight());
    }

    @Test
    public void testHasSameVertices(){
        Edge<String> edge1 = new Edge<>(start, end, weight);
        Edge<String> edge2 = new Edge<>(start, end, weight);
        Edge<String> differentEdge = new Edge<>(new Vertex<>("Different"), end, weight);
        assertTrue(edge1.hasSameVertices(edge2));
        assertFalse(edge1.hasSameVertices(differentEdge));
    }

    @Test
    public void testHasSameVerticesWithNull(){
        assertThrows(NullPointerException.class, () -> edge.hasSameVertices(null));
    }

    @Test
    public void testEquals(){
        Edge<String> edge1 = new Edge<>(start, end, weight);
        Edge<String> edge2 = new Edge<>(start, end, weight);
        Edge<String> edge3 = new Edge<>(start, end, weight);
        Edge<String> differentEdge = new Edge<>(new Vertex<>("Different"), end, weight);
        // reflexive
        assertEquals(edge1, edge1);
        // symmetrical
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge1);
        // transitive
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge3);
        assertEquals(edge1, edge3);
        //not equals
        assertNotEquals(edge1, differentEdge);
        assertNotEquals(edge1, new Object());
        assertNotEquals(edge1, null);
    }

    @Test
    public void testHashCode(){
        Edge<String> edge1 = new Edge<>(start, end, weight);
        Edge<String> edge2 = new Edge<>(start, end, weight);
        Edge<String> edge3 = new Edge<>(start, end, weight);
        Edge<String> differentEdge = new Edge<>(new Vertex<>("Different"), end, weight);
        // reflexive
        assertEquals(edge1.hashCode(), edge1.hashCode());
        // symmetrical
        assertEquals(edge1.hashCode(), edge2.hashCode());
        assertEquals(edge2.hashCode(), edge1.hashCode());
        // transitive
        assertEquals(edge1.hashCode(), edge2.hashCode());
        assertEquals(edge2.hashCode(), edge3.hashCode());
        assertEquals(edge1.hashCode(), edge3.hashCode());
        //not equals
        assertNotEquals(edge1.hashCode(), differentEdge.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Edge[start=Vertex[object=Start], end=Vertex[object=End], weight=10]";
        assertEquals(expected, edge.toString());
    }
}
