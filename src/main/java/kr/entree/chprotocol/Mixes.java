package kr.entree.chprotocol;

import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.data.CPacket;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class Mixes {
    public static String upperString(Mixed mixed) {
        return mixed.val().toUpperCase();
    }

    public static CPacket packet(Mixed mixed, Target target) {
        if (mixed instanceof CPacket) {
            return ((CPacket) mixed);
        }
        throw new CREIllegalArgumentException("Expected a packet, but received " + mixed, target);
    }
}
