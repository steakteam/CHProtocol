package com.github.entrypointkr.chprotocol.event;

import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-09-20
 */
public interface MapWriter {
    void write(Map<String, Mixed> map);
}
