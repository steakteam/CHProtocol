package com.github.entrypointkr.chprotocol.converter;

import com.google.gson.internal.$Gson$Types;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class CollectionConverter implements DuplexConverter {
    public static final CollectionConverter INSTANCE = new CollectionConverter();

    private CollectionConverter() {
    }

    @SuppressWarnings("unchecked")
    private Map<Object, Object> createMap(Class key) {
        if (key == Map.class) {
            return new HashMap<>();
        } else {
            try {
                return (Map<Object, Object>) key.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Collection<Object> createCollection(Class key) {
        if (key == Collection.class || key == List.class) {
            return new ArrayList<>();
        } else if (key == Set.class) {
            return new HashSet<>();
        } else {
            try {
                return (Collection<Object>) key.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public Object convert(ConstructConverter ctx, Construct construct, Class to, Type generic, Target t) {
        Type[] generics = $Gson$Types.getMapKeyAndValueTypes(generic, to);
        Class keyType = (Class) generics[0];
        Class valueType = (Class) generics[1];
        if (construct instanceof CArray) {
            CArray array = ((CArray) construct);
            if (Map.class.isAssignableFrom(to)) {
                Map<Object, Object> map = createMap(to);
                for (Construct key : array.keySet()) {
                    Construct value = array.get(key, t);
                    Object convertedKey = ctx.convert(ctx, key, keyType, keyType.getGenericSuperclass(), t);
                    Object convertedValue = ctx.convert(ctx, value, valueType, valueType.getGenericSuperclass(), t);
                    map.put(convertedKey, convertedValue);
                }
                return map;
            } else if (Collection.class.isAssignableFrom(to)) {
                Collection<Object> collection = createCollection(to);
                for (int i = 0; i < array.size(); i++) {
                    Construct value = array.get(i, t);
                    Object convertedValue = ctx.convert(ctx, value, valueType, valueType.getGenericSuperclass(), t);
                    collection.add(convertedValue);
                }
                return collection;
            }
        }
        return null;
    }

    @Override
    public Construct convert(ObjectConverter ctx, Object object, Target target) {
        if (object instanceof Map) {
            Map<?, ?> map = ((Map<?, ?>) object);
            CArray ret = new CArray(target, map.size());
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Construct elementConstruct = ctx.convert(ctx, entry.getValue(), target);
                ret.set(entry.getKey().toString(), elementConstruct, target);
            }
            return ret;
        } else if (object instanceof Collection) {
            Collection<?> collection = ((Collection) object);
            CArray ret = new CArray(target, collection.size());
            for (Object element : collection) {
                Construct elementConstruct = ctx.convert(ctx, element, target);
                ret.push(elementConstruct, target);
            }
            return ret;
        }
        return null;
    }
}
