package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.Construct;

/**
 * Created by JunHyeong on 2018-09-20
 */
public interface ConstructToObject {
    Object convert(Construct construct, Class type);
}
