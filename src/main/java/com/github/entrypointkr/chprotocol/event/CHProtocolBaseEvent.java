package com.github.entrypointkr.chprotocol.event;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.environments.GlobalEnv;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.BoundEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunHyeong on 2018-09-20
 */
public abstract class CHProtocolBaseEvent extends AbstractEvent {
    public static final String CUSTOM_ID_PACKET = "CHProtocolPacket";

    public abstract boolean match(BindableEvent bindableEvent);

    @Override
    public boolean matches(Map<String, Construct> map, BindableEvent bindableEvent) throws PrefilterNonMatchException {
        if (match(bindableEvent)) {
            if (bindableEvent instanceof PrefilterMatcher) {
                PrefilterMatcher matcher = ((PrefilterMatcher) bindableEvent);
                return matcher.match(map);
            }
        }
        return false;
    }

    @Override
    public Map<String, Construct> evaluate(BindableEvent bindableEvent) throws EventException {
        if (bindableEvent instanceof MapWriter) {
            MapWriter writer = ((MapWriter) bindableEvent);
            Map<String, Construct> ret = new HashMap<>();
            writer.write(ret);
            return ret;
        }
        throw new EventException("Unknown event.");
    }

    @Override
    public BindableEvent convert(CArray cArray, Target target) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public Driver driver() {
        return Driver.EXTENSION;
    }

    @Override
    public Version since() {
        return CHVersion.V3_3_2;
    }

    @Override
    public void preExecution(Environment env, BoundEvent.ActiveEvent activeEvent) {
        BindableEvent bindableEvent = activeEvent.getUnderlyingEvent();
        if (bindableEvent instanceof PacketProvider) {
            env.getEnv(GlobalEnv.class).SetCustom(CUSTOM_ID_PACKET, ((PacketProvider) bindableEvent).getPacket());
        }
    }
}
