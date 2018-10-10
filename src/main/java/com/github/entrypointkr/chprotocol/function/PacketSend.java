package com.github.entrypointkr.chprotocol.function;

import com.comphenix.protocol.ProtocolLibrary;
import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.exceptions.CRE.CREPlayerOfflineException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-10
 */
@api
public class PacketSend extends CHProtocolFunction {
    @Override
    public Construct exec(Target t, Environment env, List<Construct> args) throws ConfigRuntimeException {
        MCPlayer player = args.size() > 0
                ? Static.GetPlayer(args.remove(0), t)
                : Static.getPlayer(env, t);
        PacketWrapper packet = args.size() > 0
                ? PacketWrapper.of(args.remove(0), t)
                : PacketWrapper.of(env, t);
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(
                    ((BukkitMCPlayer) player)._Player(),
                    packet.packet
            );
        } catch (InvocationTargetException e) {
            throw new CREException(e.getMessage(), t, e);
        }
        return CVoid.VOID;
    }

    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREPlayerOfflineException.class,
                IllegalStateException.class
        };
    }

    @Override
    public String getName() {
        return "send_packet";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{
                0, 1, 2
        };
    }

    @Override
    public String docs() {
        return "";
    }
}
