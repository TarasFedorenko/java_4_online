package ua.com.alevel;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dictionary<K, V> {

    private static final int MAXIMUM_CAPACITY = 1<<30;

    private Entry<K, V>[] entryTable;

    private int size;

    private int threshold;

    private final float loadFactor;

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] newTable(int n) {
        return (Entry<K, V>[]) new Entry<?, ?>[n];
    }

    public Dictionary() {
        this(16, 0.75f);
    }

    public Dictionary(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public Dictionary(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0 || loadFactor <= 0) {
            throw new IllegalArgumentException("Initial capacity or loadFactor must be more than 0: but now initialCapacity is " + initialCapacity + "loadFactor is" + loadFactor);
        }

        if (initialCapacity > MAXIMUM_CAPACITY) {
            throw new IllegalArgumentException("Initial capacity is too large");
        }

        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity *= 2;
        }

        entryTable = newTable(capacity);
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
    }

    final int hash(Object key) {
        return Math.abs(key.hashCode()) % entryTable.length;
    }

    private Entry<K, V>[] getTable() {
        return entryTable;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V get(Object key) {
        if (key == null) {
            return null;
        }
        int h = hash(key);
        Entry<K, V>[] tab = getTable();
        Entry<K, V> e = tab[h];
        while (e != null) {
            if (e.hash == h && key.equals(e.k)) {
                return e.v;
            }
            e = e.next;
        }
        return null;
    }

    private Entry<K, V> getEntry(Object key) {
        int h = hash(key);
        Entry<K, V>[] tab = getTable();
        Entry<K, V> e = tab[h];
        while (e != null && !(e.hash == h && key.equals(e.k))) {
            e = e.next;
        }
        return e;
    }

    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    public void putAll(Dictionary<? extends K, ? extends V> m) {
        Arrays.stream(m.entryTable).filter(it -> it != null).forEach(entry -> {
            put(entry.getKey(), entry.getValue());
        });
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new RuntimeException("Wrong key: " + key);
        }
        int h = hash(key);
        Entry<K, V>[] tab = getTable();

        for (Entry<K, V> e = tab[h]; e != null; e = e.next) {
            if (h == e.hash && key.equals(e.k)) {
                V oldValue = e.v;
                if (value != oldValue) {
                    e.v = value;
                }
                return oldValue;
            }
        }

        Entry<K, V> e = tab[h];
        tab[h] = new Entry<>(h, key, value, e);

        if (++size >= threshold) {
            resize(tab.length * 2);
        }

        return null;
    }


    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = getTable();
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K, V>[] newTable = newTable(newCapacity);
        transfer(oldTable, newTable);
        entryTable = newTable;

        if (size >= threshold / 2) {
            threshold = (int) (newCapacity * loadFactor);
        } else {
            transfer(newTable, oldTable);
            entryTable = oldTable;
        }
    }

    private void transfer(Entry<K, V>[] src, Entry<K, V>[] dest) {
        for (int i = 0; i < src.length; ++i) {
            Entry<K, V> e = src[i];
            src[i] = null;
            while (e != null) {
                Entry<K, V> next = e.next;
                if (e.k == null) {
                    e.next = null;
                    e.v = null;
                    size--;
                } else {
                    e.next = dest[e.hash];
                    dest[e.hash] = e;
                }
                e = next;
            }
        }
    }

    public V remove(Object key) {
        int h = hash(key);
        Entry<K, V>[] tab = getTable();
        Entry<K, V> prev = tab[h];
        Entry<K, V> e = prev;

        while (e != null) {
            Entry<K, V> next = e.next;
            if (h == e.hash && key.equals(e.k)) {
                size--;
                if (prev == e) {
                    tab[h] = next;
                } else {
                    prev.next = next;
                }
                return e.v;
            }
            prev = e;
            e = next;
        }

        return null;
    }

    public void clear() {
        Entry<K, V>[] entry;
        if ((entry = entryTable) != null && size > 0) {
            size = 0;
            for (int i = 0; i < entry.length; ++i) {
                entry[i] = null;
            }
        }
    }

    public boolean containsValue(Object value) {
        Entry<K, V>[] tab = getTable();

        for (int i = tab.length - 1; i > 0; i--) {
            for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.v)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Set<K> keySet() {
        Set<K> ks = new HashSet<>();
        Entry<K, V>[] table = getTable();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ks.add(table[i].getKey());
            }
        }

        return ks;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();

        Entry<K, V>[] table = getTable();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                entrySet.add(table[i]);
            }
        }

        return entrySet;
    }

    public Collection<V> values() {
        Set<V> values = new HashSet<>();

        Entry<K, V>[] table = getTable();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                values.add(table[i].getValue());
            }
        }

        return values;
    }

    static class Entry<K, V> {
        private K k;
        private V v;
        final int hash;
        private Entry<K, V> next;

        public Entry(int hash, K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
            this.hash = hash;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !this.getClass().getName().equals(o.getClass().getName())) {
                return false;
            }
            Entry<K, V> e = (Entry<K, V>) o;
            K k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                V v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            K k = getKey();
            V v = getValue();

            int result = 1;
            final int prime = 31;
            result = prime * result + ((k == null) ? 0 : k.hashCode());
            result = prime * result + ((v == null) ? 0 : v.hashCode());

            return result;
        }

        @Override
        public String toString() {
            return getKey() + " => " + getValue();
        }
    }

}

