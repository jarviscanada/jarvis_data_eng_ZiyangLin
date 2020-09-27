package ca.jrvs.practice.dataStructure.set;

import ca.jrvs.practice.dataStructure.tree.JTree;

import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

public class JTreeSet<E> implements JSet<E> {

    private final transient NavigableMap<E,Object> backMap;
    private static final Object PRESENT = new Object();

    public JTreeSet() {
        backMap = new TreeMap<>();
    }

    @Override
    public int size() {
        return backMap.size();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("error: specified object is null.");
        }
        return backMap.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("error: specified object is null.");
        }
        return backMap.put(e, PRESENT)==null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException("error: specified object is null.");
        }
        return backMap.remove(o)==PRESENT;
    }

    @Override
    public void clear() {
        backMap.clear();
    }
}
