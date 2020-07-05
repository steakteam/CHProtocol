package kr.entree.chprotocol.function;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CRERangeException;
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
public class PacketRead extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CRECastException.class, CRERangeException.class, CREIllegalArgumentException.class
        };
    }

    @Override
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        int index = 0;
        val packet = args.length == 1
                ? CPacket.create(environment, t)
                : Mixes.packet(args[index++], t);
        val readIndex = ArgumentValidation.getInt32(args[index], t);
        return packet.readMixed(readIndex, t);
    }

    @Override
    public String getName() {
        return "packet_read";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{1, 2};
    }

    @Override
    public String docs() {
        return "mixed {[packet], index} Gets the value at the index in the packet.";
    }
}
