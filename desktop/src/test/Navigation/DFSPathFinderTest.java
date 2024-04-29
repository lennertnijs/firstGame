package Navigation;

import com.mygdx.game.V2.Navigation.BFSPathFinder;
import com.mygdx.game.V2.Navigation.DFSPathFinder;
import com.mygdx.game.V2.Navigation.Graph;
import com.mygdx.game.V2.Navigation.IGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DFSPathFinderTest {


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
    private DFSPathFinder<String> dfsPathFinder;

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

        dfsPathFinder = new DFSPathFinder<>(graph);
    }

    @Test
    public void testFindPath1(){
        assertEquals(Arrays.asList(str1, str2, str3, str7), dfsPathFinder.findPath(str1, str7));
    }

    @Test
    public void testFindPath2(){
        assertEquals(Arrays.asList(str1, str2, str3, str7, str8, str12, str16), dfsPathFinder.findPath(str1, str16));
    }

    @Test
    public void testFindPath3(){
        List<List<String>> solutions = new ArrayList<>();
        solutions.add(Arrays.asList(str1, str2, str3, str7, str11, str10, str14, str13));
        solutions.add(Arrays.asList(str1, str2, str3, str7, str11, str10, str9, str13));
        assertTrue(solutions.contains(dfsPathFinder.findPath(str1, str13)));
    }

    @Test
    public void testFindPath4(){
        assertEquals(Arrays.asList(str1, str2, str3, str7, str11, str10, str14, str15), dfsPathFinder.findPath(str1, str15));
    }

    @Test
    public void testFindPath5(){
        assertEquals(Arrays.asList(str15, str14, str10, str11, str7, str8, str12, str16), dfsPathFinder.findPath(str15, str16));
    }

    @Test
    public void testFindPath6(){
        List<List<String>> solutions = new ArrayList<>();
        solutions.add(Arrays.asList(str10, str14, str13));
        solutions.add(Arrays.asList(str10, str9, str13));
        assertTrue(solutions.contains(dfsPathFinder.findPath(str10, str13)));
    }

    @Test
    public void testFindPath7(){
        assertEquals(Arrays.asList(str4, str3, str7, str11, str10, str9), dfsPathFinder.findPath(str4, str9));
    }

    @Test
    public void testFindPath8(){
        assertEquals(Arrays.asList(str4, str3, str7, str6), dfsPathFinder.findPath(str4, str6));
    }

    @Test
    public void testFindPathDoesNotExist(){
        assertThrows(NoSuchElementException.class, () -> dfsPathFinder.findPath(str5, str1));
        assertThrows(NoSuchElementException.class, () -> dfsPathFinder.findPath(str1, str5));
    }

    @Test
    public void testFindPathWithNullStart(){
        assertThrows(NullPointerException.class, () -> dfsPathFinder.findPath(null, str1));
    }

    @Test
    public void testFindPathWithNullEnd(){
        assertThrows(NullPointerException.class, () -> dfsPathFinder.findPath(str1, null));
    }

    @Test
    public void testFindPathStartNotInGraph(){
        assertThrows(NoSuchElementException.class, () -> dfsPathFinder.findPath("random", str1));
    }

    @Test
    public void testFindPathEndNotInGraph(){
        assertThrows(NoSuchElementException.class, () -> dfsPathFinder.findPath(str1, "random"));
    }

    @Test
    public void testGraphIsCopied(){
        graph.addVertex("17");
        assertTrue(graph.hasVertex("17"));
        assertThrows(NoSuchElementException.class, () -> dfsPathFinder.findPath(str1, "17"));
    }
}
