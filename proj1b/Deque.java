interface Deque<Item> {
    void addFirst(Item item);

    void addLast(Item item);

    Item removeFirst();

    Item removeLast();

    void printDeque();

    int size();

    boolean isEmpty();

    Item get(int index);

    Item getLast();

    Item getFirst();

}
