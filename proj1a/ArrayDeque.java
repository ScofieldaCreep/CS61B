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

    private int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    private int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private void resize() {
        if (size == items.length) {
            expand();
        }

        if ((double) size / items.length < 0.25 && items.length >= 16) {
            reduce();
        }
    }

    private void expand() {
        helper(items.length * 2);
    }

    private void reduce() {
        helper(items.length / 2);
    }

    private void helper(int newSize) {
        T[] temp = items;
        int begin = plusOne(nextFront);
        int end = minusOne(nextBack);
        items = (T[]) new Object[newSize];
        nextFront = 0;
        nextBack = 1;

        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            items[nextBack] = (T) temp[i];
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

    private T getFirst() {
        return items[plusOne(nextFront)];
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
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
        nextBack = plusOne(nextBack);
        size++;
    }

    private T getLast() {
        return items[minusOne(nextBack)];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        T removed = getLast();
        nextBack = minusOne(nextBack);
        items[nextBack] = null;
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
        if (index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFront) + index, items.length);
        return items[index];
    }
}
