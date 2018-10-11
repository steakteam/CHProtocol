package com.github.entrypointkr.chprotocol.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-10-11
 */
public abstract class CombinedConverter<V, T extends CombinedConverter> {
    protected final ClassMap<List<V>> map = ClassMap.ofHashMap();

    @SuppressWarnings("unchecked")
    public T register(Class key, V value) {
        List<V> list = map.computeIfAbsent(key, k -> new ArrayList<>());
        list.add(value);
        return (T) this;
    }

    protected <R> R startConvert(Class key, Function<V, R> invoker) {
        Iterator<List<V>> iterator = map.findIterator(key);
        while (iterator.hasNext()) {
            for (V value : iterator.next()) {
                R ret = invoker.apply(value);
                if (ret != null) {
                    return ret;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported object type: " + key);
    }
}
