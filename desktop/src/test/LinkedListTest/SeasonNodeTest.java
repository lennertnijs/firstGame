package LinkedListTest;

import com.mygdx.game.Clock.Season;
import com.mygdx.game.Clock.SeasonName;
import com.mygdx.game.Clock.SeasonNode;
import com.mygdx.game.LinkedList.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonNodeTest {
    @Test
    public void testSeasonNodeCreation(){
        SeasonName name1 = SeasonName.DARK;
        Season season1 = Season.builder().seasonName(name1).lengthInDays(28).build();

        SeasonNode node1 = SeasonNode.builder().setSeason(season1).build();
        Assertions.assertEquals(season1, node1.getSeason());
        Assertions.assertFalse(node1.hasNext());
        Assertions.assertFalse(node1.hasPrev());

        SeasonName name2 = SeasonName.LIGHT;
        Season season2 = Season.builder().seasonName(name2).lengthInDays(30).build();

        SeasonNode node2 = SeasonNode.builder().setSeason(season2).build();
        node1.setNext(node2);
        node1.setPrev(node2);
        Assertions.assertTrue(node1.hasNext());
        Assertions.assertTrue(node1.hasPrev());
        Assertions.assertFalse(node2.hasNext());
        Assertions.assertFalse(node2.hasPrev());
        Assertions.assertEquals(node1.next(), node2);
        Assertions.assertEquals(node1.prev(), node2);
        Assertions.assertEquals(season1, node1.getSeason());
        Assertions.assertEquals(season2, node2.getSeason());

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
        Assertions.assertThrows(IllegalArgumentException.class, ()->SeasonNode.builder().setSeason(null).build());
    }

}
