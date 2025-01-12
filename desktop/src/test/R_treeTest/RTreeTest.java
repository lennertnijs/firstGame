//package R_treeTest;
//
//import r_tree.game.R_tree;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public final class RTreeTest {
//
//    private DummyGameObject object1;
//    private DummyGameObject object2;
//    private DummyGameObject object3;
//    private DummyGameObject object4;
//    private DummyGameObject object5;
//    private DummyGameObject object6;
//    private DummyGameObject object7;
//    private R_tree<DummyGameObject> tree;
//
//    @BeforeEach
//    public void initialise(){
//        object1 = new DummyGameObject(0, 0, 50, 50);
//        object2 = new DummyGameObject(50, 150, 100, 100);
//        object3 = new DummyGameObject(150, 0, 50, 50);
//        object4 = new DummyGameObject(150, 150, 100, 100);
//        object5 = new DummyGameObject(25, 25, 50, 50);
//        object6 = new DummyGameObject(75, 75, 50, 50);
//        object7 = new DummyGameObject(25, 75, 25, 25);
//        tree = new R_tree<>(2);
//    }
//
//    @Test
//    public void testInitialisation(){
//        assertEquals(2, tree.getMaxEntries());
//        assertEquals(1, tree.getMinEntries());
//        assertEquals(1, tree.getDepth());
//        assertEquals(0, tree.getSize());
//    }
//
//    @Test
//    public void testInsert1(){
//        tree.insert(object1);
//        assertEquals(1, tree.getDepth());
//        assertEquals(1, tree.getSize());
//        assertEquals(1, tree.getActualSize());
//        assertEquals(List.of(object1), tree.getRoot().getObjects());
//    }
//
//    @Test
//    public void testInsert2(){
//        tree.insert(object1);
//        tree.insert(object2);
//        assertEquals(1, tree.getDepth());
//        assertEquals(2, tree.getSize());
//        assertEquals(2, tree.getActualSize());
//        assertEquals(List.of(object1, object2), tree.getRoot().getObjects());
//    }
//
//    @Test
//    public void testInsert3(){
//        tree.insert(object1);
//        tree.insert(object2);
//        tree.insert(object3);
//        assertEquals(2, tree.getDepth());
//        assertEquals(3, tree.getSize());
//        assertEquals(3, tree.getActualSize());
//        assertEquals(List.of(object1), tree.getRoot().getChildren().get(0).getObjects());
//        assertEquals(List.of(object3, object2), tree.getRoot().getChildren().get(1).getObjects());
//    }
//
//    @Test
//    public void testInsert4(){
//        tree.insert(object1);
//        tree.insert(object2);
//        tree.insert(object3);
//        tree.insert(object4);
//        assertEquals(2, tree.getDepth());
//        assertEquals(4, tree.getSize());
//        assertEquals(4, tree.getActualSize());
//        assertEquals(List.of(object1, object2), tree.getRoot().getChildren().get(0).getObjects());
//        assertEquals(List.of(object3, object4), tree.getRoot().getChildren().get(1).getObjects());
//    }
//
//    @Test
//    public void testInsert5(){
//        tree.insert(object1);
//        tree.insert(object2);
//        tree.insert(object3);
//        tree.insert(object4);
//        tree.insert(object5);
//        tree.insert(object6);
//        tree.insert(object7);
//        DummyGameObject object8 = new DummyGameObject(50, 50, 10, 10);
//
//        DummyGameObject object9 = new DummyGameObject(500, 500, 10, 10);
//        tree.insert(object8);
//        tree.insert(object9);
//        assertEquals(9, tree.getSize());
//        //assertEquals(9, tree.getActualSize());
//        assertEquals(4, tree.getDepth());
//    }
//
////    @Test
////    public void testInsertMany(){
////        for(int i = 0; i < 1000; i++){
////            int x = (int)(Math.random()*1000);
////            int y = (int)(Math.random()*1000);
////            int w = (int)(Math.random()*25);
////            int h = (int)(Math.random()*25);
////            DummyGameObject object = new DummyGameObject(x, y, w, h);
////            tree.insert(object);
////            System.out.println(tree.getSize());
////            System.out.println("depth:" + tree.getDepth());
////        }
////        assertEquals(1000, tree.getSize());
////        assertEquals(1000, tree.getActualSize());
////    }
//}
