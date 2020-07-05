package kr.entree.chprotocol.function;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.Mixes;
import kr.entree.chprotocol.data.CPacket;
import lombok.val;

/**
 * Created by JunHyung Im on 2020-07-05
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
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        val packet = args.length == 0
                ? CPacket.create(environment, t)
                : Mixes.packet(args[0], t);
        return packet.getFields(t);
    }

    @Override
    public String getName() {
        return "packet_info";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{0, 1};
    }

    @Override
    public String docs() {
        return "array {[packet]} Returns an array contains the information of packet you provided.";
    }
}
