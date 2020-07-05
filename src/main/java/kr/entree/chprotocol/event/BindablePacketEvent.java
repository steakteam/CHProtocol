package kr.entree.chprotocol.event;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.BindableEvent;
import kr.entree.chprotocol.data.CPacket;
import kr.entree.chprotocol.data.PacketKind;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public interface BindablePacketEvent extends BindableEvent {
    MCPlayer getPlayer();

    PacketKind getKind();

    CPacket getPacket(Target target);
    
    Object getInternalPacket();
}
