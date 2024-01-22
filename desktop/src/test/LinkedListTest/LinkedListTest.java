package LinkedListTest;

import com.mygdx.game.LinkedList.LinkedList;
import com.mygdx.game.LinkedList.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    @Test
    public void testLinkedList(){
        LinkedList ll = new LinkedList();
        Assertions.assertNull(ll.getHead());
        Assertions.assertEquals(ll.size(), 0);


        Node node1 = new Node();
        ll.add(node1);
        Assertions.assertEquals(ll.size(), 1);
        Assertions.assertEquals(ll.getHead(), node1);
        Assertions.assertNull(ll.getHead().next());
        Assertions.assertNull(ll.getHead().prev());

        Node node2 = new Node();
        ll.add(node2);
        Assertions.assertEquals(ll.size(), 2);
        Assertions.assertEquals(ll.getHead(), node1);
        Assertions.assertEquals(ll.getHead().next(), node2);
        Assertions.assertNull(ll.getHead().prev());
        Assertions.assertNull(ll.getHead().next().next());

        Node node3 = new Node();
        ll.add(node3);
        Assertions.assertEquals(ll.size(), 3);
        Assertions.assertEquals(ll.getHead(), node1);
        Assertions.assertEquals(ll.getHead().next(), node2);
        Assertions.assertNull(ll.getHead().prev());
        Assertions.assertEquals(ll.getHead().next().next(), node3);
        Assertions.assertNull(ll.getHead().next().next().next());


    }

    @Test
    public void testInvalidParameters(){
        LinkedList ll = new LinkedList();
        Assertions.assertThrows(IllegalArgumentException.class, ()->ll.add(null));


    }
}
