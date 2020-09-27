package ca.jrvs.practice.dataStructure.set;

import java.util.HashMap;

public class JHashSet<E> implements JSet<E> {

    private final transient HashMap<E,Object> backMap;
    private static final Object PRESENT = new Object();

    public JHashSet() {
        backMap = new HashMap<>();
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
