package list;

public interface Link<T> {
    int size();
    boolean isEmpty();
    boolean contains(T value);
    void addHead(T value);
    void addTail(T value);
    void removeHead();
    void removeTail();
    void removeValue(T value);

}
