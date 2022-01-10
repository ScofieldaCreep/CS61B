public class ArrayDeque<T> {
    private T[] items;
    private int prev;
    private int next;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        prev = next = 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
            // Remember to update the num of capacity in the func: resize;
        }
        items[prev] = item;
        prev = (prev - 1 + capacity) % capacity;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        items[next] = item;
        next = (next + 1 + capacity) % capacity;
    }

    public void printDeque() {
        if (prev < next) {
            for (int i = prev; i < next; i++) {
                if (i == next - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.print(items[i] + " ");
            }
        } else if (prev > next) {
            for (int i = prev; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < next; i++) {
                if (i == next - 1) {
                    System.out.println(items[i]);
                    break;
                }
                System.out.print(items[i] + " ");
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[prev];
        prev = (prev + 1) % capacity;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[next];
        next = (next - 1) % capacity;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return removed;
    }


    public T get(int index) {
        if (index < 0 || index >= size() || isEmpty()) {
            return null;
        }
        if (prev < next) {
            return items[prev + index];
        } else if (prev > next) {
            return items [(prev + index) % capacity];
        }
        return null;
    }


    public int size() {
        return (next - prev + capacity) % capacity;
    }

    public boolean isFull() {
        return size() == capacity - 1;
    }

    public boolean isEmpty() {
        return prev == next;
    }

    private boolean isLowUsageRate() {
        return capacity >= 16 && size() / (double) capacity < 0.25;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];

        int size = size();
        if (prev < next) {
            for (int i = prev, j = 0; i < next && j < size; i++, j++) {
                temp[j] = items[i];
            }
        } else if (prev > next) {
            int j = 0;
            for (int i = prev; j < capacity - prev; i++, j++) {
                temp[j] = items[i];
            }
            for (int i = 0; j < size; i++, j++) {
                temp[j] = items[i];
            }
        }
        prev = 0;
        next = size;
        items = temp;
        capacity = newSize;
    }
}
