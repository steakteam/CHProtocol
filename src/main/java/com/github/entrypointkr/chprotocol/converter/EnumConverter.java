package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.PureUtilities.Common.ReflectionUtils;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class EnumConverter implements DuplexConverter {
    public static final EnumConverter INSTANCE = new EnumConverter();

    private EnumConverter() {
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object convert(ConstructConverter ctx, Mixed mixed, Class<?> to, Type generic, Target t) {
        if (Enum.class.isAssignableFrom(to)) {
            try {
                return Enum.valueOf((Class) to, mixed.val());
            } catch (IllegalArgumentException ex) {
                Enum[] enums = (Enum[]) ReflectionUtils.invokeMethod(to, null, "values");
                throw new IllegalArgumentException(String.format("%s has elements [%s]",
                        to.getSimpleName(), StringUtils.join(enums, ", ")));
            }
        }
        return null;
    }

    @Override
    public Construct convert(ObjectConverter ctx, Object object, Target target) {
        return new CString(object.toString(), target);
    }
}
