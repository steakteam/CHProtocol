package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public interface ConstructConverter {
    Object convert(ConstructConverter ctx, Mixed mixed, Class<?> to, Type generic, Target t);
}
