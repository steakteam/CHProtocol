package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.Construct;

/**
 * Created by JunHyeong on 2018-10-11
 */
public interface ConstructConverter {
    Object convert(Construct construct, Class to);
}
