package kr.entree.chprotocol.protocollib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import kr.entree.chprotocol.event.ProtocolLibPacketEvent;
import lombok.val;
import org.bukkit.plugin.Plugin;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class PacketEventNotifier extends PacketAdapter {
    public PacketEventNotifier(Plugin plugin) {
        super(plugin, PacketType.values());
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        val packetEvent = new ProtocolLibPacketEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "packet_event", packetEvent);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        val packetEvent = new ProtocolLibPacketEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "packet_event", packetEvent);
    }

    public void register() {
        ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    public void unregister() {
        ProtocolLibrary.getProtocolManager().removePacketListener(this);
    }
}
