import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;


public class TestArrayDequeGold {
    @Test
    public void testRandom() {
        ArrayDequeSolution<Integer> right = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> own = new StudentArrayDeque<>();

        // test addLast
        for (int i = 0; i < 100; i++) {
            int random = StdRandom.uniform(100);
            right.addLast(random);
            own.addLast(random);
        }

        for (int i = 0; i < 100; i++) {
            int expect = right.get(i);
            int actual = own.get(i);
            assertEquals("Fuck!\nRandom number in addLast() is " + expect + "but yours is " + actual,
                    expect, actual);
        }

        // test addFirst
        for (int i = 0; i < 100; i++) {
            int random = StdRandom.uniform(100);
            right.addFirst(random);
            own.addFirst(random);
        }

        for (int i = 0; i < 100; i++) {
            int expect = right.get(i);
            int actual = own.get(i);
            assertEquals("Fuck!\nRandom number in addFirst() is " + expect + "but yours is " + actual,
                    expect, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(own.removeFirst());
            expectedList.add(right.removeFirst());
        }
        for (int i=0; i<10; i++) {
            int actual = own.get(i);
            int expect = right.get(i);
            assertEquals("Fuck!\nRandom number in addFirst() is " + expect + "but yours is " + actual,
                    expect, actual);
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expect = expectedList.get(i);
            assertEquals("Fuck!\nRandom number in addFirst() is " + expect + "but yours is " + actual,
                    expect, actual);
        }

        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<10; i++) {
            actualList.add(own.removeLast());
            expectedList.add(right.removeLast());
        }

        int actual = own.size();
        int expected = right.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   actual size " + actual
                        + " not equal to " + expected + "!",
                expected, actual);
        for (int i=0; i<10; i++) {
            assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
                            + " not equal to " +  expectedList.get(i) + "!",
                    expectedList.get(i), actualList.get(i));
        }
    }


    @Test
    public void testArrayDeque2() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        int random = StdRandom.uniform(100);
        ads.addFirst(random);
        sad.addFirst(random);
        assertEquals("addFirst("+random+")", ads.get(0), sad.get(0));
        System.out.println("addFirst("+random+")");

        random = StdRandom.uniform(100);
        ads.addLast(random);
        sad.addLast(random);
        assertEquals("addLast("+random+")", ads.get(1), sad.get(1));
        System.out.println("addLast("+random+")");

        int actual = ads.removeFirst();
        int expected = ads.removeFirst();
        assertEquals("removeFirst()", actual, expected);
        System.out.println("removeFirst()");

        actual = ads.removeLast();
        expected = sad.removeLast();
        assertEquals("removeLast()", actual, expected);
        System.out.println("removeLast()");
    }

}

