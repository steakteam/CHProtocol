package kr.entree.chprotocol.event;

import com.comphenix.protocol.events.PacketContainer;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.Map;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class CPacketEvent extends AbstractEvent {
    public static final String ENV_PACKET_KEY = "chprotocol.packet.object";

    public static PacketContainer getPacket(Environment environment, Target target) {
        Object value = environment.getEnv(GlobalEnv.class).GetCustom(ENV_PACKET_KEY);
        if (value instanceof PacketContainer) {
            return (PacketContainer) value;
        }
        throw new CREIllegalArgumentException("There's no packet data in the environment.", target);
    }

    @Override
    public String getName() {
        return "packet_event";
    }

    @Override
    public String docs() {
        return "";
    }

    @Override
    public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
        return false;
    }

    @Override
    public BindableEvent convert(CArray manualObject, Target t) {
        return null;
    }

    @Override
    public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
        return null;
    }

    @Override
    public Driver driver() {
        return null;
    }

    @Override
    public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
        return false;
    }

    @Override
    public Version since() {
        return null;
    }
}
