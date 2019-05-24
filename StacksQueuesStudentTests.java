import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

/**
 * A set of basic tests to test your Stack and Queue implementations.
 *
 * These tests are NOT exhaustive. Write your own test to ensure code coverage.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private ArrayStack<Integer> arrayStack;
    private ArrayQueue<Integer> arrayQueue;
    private LinkedStack<Integer> linkedStack;
    private LinkedQueue<Integer> linkedQueue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPushResize() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _, _, _]
        arrayStack.push(0);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.push(7);
        arrayStack.push(8);
        arrayStack.push(9);
        arrayStack.push(10);
        arrayStack.push(11);
        arrayStack.push(12);
        arrayStack.push(13);
        arrayStack.push(14);
        arrayStack.push(15);


        assertEquals(16, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[(ArrayStack.INITIAL_CAPACITY) * 2];
        expected[0] = 0;
        expected[1] = 1;
        expected[2] = 2;
        expected[3] = 3;
        expected[4] = 4;
        expected[5] = 5;
        expected[6] = 6;
        expected[7] = 7;
        expected[8] = 8;
        expected[9] = 9;
        expected[10] = 10;
        expected[11] = 11;
        expected[12] = 12;
        expected[13] = 13;
        expected[14] = 14;
        expected[15] = 15;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPushGeneral() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [0, 1, 2, 3, _, _, _, _, _, _, _]
        arrayStack.push(0);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.push(7);


        assertEquals(8, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[(ArrayStack.INITIAL_CAPACITY)];
        expected[0] = 0;
        expected[1] = 1;
        expected[2] = 2;
        expected[3] = 3;
        expected[4] = 4;
        expected[5] = 5;
        expected[6] = 6;
        expected[7] = 7;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayStackPushNull() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());
        arrayStack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayStackPopSizeZero() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());
        arrayStack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        // [34, 29, 48, _, _, _, _, _, _, _, _]
        assertEquals((Integer) 59, arrayStack.pop());

        assertEquals(3, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeek() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals((Integer) 59, arrayStack.peek());
        assertEquals(4, arrayStack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeekNull() {
        arrayStack = new ArrayStack<>();
        assertEquals(null, arrayStack.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPush() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals(4, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedStackPushNull() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());
        linkedStack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedStackPopSizeZero() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());
        linkedStack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPopSizeOne() {
        linkedStack = new LinkedStack<>();
        linkedStack.push(34);
        assertEquals(1, linkedStack.size());
        assertEquals((Integer) 34, linkedStack.pop());
        assertEquals(0, linkedStack.size());
        LinkedNode<Integer> curr = linkedStack.getHead();
        assertEquals(null, curr);

    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPop() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        // 48 -> 29 -> 34
        assertEquals((Integer) 59, linkedStack.pop());

        assertEquals(3, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPeek() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals((Integer) 59, linkedStack.peek());
        assertEquals(4, linkedStack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPeekSizeZero() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());
        assertEquals(null, linkedStack.peek());

    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals(4, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayQueueEnqueueNull() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());
        arrayQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayQueueEnqueueSizeZero() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());
        arrayQueue.dequeue();
    }


    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        // [_, 29, 38, 59, _, _, _, _, _, _, _]
        assertEquals((Integer) 34, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);

        // When queue empties, enqueue should resume as before
        // [_, _, _, _, _, _, _, _, _, _, _]
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();

        // [_, _, _, _, 5, _, _, _, _, _, _]
        arrayQueue.enqueue(5);
        assertEquals(5, backingArray[4]);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueGeneral() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(38);
        arrayQueue.enqueue(59);

        // [_, 29, 38, 59, _, _, _, _, _, _, _]
        assertEquals((Integer) 34, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        // [_, 29, 38, 59, 12, 13, 14, 15, 16, 17, 18]
        arrayQueue.enqueue(12);
        arrayQueue.enqueue(13);
        arrayQueue.enqueue(14);
        arrayQueue.enqueue(15);
        arrayQueue.enqueue(16);
        arrayQueue.enqueue(17);
        arrayQueue.enqueue(18);

        assertEquals(10, arrayQueue.size());

        // [_, _, _, 59, 12, 13, 14, 15, 16, 17, 18]
        assertEquals((Integer) 29, arrayQueue.dequeue());
        assertEquals((Integer) 38, arrayQueue.dequeue());
        assertEquals(8, arrayQueue.size());

        // [1, 2, 3, 59, 12, 13, 14, 15, 16, 17, 18]
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        assertEquals(11, arrayQueue.size());


        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 59;
        expected[4] = 12;
        expected[5] = 13;
        expected[6] = 14;
        expected[7] = 15;
        expected[8] = 16;
        expected[9] = 17;
        expected[10] = 18;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueGeneralResize() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(38);
        arrayQueue.enqueue(59);

        // [_, 29, 38, 59, _, _, _, _, _, _, _]
        assertEquals((Integer) 34, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        // [_, 29, 38, 59, 12, 13, 14, 15, 16, 17, 18]
        arrayQueue.enqueue(12);
        arrayQueue.enqueue(13);
        arrayQueue.enqueue(14);
        arrayQueue.enqueue(15);
        arrayQueue.enqueue(16);
        arrayQueue.enqueue(17);
        arrayQueue.enqueue(18);

        assertEquals(10, arrayQueue.size());

        // [_, _, _, 59, 12, 13, 14, 15, 16, 17, 18]
        assertEquals((Integer) 29, arrayQueue.dequeue());
        assertEquals((Integer) 38, arrayQueue.dequeue());
        assertEquals(8, arrayQueue.size());

        // [1, 2, 3, 59, 12, 13, 14, 15, 16, 17, 18]
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        assertEquals(11, arrayQueue.size());


        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 59;
        expected[4] = 12;
        expected[5] = 13;
        expected[6] = 14;
        expected[7] = 15;
        expected[8] = 16;
        expected[9] = 17;
        expected[10] = 18;

        assertArrayEquals(expected, backingArray);

        arrayQueue.enqueue(4);
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(6);
        assertEquals(14, arrayQueue.size());

        Object[] backingArrayResize = arrayQueue.getBackingArray();
        Object[] expectedResize = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expectedResize[0] = 1;
        expectedResize[1] = 2;
        expectedResize[2] = 3;
        expectedResize[3] = 59;
        expectedResize[4] = 12;
        expectedResize[5] = 13;
        expectedResize[6] = 14;
        expectedResize[7] = 15;
        expectedResize[8] = 16;
        expectedResize[9] = 17;
        expectedResize[10] = 18;
        expectedResize[11] = 4;
        expectedResize[12] = 5;
        expectedResize[13] = 6;

        assertArrayEquals(expectedResize, backingArrayResize);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueuePeek() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals((Integer) 34, arrayQueue.peek());
        assertEquals(4, arrayQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals(4, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedQueueEnqueueNull() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());
        linkedQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedQueueDequeueSizeZero() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());
        linkedQueue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeueSizeOne() {
        linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(34);
        linkedQueue.dequeue();
        assertEquals(0, linkedQueue.size());
        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertEquals(null, curr);
        assertEquals(null, linkedQueue.getTail());

    }


    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        // 29 -> 48 -> 59
        assertEquals((Integer) 34, linkedQueue.dequeue());

        assertEquals(3, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueuePeek() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals((Integer) 34, linkedQueue.peek());
        assertEquals(4, linkedQueue.size());
    }


    @Test(timeout = TIMEOUT)
    public void testLinkedQueuePeekSizeZero() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());
        assertEquals(null, linkedStack.peek());

    }

}