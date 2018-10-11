package com.github.entrypointkr.chprotocol.converter;

import com.github.entrypointkr.chprotocol.util.CombinedConverter;
import com.laytonsmith.core.constructs.Construct;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class CombinedConstructConverter extends CombinedConverter<ConstructConverter, CombinedConstructConverter> implements ConstructConverter {
    @Override
    public Object convert(Construct construct, Class to) {
        Class type = construct.getClass();
        return startConvert(type, converter -> converter.convert(construct, to));
    }
}
