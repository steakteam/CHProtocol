package kr.entree.chprotocol.function;

import com.comphenix.protocol.PacketType;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.external.ProtocolLibraries;
import kr.entree.chprotocol.CPacket;

import static kr.entree.chprotocol.Mixes.upperString;

/**
 * Created by JunHyung Im on 2020-07-05
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
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{CREIllegalArgumentException.class};
    }

    @Override
    public Mixed exec(Target target, Environment environment, Mixed... args) throws ConfigRuntimeException {
        return CPacket.create(ProtocolLibraries.createPacket(
                upperString(args[0]),
                upperString(args[1]),
                upperString(args[2]),
                target
        ), target);
    }

    @Override
    public String getName() {
        return "create_packet";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{3};
    }

    @Override
    public String docs() {
        return "packet {protocol, side, name} Returns a created packet object.";
    }
}
