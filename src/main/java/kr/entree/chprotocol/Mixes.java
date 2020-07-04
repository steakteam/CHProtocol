package kr.entree.chprotocol;

import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import lombok.experimental.UtilityClass;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@UtilityClass
public class Mixes {
    public String upperString(Mixed mixed) {
        return mixed.val().toUpperCase();
    }

    public CPacket packet(Mixed mixed, Target target) {
        if (mixed instanceof CPacket) {
            return ((CPacket) mixed);
        }
        throw new CREIllegalArgumentException("Expected a packet, but received " + mixed, target);
    }
}
