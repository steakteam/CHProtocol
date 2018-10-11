package com.github.entrypointkr.chprotocol.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class ClassMap<V> implements Map<Class, V> {
    private final Map<Class, V> map;

    public static <V> ClassMap<V> of(Map<Class, V> map) {
        return new ClassMap<>(map);
    }

    public static <V> ClassMap<V> ofHashMap() {
        return of(new HashMap<>());
    }

    private ClassMap(Map<Class, V> map) {
        this.map = map;
    }

    public V get(Class key) {
        V data = map.get(key);
        if (data != null) {
            return data;
        } else {
            Class parent = key.getSuperclass();
            if (parent != null) {
                return get(parent);
            }
        }
        return null;
    }

    public Optional<V> getOptional(Class key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(Class key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends Class, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<Class> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<Class, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super Class, ? super V> action) {
        map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super Class, ? super V, ? extends V> function) {
        map.replaceAll(function);
    }

    @Override
    public V putIfAbsent(Class key, V value) {
        return map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    @Override
    public boolean replace(Class key, V oldValue, V newValue) {
        return map.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(Class key, V value) {
        return map.replace(key, value);
    }

    @Override
    public V computeIfAbsent(Class key, Function<? super Class, ? extends V> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(Class key, BiFunction<? super Class, ? super V, ? extends V> remappingFunction) {
        return map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(Class key, BiFunction<? super Class, ? super V, ? extends V> remappingFunction) {
        return map.compute(key, remappingFunction);
    }

    @Override
    public V merge(Class key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }
}
