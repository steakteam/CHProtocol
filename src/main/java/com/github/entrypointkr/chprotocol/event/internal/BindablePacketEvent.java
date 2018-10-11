package com.github.entrypointkr.chprotocol.event.internal;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.github.entrypointkr.chprotocol.event.MapWriter;
import com.github.entrypointkr.chprotocol.event.PacketProvider;
import com.github.entrypointkr.chprotocol.event.PrefilterMatcher;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;

import java.util.Map;
import java.util.Objects;

/**
 * Created by JunHyeong on 2018-09-20
 */
public class BindablePacketEvent implements BindableEvent, PrefilterMatcher, MapWriter, PacketProvider {
    private final PacketEvent event;

    public BindablePacketEvent(PacketEvent event) {
        this.event = event;
    }

    @Override
    public Object _GetObject() {
        return Objects.requireNonNull(event);
    }

    @Override
    public boolean match(Map<String, Construct> prefilters) throws PrefilterNonMatchException {
        PacketType type = event.getPacketType();
        Prefilters.match(prefilters, "protocol", type.getProtocol().getPacketName().toUpperCase(), Prefilters.PrefilterType.STRING_MATCH);
        Prefilters.match(prefilters, "side", type.getSender().getPacketName().toUpperCase(), Prefilters.PrefilterType.STRING_MATCH);
        Prefilters.match(prefilters, "name", type.name().toUpperCase(), Prefilters.PrefilterType.STRING_MATCH);
        return true;
    }

    @Override
    public void write(Map<String, Construct> map) {
        PacketType type = event.getPacketType();
        Target target = Target.UNKNOWN;
        map.put("protocol", new CString(type.getProtocol().getPacketName().toUpperCase(), target));
        map.put("side", new CString(type.getSender().getPacketName().toUpperCase(), target));
        map.put("name", new CString(type.name().toUpperCase(), target));
        map.put("packet", PacketWrapper.of(event.getPacket(), target));
    }

    @Override
    public PacketContainer getPacket() {
        return event.getPacket();
    }
}
