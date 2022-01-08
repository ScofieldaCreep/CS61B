public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /*
    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        items = (T[]) new Object[other.items.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = (T) other.items[i];
        }
    }
     */

    public void addFirst(T item) {
        if (items[nextFirst] != null) {
            expandArray();
        }

        items[nextFirst] = item;
        size++;
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
    }

    public void addLast(T item) {
        if (items[nextLast] != null) {
            expandArray();
        }

        items[nextLast] = item;
        size++;
        nextLast++;
        if (nextLast == items.length) {
            nextLast = 0;
        }
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }

        T temp;
        if (nextFirst + 1 == items.length) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        temp = items[nextFirst];
        items[nextFirst] = null;
        size--;

        return temp;
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }

        T temp;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        temp = items[nextLast];
        items[nextLast] = null;
        size--;

        return temp;
    }

    private void expandArray() {
        int newSize = (int) (size * 1.5);
        T[] newArray = (T[]) new Object[newSize];
        T[] temp = iterateArray();
        int newIndex = (int) size / 2;
        nextFirst = newIndex - 1;

        for (int i = 0; i < size; i++) {
            newArray[newIndex] = temp[i];
            newIndex++;
        }

        nextLast = newIndex;
        items = newArray;
    }

    private void shrinkArray() {
        int newSize = (int) items.length / 2;
        T[] newArray = (T[]) new Object[newSize];
        T[] temp = iterateArray();
        int newIndex = (int) newSize / 4;
        nextFirst = newIndex - 1;

        for (int i = 0; i < size; i++) {
            newArray[newIndex] = temp[i];
            newIndex++;
        }

        nextLast = newIndex;
        items = newArray;
    }



    private T[] iterateArray() {
        int first = nextFirst + 1;
        int curSize = this.size;
        int index = 0;
        T[] newArray = (T[]) new Object[curSize];

        while (curSize > 0) {
            if (first >= items.length) {
                first = 0;
            } else if (items[first] != null) {
                newArray[index] = items[first];
                first++;
                index++;
                curSize--;
            } else {
                first++;
            }
        }
        return newArray;
    }

    public void printDeque() {
        T[] temp = iterateArray();
        for (int i = 0; i < size; i++) {
            System.out.print(temp[i].toString() + " ");
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        int first = nextFirst + 1;
        if (index + first < items.length) {
            return items[index + first];
        } else {
            return items[index - items.length + first];
        }
    }
}
