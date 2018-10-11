package com.github.entrypointkr.chprotocol.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-10-11
 */
public abstract class CombinedConverter<V, T extends CombinedConverter> {
    protected final ClassMap<Set<V>> map = ClassMap.ofHashMap();

    @SuppressWarnings("unchecked")
    public T register(Class key, V value) {
        Set<V> set = map.computeIfAbsent(key, k -> new HashSet<>());
        set.add(value);
        return (T) this;
    }

    protected <R> R startConvert(Class key, Function<V, R> invoker) {
        Set<V> values = map.getOptional(key).orElse(Collections.emptySet());
        for (V value : values) {
            R ret = invoker.apply(value);
            if (ret != null) {
                return ret;
            }
        }
        throw new IllegalArgumentException("Unsupported object type: " + key);
    }
}
