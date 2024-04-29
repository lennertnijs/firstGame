package Navigation;

import com.mygdx.game.V2.Navigation.Graph;
import com.mygdx.game.V2.Navigation.IGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {


    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private IGraph<String> graph;

    @BeforeEach
    public void initialise() {
        str1 = "First";
        str2 = "Second";
        str3 = "Third";
        str4 = "Fourth";
        graph = new Graph<>();
    }

    @Test
    public void testHasVertex() {
        assertFalse(graph.hasVertex(str1));
        graph.addVertex(str1);
        assertTrue(graph.hasVertex(str1));
    }

    @Test
    public void testHasVertexWithNull(){
        assertThrows(NullPointerException.class, () -> graph.hasVertex(null));
    }

    @Test
    public void testAddVertex() {
        assertEquals(new HashSet<>(), graph.getVertices());
        graph.addVertex(str1);
        assertTrue(graph.hasVertex(str1));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getVertices());
        graph.addVertex(str2);
        assertTrue(graph.hasVertex(str2));
        assertEquals(new HashSet<>(Arrays.asList(str1, str2)), graph.getVertices());
    }

    @Test
    public void testAddVertexWithNullObject() {
        assertThrows(NullPointerException.class, () -> graph.addVertex(null));
    }

    @Test
    public void testAddVertexDuplicate() {
        graph.addVertex(str1);
        assertThrows(IllegalStateException.class, () -> graph.addVertex(str1));
    }

    @Test
    public void testAddVertices() {
        assertEquals(new HashSet<>(), graph.getVertices());
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertEquals(new HashSet<>(Arrays.asList(str1, str2, str3)), graph.getVertices());
        assertTrue(graph.hasVertex(str1));
        assertTrue(graph.hasVertex(str2));
        assertTrue(graph.hasVertex(str3));
        assertFalse(graph.hasVertex(str4));
    }

    @Test
    public void testAddVerticesWithNullList() {
        assertThrows(NullPointerException.class, () -> graph.addVertices(null));
    }

    @Test
    public void testAddVerticesWithNullElement() {
        assertThrows(NullPointerException.class, () -> graph.addVertices(Arrays.asList(str1, str2, null)));
    }

    @Test
    public void testAddVerticesWithDuplicates() {
        assertThrows(IllegalStateException.class, () -> graph.addVertices(Arrays.asList(str1, str2, str1)));
    }

    @Test
    public void testVertexCount() {
        assertEquals(0, graph.vertexCount());
        graph.addVertex(str1);
        assertEquals(1, graph.vertexCount());
        assertTrue(graph.hasVertex(str1));
        graph.addVertices(Arrays.asList(str2, str3, str4));
        assertEquals(4, graph.vertexCount());
        assertTrue(graph.hasVertex(str2));
        assertTrue(graph.hasVertex(str3));
        assertTrue(graph.hasVertex(str4));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(graph.isEmpty());
        graph.addVertex(str1);
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testClear() {
        graph.addVertex(str1);
        assertFalse(graph.isEmpty());
        assertTrue(graph.hasVertex(str1));
        graph.clear();
        assertTrue(graph.isEmpty());
        assertFalse(graph.hasVertex(str1));
    }

    @Test
    public void testHasEdge(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertFalse(graph.hasEdge(str1, str2));
        graph.addEdge(str1, str2);
        assertTrue(graph.hasEdge(str1, str2));
        assertFalse(graph.hasEdge(str2, str1));
        graph.addEdge(str2, str1);
        assertTrue(graph.hasEdge(str2, str1));
    }

    @Test
    public void testHasEdgeWithNull(){
        assertThrows(NullPointerException.class, () -> graph.hasEdge(null, str1));
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.hasEdge(str1, null));
    }

    @Test
    public void testHasEdgeNotExists(){
        assertThrows(NoSuchElementException.class, () -> graph.hasEdge(str1, str2));
        graph.addVertex(str1);
        assertThrows(NoSuchElementException.class, () -> graph.hasEdge(str1, str2));
    }

    @Test
    public void testAddEdge() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        assertEquals(0, graph.edgeCount());
        graph.addEdge(str1, str2);
        assertEquals(1, graph.edgeCount());
        graph.addEdge(str2, str3);
        assertEquals(2, graph.edgeCount());
    }

    @Test
    public void testAddEdgeWithNull() {
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.addEdge(null, str1));
        assertThrows(NullPointerException.class, () -> graph.addEdge(str1, null));
    }

    @Test
    public void testAddEdgeVertexDoesntExist() {
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(str1, str2));
        graph.addVertex(str1);
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(str1, str2));
    }

    @Test
    public void testAddEdgeDuplicate() {
        graph.addVertex(str1);
        graph.addVertex(str2);
        graph.addEdge(str1, str2);
        assertThrows(IllegalStateException.class, () -> graph.addEdge(str1, str2));
    }

    @Test
    public void testAddWeightedEdge() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        assertEquals(0, graph.edgeCount());
        graph.addEdge(str1, str2, 15);
        assertEquals(1, graph.edgeCount());
        graph.addEdge(str2, str3, 25);
        assertEquals(2, graph.edgeCount());
    }

    @Test
    public void testAddWeightedEdgeWithNull() {
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.addEdge(null, str1, 15));
        assertThrows(NullPointerException.class, () -> graph.addEdge(str1, null, 15));
    }

    @Test
    public void testAddWeightedEdgeWithNegativeWeight() {
        graph.addVertices(Arrays.asList(str1, str2));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(str1, str2, -1));
    }

    @Test
    public void testAddWeightedEdgeVertexDoesntExist() {
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(str1, str2, 15));
        graph.addVertex(str1);
        assertThrows(NoSuchElementException.class, () -> graph.addEdge(str1, str2, 15));
    }

    @Test
    public void testAddWeightedEdgeDuplicate() {
        graph.addVertex(str1);
        graph.addVertex(str2);
        graph.addEdge(str1, str2, 25);
        assertThrows(IllegalStateException.class, () -> graph.addEdge(str1, str2, 15));
    }

    @Test
    public void testGetWeight(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        graph.addEdge(str1, str2, 5);
        assertEquals(5, graph.getWeight(str1, str2));
    }

    @Test
    public void testGetWeightWithNullObject(){
        assertThrows(NullPointerException.class, () -> graph.getWeight(null, str1));
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.getWeight(str1, null));
    }

    @Test
    public void testGetWeightNotExists(){
        assertThrows(NoSuchElementException.class, () -> graph.getWeight(str1, str2));
        graph.addVertex(str1);
        assertThrows(NoSuchElementException.class, () -> graph.getWeight(str1, str2));
    }

    @Test
    public void testGetDegree() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.addEdge(str1, str2);
        assertEquals(1, graph.getDegree(str1));
        graph.addEdge(str1, str3);
        graph.addEdge(str3, str1);
        assertEquals(2, graph.getDegree(str1));
    }

    @Test
    public void testGetDegreeNotExists(){
        assertThrows(NoSuchElementException.class, () -> graph.getDegree(str1));
    }

    @Test
    public void testGetNeighbors() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.addEdge(str1, str2);
        graph.addEdge(str1, str3);
        graph.addEdge(str3, str1);
        assertEquals(graph.getNeighbors(str1), new HashSet<>(Arrays.asList(str2, str3)));
    }

    @Test
    public void testGetNeighborsNotExists(){
        assertThrows(NoSuchElementException.class, () -> graph.getNeighbors(str1));
    }


    @Test
    public void testAddEdges() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.addEdges(str1, Arrays.asList(str2, str3, str4));
        assertEquals(graph.getNeighbors(str1), new HashSet<>(Arrays.asList(str2, str3, str4)));
    }

    @Test
    public void testAddEdgesWithNullList() {
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.addEdges(str1, null));
    }

    @Test
    public void testAddWeightedEdges() {
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.addEdges(str1, Arrays.asList(str2, str3, str4), Arrays.asList(2, 3, 4));
        assertEquals(graph.getNeighbors(str1), new HashSet<>(Arrays.asList(str2, str3, str4)));
    }

    @Test
    public void testAddWeightedEdgesWithNullList() {
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.addEdges(str1, null, Arrays.asList(1, 2)));
    }

    @Test
    public void testAddWeightedEdgesWithNullWeightsList() {
        graph.addVertices(Arrays.asList(str1, str2));
        assertThrows(NullPointerException.class,
                () -> graph.addEdges(str1, Collections.singletonList(str2), null));
    }

    @Test
    public void testAddWeightedEdgesWithListLengthsDifferent() {
        graph.addVertices(Arrays.asList(str1, str2));
        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdges(str1, Collections.singletonList(str2), Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testAddWeightedEdgesWithNullWeight(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertThrows(NullPointerException.class,
                () -> graph.addEdges(str1, Arrays.asList(str2, str3), Arrays.asList(1, null)));
    }

    @Test
    public void testConnect(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        graph.connect(str1, str2);
        assertEquals(new HashSet<>(Collections.singletonList(str2)), graph.getNeighbors(str1));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str2));
    }

    @Test
    public void testConnectAll(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        graph.connectAll(str1, Arrays.asList(str2, str3));
        assertEquals(new HashSet<>(Arrays.asList(str2, str3)), graph.getNeighbors(str1));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str2));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str3));
    }

    @Test
    public void testConnectAllWithNullList(){
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.connectAll(str1, null));
    }

    @Test
    public void testConnectWeighted(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        graph.connect(str1, str2, 15);
        assertEquals(new HashSet<>(Collections.singletonList(str2)), graph.getNeighbors(str1));
        assertEquals(15, graph.getWeight(str1, str2));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str2));
        assertEquals(15, graph.getWeight(str2, str1));
    }

    @Test
    public void testConnectAllWeighted(){
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.connectAll(str1, Arrays.asList(str2, str3), Arrays.asList(2, 3));
        assertEquals(new HashSet<>(Arrays.asList(str2, str3)), graph.getNeighbors(str1));
        assertEquals(2, graph.getWeight(str1, str2));
        assertEquals(2, graph.getWeight(str2, str1));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str2));
        assertEquals(new HashSet<>(Collections.singletonList(str1)), graph.getNeighbors(str3));
        assertEquals(3, graph.getWeight(str1, str3));
        assertEquals(3, graph.getWeight(str3, str1));
    }

    @Test
    public void testConnectAllWeightedWithNullList(){
        graph.addVertex(str1);
        assertThrows(NullPointerException.class, () -> graph.connectAll(str1, null, Arrays.asList(1,2,3)));
    }

    @Test
    public void testConnectAllWeightedWithNullWeightsList(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertThrows(NullPointerException.class, () -> graph.connectAll(str1, Arrays.asList(str2, str3), null));
    }

    @Test
    public void testConnectAllWeightedWithListLengthsDifferent(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertThrows(IllegalArgumentException.class,
                () -> graph.connectAll(str1, Arrays.asList(str2, str3), Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testConnectAllWeightedWithNullWeight(){
        graph.addVertices(Arrays.asList(str1, str2, str3));
        assertThrows(NullPointerException.class,
                () -> graph.connectAll(str1, Arrays.asList(str2, str3), Arrays.asList(1, null)));
    }

    @Test
    public void testRemoveVertex(){
        graph.addVertex(str1);
        graph.addVertex(str2);
        assertEquals(2, graph.vertexCount());
        graph.removeVertex(str2);
        assertEquals(1, graph.vertexCount());
        assertFalse(graph.hasVertex(str2));
        graph.removeVertex(str3);
        assertEquals(1, graph.vertexCount());
        assertFalse(graph.hasVertex(str2));
    }

    @Test
    public void testRemoveEdge(){
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.connectAll(str1, Arrays.asList(str2, str3, str4));
        assertEquals(6, graph.edgeCount());
        graph.removeEdge(str1, str2);
        assertEquals(5, graph.edgeCount());
        graph.removeEdge(str1, str2);
        assertEquals(5, graph.edgeCount());
    }

    @Test
    public void testRemoveEdgeVertexDoesntExist(){
        assertTrue(graph.isEmpty());
        graph.removeEdge(str1, str2);
        assertTrue(graph.isEmpty());
    }

    @Test
    public void testDisconnect(){
        graph.addVertices(Arrays.asList(str1, str2, str3, str4));
        graph.connectAll(str1, Arrays.asList(str2, str3, str4));
        graph.disconnect(str1, str2);
        assertEquals(4, graph.edgeCount());
        assertFalse(graph.hasEdge(str1, str2));
        assertFalse(graph.hasEdge(str2, str1));
    }

    @Test
    public void testEquals(){
        Graph<String> graph1 = new Graph<>();
        graph1.addVertices(Arrays.asList(str1, str2, str3));
        graph1.connectAll(str1, Arrays.asList(str2, str3));
        Graph<String> graph2 = graph1.copy();
        Graph<String> graph3 = graph1.copy();
        Graph<String> differentGraph = new Graph<>();
        differentGraph.addVertices(Arrays.asList(str1, str2, str3));
        differentGraph.connectAll(str1, Arrays.asList(str2, str3), Arrays.asList(1, 2));
        // reflexive
        assertEquals(graph1, graph1);
        // symmetrical
        assertEquals(graph1, graph2);
        assertEquals(graph2, graph1);
        // transitive
        assertEquals(graph1, graph2);
        assertEquals(graph2, graph3);
        assertEquals(graph1, graph3);
        // not equals
        assertNotEquals(graph1, differentGraph);
        assertNotEquals(graph1, new Object());
        assertNotEquals(graph1, null);
    }

    @Test
    public void testHashCode(){
        Graph<String> graph1 = new Graph<>();
        graph1.addVertices(Arrays.asList(str1, str2, str3));
        graph1.connectAll(str1, Arrays.asList(str2, str3));
        Graph<String> graph2 = graph1.copy();
        Graph<String> graph3 = graph1.copy();
        Graph<String> differentGraph = new Graph<>();
        differentGraph.addVertices(Arrays.asList(str1, str2, str3));
        // reflexive
        assertEquals(graph1.hashCode(), graph2.hashCode());
        // symmetrical
        assertEquals(graph1.hashCode(), graph2.hashCode());
        assertEquals(graph2.hashCode(), graph1.hashCode());
        // transitive
        assertEquals(graph1.hashCode(), graph2.hashCode());
        assertEquals(graph2.hashCode(), graph3.hashCode());
        assertEquals(graph1.hashCode(), graph3.hashCode());
        // not equals
        assertNotEquals(graph1.hashCode(), differentGraph.hashCode());
    }

    @Test
    public void testToString(){
        graph.addVertices(Arrays.asList(str1, str2));
        graph.connectAll(str1, Collections.singletonList(str2));
        String expected = "Navigation[Vertex[Second] -> {Vertex[First]}, Vertex[First] -> {Vertex[Second]}]";
        assertEquals(expected, graph.toString());
    }

}