package com.github.entrypointkr.chprotocol.function;

import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CRERangeException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.List;

/**
 * Created by JunHyeong on 2018-09-20
 */
@api
public class PacketRead extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CRECastException.class, CRERangeException.class, CREIllegalArgumentException.class
        };
    }

    @Override
    public Construct exec(Target t, Environment env, List<Mixed> args) throws ConfigRuntimeException {
        PacketWrapper packet = args.size() == 1
                ? PacketWrapper.of(env, t)
                : PacketWrapper.of(args.remove(0), t);
        int index = Static.getInt32(args.remove(0), t);
        try {
            return packet.read(index, t);
        } catch (Exception ex) {
            throw new CREException("Exception was thrown", t, ex);
        }
    }

    @Override
    public String getName() {
        return "packet_read";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                1, 2
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
