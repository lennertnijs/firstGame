package NPC;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.MovementGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestNavigationData {

    @Test
    public void testConstructor() {
        Position v = Position.builder().x(3000).y( 2000).build();
        Position v1 = Position.builder().x(2000).y( 2000).build();
        Position v2 = Position.builder().x(1000).y( 2000).build();
        Position v3 = Position.builder().x(1000).y( 1000).build();
        Position v4 = Position.builder().x(1000).y( 0).build();
        Position v5 = Position.builder().x(0).y( 0).build();
        Position v6 = Position.builder().x(0).y( 1000).build();
        Position v7 = Position.builder().x(2000).y( 1000).build();
        Position v8 = Position.builder().x(2000).y( 0).build();
        Position v9 = Position.builder().x(5000).y( 0).build();



        HashMap<Position, ArrayList<Position>> network = new HashMap<>();
        network.put(v, new ArrayList<>(Collections.singletonList(v1)));
        network.put(v1, new ArrayList<>(Arrays.asList(v, v2)));
        network.put(v2, new ArrayList<>(Arrays.asList(v1, v3)));

        network.put(v3, new ArrayList<>(Arrays.asList(v2, v4, v6, v7)));
        network.put(v4, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v5, new ArrayList<>(Arrays.asList(v4, v6)));

        network.put(v6, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v7, new ArrayList<>(Arrays.asList(v3, v8)));
        network.put(v8, new ArrayList<>(Collections.singletonList(v7)));
        network.put(v9, new ArrayList<>());

        MovementGraph graph =  MovementGraph.builder()
                .movementGraph(network)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(graph.getMovementGraph(), network)
        );
    }

    @Test
    public void testConstructorInvalid() {
        Position v = Position.builder().x(3000).y( 2000).build();
        Position v1 = Position.builder().x(2000).y( 2000).build();
        Position v2 = Position.builder().x(1000).y( 2000).build();
        Position v3 = Position.builder().x(1000).y( 1000).build();
        Position v4 = Position.builder().x(1000).y( 0).build();
        Position v5 = Position.builder().x(0).y( 0).build();
        Position v6 = Position.builder().x(0).y( 1000).build();
        Position v7 = Position.builder().x(2000).y( 1000).build();
        Position v8 = Position.builder().x(2000).y( 0).build();
        Position v9 = Position.builder().x(5000).y( 5000).build();



        HashMap<Position, ArrayList<Position>> network = new HashMap<>();
        network.put(v, new ArrayList<>(Collections.singletonList(v1)));
        network.put(v1, new ArrayList<>(Arrays.asList(v, v2)));
        network.put(v2, new ArrayList<>(Arrays.asList(v1, v3)));

        network.put(v3, new ArrayList<>(Arrays.asList(v2, v4, v6, v7)));
        network.put(v4, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v5, new ArrayList<>(Arrays.asList(v4, v6)));

        network.put(v6, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v7, new ArrayList<>(Arrays.asList(v3, v8)));
        network.put(v8, new ArrayList<>(Collections.singletonList(v7)));
        network.put(v9, new ArrayList<>());

        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> MovementGraph.builder().movementGraph(null).build()),

                () -> network.put(v9, null),
                ()->Assertions.assertThrows(NullPointerException.class,
                        () -> MovementGraph.builder().movementGraph(network).build()),

                () -> network.put(v9, new ArrayList<>(Collections.singletonList(null))),
                ()->Assertions.assertThrows(NullPointerException.class,
                        () -> MovementGraph.builder().movementGraph(network).build()),

                () -> network.put(null, new ArrayList<>()),
                ()->Assertions.assertThrows(NullPointerException.class,
                        () -> MovementGraph.builder().movementGraph(network).build())
        );
    }

    @Test
    public void testFindPath(){
        Position v = Position.builder().x(3000).y( 2000).build();
        Position v1 = Position.builder().x(2000).y( 2000).build();
        Position v2 = Position.builder().x(1000).y( 2000).build();
        Position v3 = Position.builder().x(1000).y( 1000).build();
        Position v4 = Position.builder().x(1000).y( 0).build();
        Position v5 = Position.builder().x(0).y( 0).build();
        Position v6 = Position.builder().x(0).y( 1000).build();
        Position v7 = Position.builder().x(2000).y( 1000).build();
        Position v8 = Position.builder().x(2000).y( 0).build();
        Position v9 = Position.builder().x(5000).y( 0).build();
        Position v10 = Position.builder().x(5000).y( 5000).build();
        Position v11 = Position.builder().x(7500).y( 5000).build();
        MovementGraph graph = generateMovementGraphBuilder(7500).build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(graph.findPath(v, v6), new ArrayList<>(Arrays.asList(v, v1, v2, v3, v6))),
                () -> Assertions.assertEquals(graph.findPath(v8, v), new ArrayList<>(Arrays.asList(v8, v7, v3, v2, v1, v))),
                () -> Assertions.assertEquals(graph.findPath(v5, v8), new ArrayList<>(Arrays.asList(v5, v4, v3, v7, v8))),
                () -> Assertions.assertEquals(graph.findPath(v5, v5), new ArrayList<>()),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> graph.findPath(v9, v8)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> graph.findPath(v10, v8)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> graph.findPath(v11, v8)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> graph.findPath(v8, v11))
        );
    }

    @Test
    public void testFindPathIllegal(){
        Position v = Position.builder().x(3000).y( 2000).build();
        MovementGraph graph = generateMovementGraphBuilder(7500).build();
        Assertions.assertAll(
                () -> Assertions.assertThrows(NullPointerException.class, () -> graph.findPath(null, v)),
                () -> Assertions.assertThrows(NullPointerException.class, () -> graph.findPath(v, null))
        );
    }

    @Test
    public void testEquals(){
        MovementGraph graph1 = generateMovementGraphBuilder(7500).build();
        MovementGraph graph2 = generateMovementGraphBuilder(50000).build();
        MovementGraph graph3 = generateMovementGraphBuilder(7500).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(graph1, graph3),
                () -> Assertions.assertNotEquals(graph1, graph2),
                () -> Assertions.assertNotEquals(graph1, new ArrayList<>()),
                () -> Assertions.assertEquals(graph1.hashCode(), graph3.hashCode()),
                () -> Assertions.assertNotEquals(graph1.hashCode(), graph2.hashCode())
        );
    }

    private MovementGraph.Builder generateMovementGraphBuilder(int y){
        Position v = Position.builder().x(3000).y( 2000).build();
        Position v1 = Position.builder().x(2000).y( 2000).build();
        Position v2 = Position.builder().x(1000).y( 2000).build();
        Position v3 = Position.builder().x(1000).y( 1000).build();
        Position v4 = Position.builder().x(1000).y( 0).build();
        Position v5 = Position.builder().x(0).y( 0).build();
        Position v6 = Position.builder().x(0).y( 1000).build();
        Position v7 = Position.builder().x(2000).y( 1000).build();
        Position v8 = Position.builder().x(2000).y( 0).build();
        Position v9 = Position.builder().x(5000).y( 5000).build();
        Position v10 = Position.builder().x(5000).y( 5000).build();
        Position v11 = Position.builder().x(7500).y( y).build();


        HashMap<Position, ArrayList<Position>> network = new HashMap<>();
        network.put(v, new ArrayList<>(Collections.singletonList(v1)));
        network.put(v1, new ArrayList<>(Arrays.asList(v, v2)));
        network.put(v2, new ArrayList<>(Arrays.asList(v1, v3)));

        network.put(v3, new ArrayList<>(Arrays.asList(v2, v4, v6, v7)));
        network.put(v4, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v5, new ArrayList<>(Arrays.asList(v4, v6)));

        network.put(v6, new ArrayList<>(Arrays.asList(v5, v3)));
        network.put(v7, new ArrayList<>(Arrays.asList(v3, v8)));
        network.put(v8, new ArrayList<>(Collections.singletonList(v7)));
        network.put(v9, new ArrayList<>());
        network.put(v10, new ArrayList<>(Collections.singletonList(v11)));


        return MovementGraph.builder()
                .movementGraph(network);
    }
}