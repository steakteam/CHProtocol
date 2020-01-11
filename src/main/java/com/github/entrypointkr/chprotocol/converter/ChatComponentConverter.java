package com.github.entrypointkr.chprotocol.converter;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class ChatComponentConverter implements DuplexConverter {
    public static final ChatComponentConverter INSTANCE = new ChatComponentConverter();

    private ChatComponentConverter() {
    }

    @Override
    public Object convert(ConstructConverter ctx, Mixed mixed, Class<?> to, Type generic, Target t) {
        if (to == MinecraftReflection.getIChatBaseComponentClass()) {
            String contents = mixed.val();
            if (contents.startsWith("{") && contents.endsWith("}")) {
                return WrappedChatComponent.fromJson(contents).getHandle();
            } else {
                return WrappedChatComponent.fromText(contents).getHandle();
            }
        }
        return null;
    }

    @Override
    public Construct convert(ObjectConverter ctx, Object object, Target target) {
        WrappedChatComponent component = WrappedChatComponent.fromHandle(object);
        return new CString(component.getJson(), target);
    }
}
