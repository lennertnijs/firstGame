package NPC;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.MovementGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class TestGraphs {

    @Test
    public void testGraphs(){
        Position v = Position.builder().x(3000).y( 2000).build();
        Position v1 = Position.builder().x(2000).y( 2000).build();
        Position v2 = Position.builder().x(1000).y( 2000).build();
        Position v3 = Position.builder().x(1000).y( 1000).build();
        Position v4 = Position.builder().x(1000).y( 0).build();
        Position v5 = Position.builder().x(0).y( 0).build();
        Position v6 = Position.builder().x(0).y( 1000).build();
        Position v7 = Position.builder().x(2000).y( 1000).build();
        Position v8 = Position.builder().x(2000).y( 0).build();



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

        MovementGraph graph = MovementGraph.builder()
                .movementGraph(network)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(graph.findPath(v, v6), new ArrayList<>(Arrays.asList(v, v1, v2, v3, v6))),
                () -> Assertions.assertEquals(graph.findPath(v8, v), new ArrayList<>(Arrays.asList(v8, v7, v3, v2, v1, v))),
                () -> Assertions.assertEquals(graph.findPath(v5, v8), new ArrayList<>(Arrays.asList(v5, v4, v3, v7, v8)))
        );
    }
}