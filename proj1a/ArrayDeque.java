import java.awt.*;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFront;
    private int nextBack;
    private final int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        nextFront = 0;
        nextBack = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    public int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    public int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private void resize() {
        if (size == items.length) {
            expand();
        }

        if (size < items.length / 4 && items.length >= 16) {
            reduce();
        }
    }

    private void expand() {
        helper((int) (items.length * 1.5));
    }

    private void reduce() {
        helper((int) (items.length * 0.5));
    }

    private void helper(int newSize) {
        T[] temp = items;
        int begin = plusOne(nextFront);
        int end = minusOne(nextBack);
        items = (T[]) new Object[newSize];
        nextFront = 0;
        nextBack = 1;

        for (int i = begin; i != end; plusOne(i, temp.length)) {
            items[nextBack] = temp[i];
            nextBack = plusOne(nextBack);
        }
        items[nextBack] = temp[end];
        nextBack = plusOne(nextBack);
    }

    public void addFirst(T item) {
        resize();
        items[nextFront] = item;
        nextFront = minusOne(nextFront);
        size++;
    }

    public T getFirst() {
        return items[plusOne(nextFront)];
    }

    public T removeFirst() {
        resize();
        T removed = getFirst();
        nextFront = plusOne(nextFront);
        items[nextFront] = null;
        size--;
        return removed;
    }

    public void addLast(T item) {
        resize();
        items[nextBack] = item;
        nextBack = plusOne(nextFront);
        size++;
    }

    public T getLast() {
        return items[minusOne(nextFront)];
    }

    public T removeLast() {
        resize();
        T removed = getLast();
        nextBack = minusOne(nextBack);
        items[nextLast] = null;
        size--;
        return removed;
    }

    public void printDeque() {
        for (int i = plusOne(nextFront); i != nextBack; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index > items.length) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFront) + index, items.length);
        return items[index];
    }

}
