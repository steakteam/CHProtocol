package com.github.entrypointkr.chprotocol.event;

import com.github.entrypointkr.chprotocol.event.internal.BindablePacketEvent;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.natives.interfaces.Mixed;

/**
 * Created by JunHyeong on 2018-09-20
 */
@api
public class CHPacketEvent extends CHProtocolBaseEvent {
    @Override
    public String getName() {
        return "packet_event";
    }

    @Override
    public String docs() {
        return "{}";
    }

    @Override
    public boolean match(BindableEvent bindableEvent) {
        return bindableEvent instanceof BindablePacketEvent;
    }

    @Override
    public boolean modifyEvent(String s, Mixed construct, BindableEvent bindableEvent) {
        return false;
    }
}
