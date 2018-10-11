package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public interface ConstructConverter {
    Object convert(ConstructConverter ctx, Construct construct, Class to, Type generic, Target t);
}
