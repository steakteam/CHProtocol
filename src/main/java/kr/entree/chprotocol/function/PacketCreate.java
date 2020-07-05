package kr.entree.chprotocol.function;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.data.CPacket;
import kr.entree.chprotocol.protocollib.ProtocolLibraries;

import static kr.entree.chprotocol.Mixes.upperString;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@api
public class PacketCreate extends CHProtocolFunction {
    @Override
    public Mixed exec(Target target, Environment environment, Mixed... args) throws ConfigRuntimeException {
        return CPacket.create(ProtocolLibraries.createPacket(
                upperString(args[0]),
                upperString(args[1]),
                args[2].val(),
                target
        ), target);
    }

    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{CREIllegalArgumentException.class};
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
