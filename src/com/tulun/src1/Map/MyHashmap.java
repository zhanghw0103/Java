package com.tulun.src1.Map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyHashmap<K, V> {
    private Entry[] table;
    private static final int DEFAULT_SIZE = 16;
    Entry<?, ?>[] EMPTY_TABLE = {};
    private int size;
    float threshold = 0.75f;
    private int ModCount;

    public MyHashmap() {
        table = EMPTY_TABLE;

    }

    public Iterator<Entry<K,V>> Iterator(){
        return new HashIterator();
    }

    class HashIterator implements Iterator<Entry<K, V>> {
        Entry<K, V> next;
        int index;
        Entry<K, V> current;
        int expectedModCount;

        public HashIterator() {
            expectedModCount = ModCount;
            if (size > 0) {
                Entry<K, V>[] t = table;
                while (index < t.length && (next = t[index++]) == null) ;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Entry<K, V> next() {
            if (ModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            Entry<K, V> e = next;
            if (next.next != null) {
                next = e.next;
            } else {
                if (size > 0) {
                    Entry<K, V>[] t = table;
                    while (index < t.length && (next = t[index++]) == null) ;
                }

            }
            current = e;
            return current;
        }

        @Override
        public void remove() {
            K key = current.key;
            MyHashmap.this.remove(key);
            current = null;
        }
    }

    public int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode();
    }

    public int index(int hash) {
        return hash % table.length;
    }


    public void grow(int index) {
        while (table.length == 0) {
            table = Arrays.copyOf(table, DEFAULT_SIZE);
            return;
        }
        if (size >= table.length * threshold && null != table[index]) {
            Entry<K, V>[] newtable = new Entry[table.length * 2];
            for (int i = 0; i < table.length; i++) {
                for (Entry<K, V> x = table[i]; x != null; x = x.next) {
                    int newindex = (hash(x.key)) % newtable.length;
                    if (x.getKey() == null) {
                        x.next = newtable[0];
                        newtable[0] = x;
                    } else {
                        x.next = newtable[newindex];
                        newtable[newindex] = x;
                    }
                }
            }
            table = newtable;
        }

    }

    private Entry<K, V> checkkey(int hash, int index, K key) {
        for (Entry<K, V> x = table[index]; x != null; x = x.next) {
            if (x.hash == hash && (x.key == key || x.key.equals(key))) {
                return x;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (table == EMPTY_TABLE) {
            table = new Entry[DEFAULT_SIZE];
        }
        int hash = hash(key);
        int index = index(hash);
        grow(index);
        Entry<K, V> newentry = new Entry<>(key, value, hash);
        while (key == null) {
            if (checkkey(hash, 0, null) == null) {
                newentry.next = table[0];
                table[0] = newentry;
                size++;
                ModCount++;
                return;
            } else {
                checkkey(hash, 0, null).setValue(newentry.value);
                ModCount++;
                return;
            }
        }
        if (checkkey(hash, index, key) == null) {
            newentry.next = table[index];
            table[index] = newentry;
            size++;
            ModCount++;
        } else {
            checkkey(hash, index, key).setValue(newentry.value);
            ModCount++;
        }
    }

    public boolean remove(K key) {
        if (size == 0) {
            return false;
        }
        int hash = hash(key);
        int index = index(hash);
        int num = 0;
        while (key == null && checkkey(hash, 0, key) != null) {
            for (Entry<K, V> x = table[0]; x != null; x = x.next) {
                if (x.key != null) {
                    num++;
                } else {
                    break;
                }
            }
            if (num == 0) {
                table[0] = table[0].next;
                size--;
                ModCount++;
                return true;
            }
            Entry<K, V> x = table[0];
            for (int i = 0; i < num - 1; i++) {
                x = x.next;
            }
            x.next = x.next.next;
            size--;
            ModCount++;
            return true;
        }
        if (checkkey(hash, index, key) != null) {
            for (Entry<K, V> x = table[index]; x != null; x = x.next) {
                if (x.hash != hash) {
                    num++;
                } else {
                    break;
                }
            }
            if (num == 0) {
                table[index] = table[index].next;
                size--;
                ModCount++;
                return true;
            }
            Entry<K, V> x = table[index];
            for (int i = 0; i < num - 1; i++) {
                x = x.next;
            }
            x.next = x.next.next;
            size--;
            ModCount++;
            return true;
        } else {
            return false;
        }
    }

    public Object get(K key) {
        int hash = hash(key);
        int index = index(hash);
        while (key == null && checkkey(hash, 0, key) != null) {
            return checkkey(hash, 0, key).value;
        }
        if (checkkey(hash, index, key) != null) {
            return checkkey(hash, index, key).value;
        } else {
            return false;
        }
    }

    public boolean replace(K key, V value) {
        int hash = hash(key);
        int index = index(hash);
        while (key == null && checkkey(hash, 0, key) != null) {
            checkkey(hash, 0, key).value = value;
            return true;
        }
        if (checkkey(hash, index, key) != null) {
            checkkey(hash, index, key).value = value;
            return true;
        } else {
            return false;
        }
    }


    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public void setHash(int hash) {
            this.hash = hash;
        }

        int hash;

        public Entry(K key, V values, Entry<K, V> next) {
            this.key = key;
            this.value = values;
            this.next = next;
        }

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V values) {
            this.value = values;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

}
