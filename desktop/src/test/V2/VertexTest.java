package V2;

import com.mygdx.game.V2.Graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VertexTest {


    private Vertex<String> vertex;
    @BeforeEach
    public void initialise(){
        vertex = new Vertex<>("String");
    }

    @Test
    public void testGetValue(){
        assertEquals("String", vertex.getValue());
    }

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class, () -> new Vertex<>(null));
    }

    @Test
    public void testEquals(){
        Vertex<String> vertex1 = new Vertex<>("First");
        Vertex<String> vertex2 = new Vertex<>("First");
        Vertex<String> vertex3 = new Vertex<>("First");
        Vertex<String> differentVertex = new Vertex<>("Second");
        // reflexive
        assertEquals(vertex1, vertex1);
        // symmetrical
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex1);
        // transitive
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex3);
        assertEquals(vertex1, vertex3);
        // not equals
        assertNotEquals(vertex1, differentVertex);
        assertNotEquals(vertex1, new Object());
        assertNotEquals(vertex1, null);
    }

    @Test
    public void testHashCode(){
        Vertex<String> vertex1 = new Vertex<>("First");
        Vertex<String> vertex2 = new Vertex<>("First");
        Vertex<String> vertex3 = new Vertex<>("First");
        Vertex<String> differentVertex = new Vertex<>("Second");
        // reflexive
        assertEquals(vertex1.hashCode(), vertex1.hashCode());
        // symmetrical
        assertEquals(vertex1.hashCode(), vertex2.hashCode());
        assertEquals(vertex2.hashCode(), vertex1.hashCode());
        // transitive
        assertEquals(vertex1.hashCode(), vertex2.hashCode());
        assertEquals(vertex2.hashCode(), vertex3.hashCode());
        assertEquals(vertex1.hashCode(), vertex3.hashCode());
        // not equals
        assertNotEquals(vertex1.hashCode(), differentVertex.hashCode());
    }

    @Test
    public void testToString(){
        String expected = "Vertex[object=String]";
        assertEquals(expected, vertex.toString());
    }
}
