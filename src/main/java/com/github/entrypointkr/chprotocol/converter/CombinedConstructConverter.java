package com.github.entrypointkr.chprotocol.converter;

import com.github.entrypointkr.chprotocol.util.CombinedConverter;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class CombinedConstructConverter extends CombinedConverter<ConstructConverter, CombinedConstructConverter> implements ConstructConverter {
    @Override
    public Object convert(ConstructConverter ctx, Construct construct, Class to, Type generic, Target t) {
        Class type = construct.getClass();
        return startConvert(type, converter -> converter.convert(ctx, construct, to, generic, t));
    }
}
