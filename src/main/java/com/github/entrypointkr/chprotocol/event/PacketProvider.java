package com.github.entrypointkr.chprotocol.event;

import com.comphenix.protocol.events.PacketContainer;

/**
 * Created by JunHyeong on 2018-10-10
 */
public interface PacketProvider {
    PacketContainer getPacket();
}
