package kr.entree.chprotocol.event;

import com.comphenix.protocol.events.PacketContainer;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.events.*;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import lombok.val;

import java.util.Map;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@api
public class CPacketEvent extends AbstractEvent {
    public static final String ENV_PACKET_KEY = "chprotocol.packet.object";

    public static PacketContainer getPacket(Environment environment, Target target) {
        val value = environment.getEnv(GlobalEnv.class).GetCustom(ENV_PACKET_KEY);
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
    public boolean matches(Map<String, Mixed> prefilters, BindableEvent e) throws PrefilterNonMatchException {
        if (!(e instanceof BindablePacketEvent)) {
            return false;
        }
        val packetEvent = (BindablePacketEvent) e;
        packetEvent.getKind().match(prefilters);
        Prefilters.match(prefilters, "player", packetEvent.getPlayer().getName(), Prefilters.PrefilterType.STRING_MATCH);
        return true;
    }

    @Override
    public BindableEvent convert(CArray manualObject, Target t) {
        return null;
    }

    @Override
    public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
        val packetEvent = ((BindablePacketEvent) e);
        val map = evaluate_helper(e);
        val target = Target.UNKNOWN;
        val packet = packetEvent.getPacket(target);
        val player = packetEvent.getPlayer();
        packet.getKind().write(map, target);
        map.put("packet", packet);
        map.put("player", new CString(player.getName(), target));
        return map;
    }

    @Override
    public Driver driver() {
        return Driver.EXTENSION;
    }

    @Override
    public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
        return false;
    }

    @Override
    public Version since() {
        return MSVersion.V3_3_4;
    }

    @Override
    public void preExecution(Environment env, BoundEvent.ActiveEvent activeEvent) {
        val event = activeEvent.getUnderlyingEvent();
        if (event instanceof BindablePacketEvent) {
            env.getEnv(GlobalEnv.class).SetCustom(ENV_PACKET_KEY, ((BindablePacketEvent) event).getInternalPacket());
        }
    }
}
