public class LinkedListDeque<Item> implements Deque<Item> {

    // 创建一个基本node单元
    private class TypeNode {
        private Item item;
        private TypeNode prev;
        private TypeNode next;

        TypeNode(Item i, TypeNode prev, TypeNode next) {
            this.prev = prev;
            this.item = i;
            this.next = next;
        }
    }

    private TypeNode sentinel; // 做成circuit，就不用设置两个sentinel
    private int size;

    // Empty List Method
    public LinkedListDeque() {
        sentinel = new TypeNode(null, null, null);
        sentinel.prev = sentinel.next = sentinel;
        size = 0;
    }


    /*
    // Create a copy of another list
    public LinkedListDeque(LinkedListDeque other) {
        this(); // 无需重复书写建立空列表的方法，'this();'即可
        for(int i = 0; i < other.size; i++) {
            this.addLast((T) other.get(i)); // (T)有何意义？
        }
        size = other.size();
    }
    */

    @Override
    public void addFirst(Item item) {
        TypeNode temp = new TypeNode(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size++;
    }

    @Override
    public void addLast(Item item) {
        TypeNode temp = new TypeNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;  // size是全局变量，不用加 'this'
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        TypeNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item.toString() + " ");
            // System.out.print(p.item.toString() + " ");
            p = p.next;
        }
        System.out.println(p.item.toString());  //print if p.next == sentinel
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Item removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size = Math.max(0, size - 1);
        return removed;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }

        Item removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = Math.max(0, size - 1);
        return removed;
    }

    @Override
    public Item get(int index) {
        if (index >= size) {
            return null;
        }

        TypeNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public Item getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        TypeNode p = sentinel.next;
        return helper(index, p);
    }

    private Item helper(int index, TypeNode p) {
        if (index == 0) {
            return p.item;
        } else {
            return helper(--index, p.next);
        }
    }

}
