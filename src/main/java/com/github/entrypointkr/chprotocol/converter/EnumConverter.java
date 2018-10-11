package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.PureUtilities.Common.ReflectionUtils;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import org.apache.commons.lang.StringUtils;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class EnumConverter implements DuplexConverter {
    public static final EnumConverter INSTANCE = new EnumConverter();

    private EnumConverter() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Construct construct, Class to) {
        if (Enum.class.isAssignableFrom(to)) {
            try {
                return Enum.valueOf(to, construct.val());
            } catch (IllegalArgumentException ex) {
                Enum[] enums = (Enum[]) ReflectionUtils.invokeMethod(to, null, "values");
                throw new IllegalArgumentException(String.format("%s has elements [%s]",
                        to.getSimpleName(), StringUtils.join(enums, ", ")));
            }
        }
        return null;
    }

    @Override
    public Construct convert(Object object, Target target) {
        return new CString(object.toString(), target);
    }
}
