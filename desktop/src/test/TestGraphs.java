import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.mygdx.game.Graph.Vertex;
import com.mygdx.game.Graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestGraphs {

    @Test
    public void testGraphs(){
        Vertex v1 = new Vertex("name1",2000, 2000);
        Vertex v2 = new Vertex("name2", 1000,2000);
        Vertex v3 = new Vertex("name3", 1000, 1000);
        Vertex v4 = new Vertex("name4", 1000, 0);
        Vertex v5 = new Vertex("name5", 0, 0);
        Vertex v6 = new Vertex("name6", 0, 1000);
        Graph graph = new Graph();
        graph.addVertex(v1, new ArrayList<>(Collections.singletonList(v2)));
        graph.addVertex(v2, new ArrayList<>(Arrays.asList(v1, v3, v6)));
        graph.addVertex(v3, new ArrayList<>(Arrays.asList(v2, v4)));
        graph.addVertex(v4, new ArrayList<>(Arrays.asList(v3, v5, v6)));
        graph.addVertex(v5, new ArrayList<>(Collections.singletonList(v4)));
        graph.addVertex(v6, new ArrayList<>(Arrays.asList(v2, v4)));
        Assertions.assertEquals(graph.getPath(v1, v6), new ArrayList<>(Arrays.asList(v1, v2, v6)));
        Assertions.assertEquals(graph.getPath(v1, v5), new ArrayList<>(Arrays.asList(v1, v2, v3, v4, v5)));
        Assertions.assertNotEquals(graph.getPath(v1, v5), new ArrayList<>(Arrays.asList(v1, v2, v6, v4, v5)));
        Assertions.assertNull(graph.getPath(new Vertex("name", 25, 25), v1));
        Assertions.assertNull(graph.getPath(v1, new Vertex("name", 25, 25)));
    }
}