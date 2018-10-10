package com.github.entrypointkr.chprotocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.github.entrypointkr.chprotocol.event.internal.BindablePacketEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.plugin.Plugin;

/**
 * Created by JunHyeong on 2018-09-19
 */
public class PacketProcessor extends PacketAdapter {
    public PacketProcessor(Plugin plugin) {
        super(plugin, PacketType.values());
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        BindablePacketEvent e = new BindablePacketEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "packet_event", e);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        BindablePacketEvent e = new BindablePacketEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "packet_event", e);
    }

    public void register() {
        ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    public void unregister() {
        ProtocolLibrary.getProtocolManager().removePacketListener(this);
    }
}
