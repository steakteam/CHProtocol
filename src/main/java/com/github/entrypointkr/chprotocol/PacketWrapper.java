package com.github.entrypointkr.chprotocol;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.github.entrypointkr.chprotocol.converter.BasicConverter;
import com.github.entrypointkr.chprotocol.converter.ConstructToObject;
import com.github.entrypointkr.chprotocol.converter.ObjectToConstruct;
import com.github.entrypointkr.chprotocol.event.CHProtocolBaseEvent;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by JunHyeong on 2018-09-20
 */
public class PacketWrapper extends Construct {
    private static final Map<Class, ObjectToConstruct> OBJ2CONST = new HashMap<>();
    private static final Map<Class, ConstructToObject> CONST2OBJ = new HashMap<>();
    public final PacketContainer packet;

    public static void registerObjectToConstruct(Class type, ObjectToConstruct converter) {
        OBJ2CONST.put(type, converter);
    }

    public static void registerConstructToObject(Class type, ConstructToObject converter) {
        CONST2OBJ.put(type, converter);
    }

    public static <T> Optional<T> getFromClassKey(Map<Class, T> map, Class type) {
        T data = map.get(type);
        if (data != null) {
            return Optional.of(data);
        } else {
            Class parent = type.getSuperclass();
            if (parent != null) {
                return getFromClassKey(map, parent);
            }
        }
        return Optional.empty();
    }

    public static Optional<ObjectToConstruct> getObjectToConstruct(Class type) {
        return getFromClassKey(OBJ2CONST, type);
    }

    public static Optional<ConstructToObject> getConstructToObject(Class type) {
        return getFromClassKey(CONST2OBJ, type);
    }

    public static PacketWrapper of(PacketContainer packet, Target t) {
        return new PacketWrapper(packet, t);
    }

    public static PacketWrapper of(Environment env, Target target) {
        Object wrapper = env.getEnv(GlobalEnv.class).GetCustom(CHProtocolBaseEvent.CUSTOM_ID_PACKET);
        if (wrapper instanceof PacketContainer) {
            return of(((PacketContainer) wrapper), target);
        }
        throw new CREException("", target);
    }

    public static PacketWrapper of(Construct construct, Target target) {
        if (construct instanceof PacketWrapper) {
            return ((PacketWrapper) construct);
        } else {
            throw new CREIllegalArgumentException(String.format("Expecting a PacketWrapper, but %s was found.", construct), target);
        }
    }

    private PacketWrapper(PacketContainer packet, Target t) {
        super("PacketData", ConstructType.RESOURCE, t);
        this.packet = packet;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public Construct read(int index) throws Exception {
//        Object object = packet.getModifier().read(index);
//        return OBJ2CONST.convert(object);
//        return getObjectToConstruct(object.getClass())
//                .orElseThrow(() -> new Exception("Unsupported read type: " + object.getClass()))
//                .convert(object);
        return null;
    }

    public void write(int index, Construct construct) throws Exception {
//        StructureModifier<Object> modifier = packet.getModifier();
//        Class type = modifier.getField(index).getType();
//        modifier.write(index, CONST2OBJ.convert(construct, type));
//        Object converted = getConstructToObject(construct.getClass())
//                .orElseThrow(() -> new Exception("Unsupported write type: " + type))
//                .convert(construct, type);
//        modifier.write(index, BasicConverter.smartCasting(converted, type));
    }
}
