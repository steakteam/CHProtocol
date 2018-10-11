package com.github.entrypointkr.chprotocol;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.github.entrypointkr.chprotocol.converter.BasicConverter;
import com.github.entrypointkr.chprotocol.converter.ChatComponentConverter;
import com.github.entrypointkr.chprotocol.converter.CollectionConverter;
import com.github.entrypointkr.chprotocol.converter.CombinedConstructConverter;
import com.github.entrypointkr.chprotocol.converter.CombinedObjectConverter;
import com.github.entrypointkr.chprotocol.converter.ConstructConverter;
import com.github.entrypointkr.chprotocol.converter.EnumConverter;
import com.github.entrypointkr.chprotocol.converter.ObjectConverter;
import com.github.entrypointkr.chprotocol.event.CHProtocolBaseEvent;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by JunHyeong on 2018-09-20
 */
public class PacketWrapper extends Construct {
    private static ObjectConverter objectConverter = new CombinedObjectConverter()
            .register(Object.class, BasicConverter.INSTANCE)
            .register(Enum.class, EnumConverter.INSTANCE)
            .register(MinecraftReflection.getChatComponentTextClass(), ChatComponentConverter.INSTANCE)
            .register(Collection.class, CollectionConverter.INSTANCE)
            .register(Map.class, CollectionConverter.INSTANCE);
    private static ConstructConverter constructConverter = new CombinedConstructConverter()
            .register(Construct.class, EnumConverter.INSTANCE)
            .register(Construct.class, BasicConverter.INSTANCE)
            .register(CString.class, ChatComponentConverter.INSTANCE)
            .register(CArray.class, CollectionConverter.INSTANCE);
    public final PacketContainer packet;

    public static ObjectConverter getObjectConverter() {
        return objectConverter;
    }

    public static void setObjectConverter(ObjectConverter objectConverter) {
        PacketWrapper.objectConverter = Objects.requireNonNull(objectConverter);
    }

    public static ConstructConverter getConstructConverter() {
        return constructConverter;
    }

    public static void setConstructConverter(ConstructConverter constructConverter) {
        PacketWrapper.constructConverter = Objects.requireNonNull(constructConverter);
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

    public Construct read(int index, Target target) {
        Object object = packet.getModifier().read(index);
        return objectConverter.convert(objectConverter, object, target);
    }

    public void write(int index, Construct construct, Target target) {
        StructureModifier<Object> modifier = packet.getModifier();
        Field field = modifier.getField(index);
        modifier.write(index, constructConverter.convert(constructConverter, construct, field.getType(), field.getGenericType(), target));
    }
}
