package kr.entree.chprotocol.function;

import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.CPacket;
import kr.entree.chprotocol.Mixes;
import lombok.val;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class PacketWrite extends CHProtocolFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREIllegalArgumentException.class, CRECastException.class
        };
    }

    @Override
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        int index = 0;
        val packet = args.length == 2
                ? CPacket.create(environment, t)
                : Mixes.packet(args[index++], t);
        val writeIndex = ArgumentValidation.getInt32(args[index++], t);
        val value = args[index];
        packet.writeMixed(writeIndex, value);
        return CVoid.VOID;
    }

    @Override
    public String getName() {
        return "packet_write";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{2, 3};
    }

    @Override
    public String docs() {
        return "void {[packet], index, value} Writes the value into the packet.";
    }
}
