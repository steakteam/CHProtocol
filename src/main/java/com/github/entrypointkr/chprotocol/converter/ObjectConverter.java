package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

/**
 * Created by JunHyeong on 2018-10-11
 */
public interface ObjectConverter {
    Construct convert(Object object, Target target);
}
