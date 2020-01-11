package com.github.entrypointkr.chprotocol.function;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.List;

/**
 * Created by JunHyeong on 2018-10-10
 */
@api
public class PacketCreate extends CHProtocolFunction {
    public static PacketType.Sender getSender(String name) {
        switch (name.toUpperCase()) {
            case "IN":
                return PacketType.Sender.CLIENT;
            case "OUT":
                return PacketType.Sender.SERVER;
        }
        throw new IllegalStateException("Unknown sender type: " + name);
    }

    @Override
    public Construct exec(Target t, Environment env, List<Mixed> args) throws ConfigRuntimeException {
        try {
            PacketContainer created = ProtocolLibrary.getProtocolManager().createPacket(PacketType.findCurrent(
                    PacketType.Protocol.valueOf(args.get(0).val().toUpperCase()),
                    getSender(args.get(1).val()),
                    args.get(2).val()
            ));
            return PacketWrapper.of(created, t);
        } catch (Exception ex) {
            throw new CREIllegalArgumentException(ex.toString(), t, ex);
        }
    }

    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREIllegalArgumentException.class
        };
    }

    @Override
    public String getName() {
        return "create_packet";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                3
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
