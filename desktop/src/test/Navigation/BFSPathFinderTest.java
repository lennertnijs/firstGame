package Navigation;

import game.npc.navigation.BFSPathFinder;
import game.npc.navigation.graph.Graph;
import game.npc.navigation.graph.IGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BFSPathFinderTest {

    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String str6;
    private String str7;
    private String str8;
    private String str9;
    private String str10;
    private String str11;
    private String str12;
    private String str13;
    private String str14;
    private String str15;
    private String str16;
    private IGraph<String> graph;
    private BFSPathFinder<String> bfsPathFinder;

    @BeforeEach
    public void initialise() {
        str1 = "1";
        str2 = "2";
        str3 = "3";
        str4 = "4";
        str5 = "5";
        str6 = "6";
        str7 = "7";
        str8 = "8";
        str9 = "9";
        str10 = "10";
        str11 = "11";
        str12 = "12";
        str13 = "13";
        str14 = "14";
        str15 = "15";
        str16 = "16";

        graph = new Graph<>();
        graph.addVertices(Arrays.asList(str1, str2, str3, str4, str5, str6, str7, str8, str9, str10,
                                        str11, str12, str13, str14, str15, str16));
        graph.connect(str1, str2);
        graph.connect(str2, str3);
        graph.connectAll(str3, Arrays.asList(str4, str7));
        graph.connect(str6, str7);
        graph.connectAll(str7, Arrays.asList(str8, str11));
        graph.connect(str8, str12);
        graph.connectAll(str9, Arrays.asList(str10, str13));
        graph.connectAll(str10, Arrays.asList(str11, str14));
        graph.connect(str12, str16);
        graph.connect(str13, str14);
        graph.connect(str14, str15);

        bfsPathFinder = new BFSPathFinder<>();
    }

    @Test
    public void testFindPath1(){
        assertEquals(Arrays.asList(str1, str2, str3, str7), bfsPathFinder.findPath(str1, str7, graph));
    }

    @Test
    public void testFindPath2(){
        assertEquals(Arrays.asList(str1, str2, str3, str7, str8, str12, str16), bfsPathFinder.findPath(str1, str16, graph));
    }

    @Test
    public void testFindPath3(){
        List<List<String>> solutions = new ArrayList<>();
        solutions.add(Arrays.asList(str1, str2, str3, str7, str11, str10, str14, str13));
        solutions.add(Arrays.asList(str1, str2, str3, str7, str11, str10, str9, str13));
        assertTrue(solutions.contains(bfsPathFinder.findPath(str1, str13, graph)));
    }

    @Test
    public void testFindPath4(){
        assertEquals(Arrays.asList(str1, str2, str3, str7, str11, str10, str14, str15), bfsPathFinder.findPath(str1, str15, graph));
    }

    @Test
    public void testFindPath5(){
        assertEquals(Arrays.asList(str15, str14, str10, str11, str7, str8, str12, str16), bfsPathFinder.findPath(str15, str16, graph));
    }

    @Test
    public void testFindPath6(){
        List<List<String>> solutions = new ArrayList<>();
        solutions.add(Arrays.asList(str10, str14, str13));
        solutions.add(Arrays.asList(str10, str9, str13));
        assertTrue(solutions.contains(bfsPathFinder.findPath(str10, str13, graph)));
    }

    @Test
    public void testFindPath7(){
        assertEquals(Arrays.asList(str4, str3, str7, str11, str10, str9), bfsPathFinder.findPath(str4, str9, graph));
    }

    @Test
    public void testFindPath8(){
        assertEquals(Arrays.asList(str4, str3, str7, str6), bfsPathFinder.findPath(str4, str6, graph));
    }

    @Test
    public void testFindPathDoesNotExist(){
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(str5, str1, graph));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(str1, str5, graph));
    }

    @Test
    public void testFindPathWithNullStart(){
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(null, str1, graph));
    }

    @Test
    public void testFindPathWithNullEnd(){
        assertThrows(NullPointerException.class, () -> bfsPathFinder.findPath(str1, null, graph));
    }

    @Test
    public void testFindPathStartNotInGraph(){
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath("random", str1, graph));
    }

    @Test
    public void testFindPathEndNotInGraph(){
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(str1, "random", graph));
    }

    @Test
    public void testGraphIsCopied(){
        graph.addVertex("17");
        assertTrue(graph.hasVertex("17"));
        assertThrows(NoSuchElementException.class, () -> bfsPathFinder.findPath(str1, "17", graph));
    }
}
