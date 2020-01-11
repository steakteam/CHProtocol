package com.github.entrypointkr.chprotocol.function;

import com.comphenix.protocol.events.PacketContainer;
import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-10
 */
@api
public class PacketInfo extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREIllegalArgumentException.class
        };
    }

    @Override
    public Construct exec(Target t, Environment env, List<Mixed> args) throws ConfigRuntimeException {
        PacketContainer packet = args.size() == 0
                ? PacketWrapper.of(env, t).packet
                : PacketWrapper.of(args.remove(0), t).packet;
        CArray ret = new CArray(t);
        for (Field field : packet.getModifier().getFields()) {
            ret.push(new CString(field.getType().getSimpleName(), t), t);
        }
        return ret;
    }

    @Override
    public String getName() {
        return "packet_info";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                0, 1
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
