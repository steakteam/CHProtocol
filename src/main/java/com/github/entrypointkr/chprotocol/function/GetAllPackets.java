package com.github.entrypointkr.chprotocol.function;

import com.comphenix.protocol.PacketType;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.List;

/**
 * Created by JunHyeong on 2018-10-10
 */
@api
public class GetAllPackets extends CHProtocolFunction {
    @Override
    public Construct exec(Target t, Environment env, List<Mixed> args) throws ConfigRuntimeException {
        CArray parent = new CArray(t);
        for (PacketType type : PacketType.values()) {
            CArray info = new CArray(t, 3);
            info.set("protocol", type.getProtocol().getPacketName().toUpperCase());
            info.set("side", type.getSender().getPacketName().toUpperCase());
            info.set("name", type.name().toUpperCase());
            parent.push(info, t);
        }
        return parent;
    }

    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CRECastException.class,
                CREIllegalArgumentException.class
        };
    }

    @Override
    public String getName() {
        return "all_packets";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                0
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
