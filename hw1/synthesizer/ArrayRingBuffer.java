// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Arrays;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last = (last + 1 == capacity ? 0 : last + 1);
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T res = rb[first];
        first = (first + 1 == capacity ? 0 : first + 1);
        fillCount -= 1;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            return rb[first];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T> {
        private int position;
        private int count;

        public ArrayBufferIterator() {
            position = first;
            count = 0;
        }

        public boolean hasNext() {
            return count < fillCount();
        }

        public T next() {
            T returnItem = rb[position % capacity()];
            position += 1;
            count += 1;
            return returnItem;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
