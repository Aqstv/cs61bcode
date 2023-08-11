public class ArrayDeque <T> {
    T[] items;
    int nextFirst,nextLast;
    int size;
    int capacity;

    public ArrayDeque() {
        capacity = 8;
        items = (T []) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    public int getFirst() {
        int f = nextFirst + 1;
        if (f == capacity) {
            f = 0;
        }
        return f;
    }
    public int getLast() {
        int f = nextLast - 1;
        if (f == -1) {
            f = capacity - 1;
        }
        return f;
    }
    public void expandSize(int c) {
        T[] n = (T []) new Object[c];
        if (nextLast == nextFirst + 1) {
            System.arraycopy(items ,0 ,n ,0,nextLast);
            System.arraycopy(items ,nextLast ,n ,nextLast + c - capacity,capacity - nextFirst -1);
            items = n;
            nextFirst += (c - capacity);
            capacity = c;
        }
        else {
            System.arraycopy(items ,0 ,n ,0 ,capacity);
            items = n;
            nextLast = capacity;
            nextFirst = c - 1;
            capacity = c;
        }
    }
    public void contractSize(int c) {
        T[] n = (T []) new Object[c];
        int f = getFirst();
        int l = getLast();
        if (f < l) {
            System.arraycopy(items ,f ,n ,1 ,size);
            nextFirst = 0;
            nextLast = size + 1;
            capacity = c;
            items = n;
        }
        else {
            System.arraycopy(items ,f ,n ,1 ,capacity - f);
            System.arraycopy(items ,0 ,n ,capacity - f + 1 ,l + 1);
            nextFirst = 0;
            nextLast = size + 1;
            capacity = c;
            items = n;
        }
    }
    public void updateNextFirst() {
        nextFirst --;
        if (nextFirst < 0) {
            nextFirst = capacity-1;
        }
    }
    public void updateNextLast() {
        nextLast ++;
        if (nextLast > capacity -1) {
            nextLast = 0;
        }
    }
    public void addFirst(T item) {
        if (size == capacity) {
            expandSize(2*capacity);
        }
        size ++;
        items[nextFirst] = item;
        updateNextFirst();
    }
    public void addLast(T item) {
        if (size == capacity) {
            expandSize(2*capacity);
        }
        size ++;
        items[nextLast] = item;
        updateNextLast();
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int st = getFirst();
        for (int i = 1 ;i <= size ;i ++) {
            if (st == capacity) {
                st = 0;
            }
            System.out.print(items[st].toString()+" ");
            st ++;
        }
    }
    public T removeFirst() {
        int f = getFirst();
        T p = items[f];
        nextFirst = f;
        size --;
        double s = size;
        double c = capacity;
        if (capacity >= 16 && s/c < 0.25) {
            contractSize(capacity / 2);
        }
        return p;
    }
    public T removeLast() {
        int l = getLast();
        T p = items[l];
        nextLast = l;
        size --;
        double s = size;
        double c = capacity;
        if (capacity >= 16 && s/c < 0.25) {
            contractSize(capacity / 2);
        }
        return p;
    }
    public T get(int index) {
        int st = getFirst();
        return items[(st + index) % capacity];
    }
}
