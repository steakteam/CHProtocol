package com.github.entrypointkr.chprotocol.function;

import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;

import java.util.List;

/**
 * Created by JunHyeong on 2018-09-20
 */
@api
public class PacketWrite extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREIllegalArgumentException.class, CRECastException.class
        };
    }

    @Override
    public Construct exec(Target t, Environment env, List<Construct> args) throws ConfigRuntimeException {
        PacketWrapper packet = args.size() == 1
                ? PacketWrapper.of(env, t)
                : PacketWrapper.of(args.remove(0), t);
        int index = Static.getInt32(args.remove(0), t);
        Construct argument = args.remove(0);
        try {
            packet.write(index, argument);
        } catch (Exception ex) {
            throw new CREException("Exception was thrown, " + ex, t, ex);
        }
        return CVoid.VOID;
    }

    @Override
    public String getName() {
        return "packet_write";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                2, 3
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
