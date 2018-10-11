package com.github.entrypointkr.chprotocol.converter;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class ChatComponentConverter implements DuplexConverter {
    public static final ChatComponentConverter INSTANCE = new ChatComponentConverter();

    private ChatComponentConverter() {
    }

    @Override
    public Object convert(Construct construct, Class to) {
        if (to == MinecraftReflection.getIChatBaseComponentClass()) {
            String contents = construct.val();
            if (contents.startsWith("{") && contents.endsWith("}")) {
                return WrappedChatComponent.fromJson(contents).getHandle();
            } else {
                return WrappedChatComponent.fromText(contents).getHandle();
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public Construct convert(Object object, Target target) {
        WrappedChatComponent component = WrappedChatComponent.fromHandle(object);
        return new CString(component.getJson(), target);
    }
}
