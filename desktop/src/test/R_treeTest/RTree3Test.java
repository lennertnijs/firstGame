package R_treeTest;

import com.mygdx.game.r_tree.GameObject2D;
import com.mygdx.game.r_tree.Node;
import com.mygdx.game.r_tree.R_tree;
import com.mygdx.game.r_tree.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class RTree3Test {

    private DummyGameObject object1;
    private DummyGameObject object2;
    private DummyGameObject object3;
    private DummyGameObject object4;
    private DummyGameObject object5;
    private DummyGameObject object6;
    private DummyGameObject object7;
    private DummyGameObject object8;
    private DummyGameObject object9;
    private DummyGameObject object10;
    private DummyGameObject object11;
    private DummyGameObject object12;
    private R_tree<GameObject2D> tree;

    @BeforeEach
    public void initialise(){
        object1 = new DummyGameObject(0, 0, 50, 50);
        object2 = new DummyGameObject(50, 150, 100, 100);
        object3 = new DummyGameObject(150, 0, 50, 50);
        object4 = new DummyGameObject(150, 150, 100, 100);
        object5 = new DummyGameObject(25, 25, 50, 50);
        object6 = new DummyGameObject(75, 75, 50, 50);
        object7 = new DummyGameObject(25, 75, 25, 25);
        object8 = new DummyGameObject(200, 0, 50, 50);
        object9 = new DummyGameObject(0, 200, 50, 50);
        object10 = new DummyGameObject(0, 150, 25, 150);
        object11 = new DummyGameObject(50, 0, 25, 25);
        object12 = new DummyGameObject(100, 0, 25, 25);
        tree = new R_tree<>(3);
    }

    @Test
    public void testInitialisation(){
        assertEquals(3, tree.getMaxEntries());
        assertEquals(1, tree.getMinEntries());
        assertEquals(1, tree.getDepth());
        assertEquals(0, tree.getSize());
    }

    @Test
    public void testInsert1(){
        tree.insert(object1);
        assertEquals(1, tree.getDepth());
        assertEquals(1, tree.getSize());
        assertEquals(1, tree.getActualSize());
        assertEquals(List.of(object1), tree.getRoot().getObjects());
        assertEquals(new Rectangle(0, 0, 50, 50), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert2(){
        tree.insert(object1);
        tree.insert(object2);
        assertEquals(1, tree.getDepth());
        assertEquals(2, tree.getSize());
        assertEquals(2, tree.getActualSize());
        assertEquals(List.of(object1, object2), tree.getRoot().getObjects());
        assertEquals(new Rectangle(0, 0, 150, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert3(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        assertEquals(1, tree.getDepth());
        assertEquals(3, tree.getSize());
        assertEquals(3, tree.getActualSize());
        assertEquals(List.of(object1, object2, object3), tree.getRoot().getObjects());
        assertEquals(new Rectangle(0, 0, 200, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert4(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        assertEquals(2, tree.getDepth());
        assertEquals(4, tree.getSize());
        assertEquals(4, tree.getActualSize());
        assertEquals(List.of(object1), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3, object2, object4), tree.getRoot().getChildren().get(1).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 50), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(50, 0, 200, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert5(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        assertEquals(2, tree.getDepth());
        assertEquals(5, tree.getSize());
        assertEquals(5, tree.getActualSize());
        assertEquals(List.of(object1), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3), tree.getRoot().getChildren().get(1).getObjects());
        assertEquals(List.of(object5, object4, object2), tree.getRoot().getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 50), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 50, 50), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 225, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert6(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        assertEquals(2, tree.getDepth());
        assertEquals(6, tree.getSize());
        assertEquals(6, tree.getActualSize());
        assertEquals(List.of(object1), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3, object4), tree.getRoot().getChildren().get(1).getObjects());
        assertEquals(List.of(object2, object5, object6), tree.getRoot().getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 50), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert7(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        assertEquals(2, tree.getDepth());
        assertEquals(7, tree.getSize());
        assertEquals(7, tree.getActualSize());
        assertEquals(List.of(object1, object7), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3, object4), tree.getRoot().getChildren().get(1).getObjects());
        assertEquals(List.of(object2, object5, object6), tree.getRoot().getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 100), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert8(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        tree.insert(object8);
        assertEquals(2, tree.getDepth());
        assertEquals(8, tree.getSize());
        assertEquals(8, tree.getActualSize());
        assertEquals(List.of(object1, object7), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3, object4, object8), tree.getRoot().getChildren().get(1).getObjects());
        assertEquals(List.of(object2, object5, object6), tree.getRoot().getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 100), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert9(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        tree.insert(object8);
        tree.insert(object9);
        assertEquals(2, tree.getDepth());
        assertEquals(9, tree.getSize());
        assertEquals(9, tree.getActualSize());
        assertEquals(List.of(object1, object7, object9), tree.getRoot().getChildren().get(0).getObjects());
        assertEquals(List.of(object3, object4, object8), tree.getRoot().getChildren().get(1).getObjects());
        assertEquals(List.of(object2, object5, object6), tree.getRoot().getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 250), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 250), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert10(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        tree.insert(object8);
        tree.insert(object9);
        tree.insert(object10);
        assertEquals(3, tree.getDepth());
        assertEquals(10, tree.getSize());
        assertEquals(10, tree.getActualSize());

        Node<GameObject2D> child0 = tree.getRoot().getChildren().get(0);
        Node<GameObject2D> child1 = tree.getRoot().getChildren().get(1);

        assertEquals(List.of(object1), child0.getChildren().get(0).getObjects());
        assertEquals(List.of(object7, object10, object9), child1.getChildren().get(0).getObjects());
        assertEquals(List.of(object2, object5, object6), child1.getChildren().get(1).getObjects());
        assertEquals(List.of(object3, object4, object8), child1.getChildren().get(2).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 250), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 300), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert11(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        tree.insert(object8);
        tree.insert(object9);
        tree.insert(object10);
        tree.insert(object11); // removes a few elements for some reason, presumably from a reinsert
        assertEquals(3, tree.getDepth());
        assertEquals(11, tree.getSize());
        assertEquals(11, tree.getActualSize());

        Node<GameObject2D> child0 = tree.getRoot().getChildren().get(0);
        Node<GameObject2D> child1 = tree.getRoot().getChildren().get(1);
        Node<GameObject2D> child2 = tree.getRoot().getChildren().get(2);

        assertEquals(List.of(object1), child0.getChildren().get(0).getObjects());

        assertEquals(List.of(object11), child1.getChildren().get(0).getObjects());

        assertEquals(List.of(object7, object10, object9), child2.getChildren().get(2).getObjects());
        assertEquals(List.of(object2, object5, object6), child2.getChildren().get(1).getObjects());
        assertEquals(List.of(object3, object4, object8), child2.getChildren().get(0).getObjects());

        assertEquals(new Rectangle(0, 0, 50, 250), tree.getRoot().getChildren().get(0).getRectangle());
        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 300), tree.getRoot().getRectangle());
    }

    @Test
    public void testInsert12(){
        tree.insert(object1);
        tree.insert(object2);
        tree.insert(object3);
        tree.insert(object4);
        tree.insert(object5);
        tree.insert(object6);
        tree.insert(object7);
        tree.insert(object8);
        tree.insert(object9);
        tree.insert(object10);
        tree.insert(object11);
        tree.insert(object12);
        assertEquals(3, tree.getDepth()); // first moment the depth is changed to 5 instead of 3
        assertEquals(11, tree.getSize());
        assertEquals(11, tree.getActualSize());

        Node<GameObject2D> child0 = tree.getRoot().getChildren().get(0);
        Node<GameObject2D> child1 = tree.getRoot().getChildren().get(1);
        Node<GameObject2D> child2 = tree.getRoot().getChildren().get(2);

        assertEquals(List.of(object1), child0.getChildren().get(0).getObjects());

        assertEquals(List.of(object11), child1.getChildren().get(0).getObjects());

        assertEquals(List.of(object7, object10, object9), child2.getChildren().get(2).getObjects());
        assertEquals(List.of(object2, object5, object6), child2.getChildren().get(1).getObjects());
        assertEquals(List.of(object3, object4, object8), child2.getChildren().get(0).getObjects());

//        assertEquals(new Rectangle(0, 0, 50, 250), tree.getRoot().getChildren().get(0).getRectangle());
//        assertEquals(new Rectangle(150, 0, 100, 250), tree.getRoot().getChildren().get(1).getRectangle());
//        assertEquals(new Rectangle(25, 25, 125, 225), tree.getRoot().getChildren().get(2).getRectangle());

        assertEquals(new Rectangle(0, 0, 250, 300), tree.getRoot().getRectangle());
    }


//    @Test
//    public void testInsertMany(){
//        for(int i = 0; i < 1000; i++){
//            int x = (int)(Math.random()*1000);
//            int y = (int)(Math.random()*1000);
//            int w = (int)(Math.random()*25);
//            int h = (int)(Math.random()*25);
//            DummyGameObject object = new DummyGameObject(x, y, w, h);
//            System.out.println(i);
//            tree.insert(object);
//        }
//        assertEquals(1000, tree.getSize());
//        assertEquals(1000, tree.getActualSize());
//        assertEquals(5, tree.getDepth());
//    }
}
