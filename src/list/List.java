package list;

import com.sun.java.accessibility.util.EventID;

public interface List<T> {
    void addhead(T value);
    void addlast(T value);
    void deletehead();
    void deletelast();
    void deleteValue(T value);
    void change(T value1, T value2);
    boolean contains(T value);
    void size();

}
