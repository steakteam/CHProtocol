package kr.entree.chprotocol.function;

import com.comphenix.protocol.PacketType;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.protocollib.ProtocolLibraries;
import lombok.val;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@api
public class GetAllPackets extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CRECastException.class,
                CREIllegalArgumentException.class
        };
    }

    @Override
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        val packetTypeArray = new CArray(t);
        for (PacketType type : PacketType.values()) {
            packetTypeArray.push(ProtocolLibraries.getPacketKind(type).toCArray(t), t);
        }
        return packetTypeArray;
    }

    @Override
    public String getName() {
        return "all_packets";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{0};
    }

    @Override
    public String docs() {
        return "array {} Returns an array contains all packet types.";
    }
}
