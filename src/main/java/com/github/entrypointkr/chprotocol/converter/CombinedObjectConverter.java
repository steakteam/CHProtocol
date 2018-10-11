package com.github.entrypointkr.chprotocol.converter;

import com.github.entrypointkr.chprotocol.util.CombinedConverter;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class CombinedObjectConverter extends CombinedConverter<ObjectConverter, CombinedObjectConverter> implements ObjectConverter {
    @Override
    public Construct convert(ObjectConverter ctx, Object object, Target target) {
        Class type = object.getClass();
        return startConvert(type, converter -> converter.convert(ctx, object, target));
    }
}
