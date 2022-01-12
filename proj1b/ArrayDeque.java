public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFront;
    private int nextBack;
    private final int capacity = 8;

    public ArrayDeque() {
        items = (Item[]) new Object[capacity];
        size = 0;
        nextFront = 0;
        nextBack = 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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
        Item[] temp = items;
        int begin = plusOne(nextFront);
        int end = minusOne(nextBack);
        items = (Item[]) new Object[newSize];
        nextFront = 0;
        nextBack = 1;

        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            items[nextBack] = (Item) temp[i];
            nextBack = plusOne(nextBack);
        }
        items[nextBack] = temp[end];
        nextBack = plusOne(nextBack);
    }

    @Override
    public void addFirst(Item item) {
        resize();
        items[nextFront] = item;
        nextFront = minusOne(nextFront);
        size++;
    }

    public Item getFirst() {
        return items[plusOne(nextFront)];
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        resize();
        Item removed = getFirst();
        nextFront = plusOne(nextFront);
        items[nextFront] = null;
        size--;
        return removed;
    }

    @Override
    public void addLast(Item item) {
        resize();
        items[nextBack] = item;
        nextBack = plusOne(nextBack);
        size++;
    }

    public Item getLast() {
        return items[minusOne(nextBack)];
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        Item removed = getLast();
        nextBack = minusOne(nextBack);
        items[nextBack] = null;
        size--;
        return removed;
    }

    @Override
    public void printDeque() {
        for (int i = plusOne(nextFront); i != nextBack; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFront) + index, items.length);
        return items[index];
    }
}


