public class LinkedListDeque<T> {
    private class Node {
        private T t;
        private Node pre;
        private Node next;
        public Node() {
            t = null;
            pre = null;
            next = null;
        }
        public Node(T tt) {
            t = tt;
            pre = null;
            next = null;
        }

    }
    private Node senti;
    private int size;
    public LinkedListDeque() {
        senti = new Node();
        senti.next = senti;
        senti.pre = senti;
        size = 0;
    }
    public void addFirst(T item) {
        size++;
        Node p = new Node(item);
        p.next = senti.next;
        senti.next.pre = p;
        senti.next = p;
        p.pre = senti;
    }
    public void addLast(T item) {
        size++;
        Node p = new Node(item);
        p.pre = senti.pre;
        senti.pre.next = p;
        senti.pre = p;
        p.next = senti;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        Node p = senti.next;
        while (p != senti) {
            System.out.print(p.t.toString() + " ");
            p = p.next;
        }
    }
    public T removeFirst() {
        if (size > 0) {
            T tt = senti.next.t;
            senti.next = senti.next.next;
            senti.next.pre = senti;
            size--;
            return tt;
        }
        return null;
    }
    public T removeLast() {
        if (size > 0) {
            T tt = senti.pre.t;
            senti.pre = senti.pre.pre;
            senti.pre.next = senti;
            size--;
            return tt;
        }
        return null;
    }
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int i = 0;
        Node p = senti.next;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.t;
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(senti.next, index);
    }

    private T getRecursiveHelper(Node p, int id) {
        if (id == 0) {
            return p.t;
        }
        return getRecursiveHelper(p.next, id - 1);
    }
}
