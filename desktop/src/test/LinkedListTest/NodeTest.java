package LinkedListTest;

import com.mygdx.game.LinkedList.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NodeTest {

    @Test
    public void testNodeCreation(){
        Node node1 = new Node();
        Assertions.assertFalse(node1.hasNext());
        Assertions.assertFalse(node1.hasPrev());

        Node node2 = new Node();
        node1.setNext(node2);
        node1.setPrev(node2);
        Assertions.assertTrue(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertFalse(node2.hasPrev());
        Assertions.assertEquals(node1.next(), node2);
        Assertions.assertEquals(node1.prev(), node2);

        node2.setNext(node1);
        node2.setPrev(node1);
        Assertions.assertTrue(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertTrue(node2.hasNext());
        Assertions.assertTrue(node2.hasPrev());
        Assertions.assertEquals(node1.next(), node2);
        Assertions.assertEquals(node1.prev(), node2);
        Assertions.assertEquals(node2.next(), node1);
        Assertions.assertEquals(node2.prev(), node1);

        node2.removeNext();
        Assertions.assertTrue(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertTrue(node2.hasPrev());
        Assertions.assertEquals(node1.next(), node2);
        Assertions.assertEquals(node1.prev(), node2);
        Assertions.assertEquals(node2.prev(), node1);

        node2.removePrev();
        Assertions.assertTrue(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertFalse(node2.hasPrev());
        Assertions.assertEquals(node1.next(), node2);
        Assertions.assertEquals(node1.prev(), node2);

        node1.removeNext();
        Assertions.assertFalse(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertFalse(node2.hasPrev());
        Assertions.assertEquals(node1.prev(), node2);

        node1.removePrev();
        Assertions.assertFalse(node1.hasNext());
        Assertions.assertFalse(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertFalse(node2.hasPrev());
    }

    @Test
    public void testInvalidParameters(){
        Node node1 = new Node();
        Assertions.assertThrows(IllegalArgumentException.class, () -> node1.setNext(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> node1.setPrev(null));
    }
}
