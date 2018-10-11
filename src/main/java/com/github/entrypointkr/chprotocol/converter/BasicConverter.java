package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CByteArray;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

import java.lang.reflect.Type;

/**
 * Created by JunHyeong on 2018-10-11
 */
public class BasicConverter implements DuplexConverter {
    public static final BasicConverter INSTANCE = new BasicConverter();

    public static Object smartCasting(Object value, Class type) {
        if (value instanceof Number) {
            Number number = ((Number) value);
            if (type == Byte.TYPE) {
                return number.byteValue();
            } else if (type == Short.TYPE) {
                return number.shortValue();
            } else if (type == Integer.TYPE) {
                return number.intValue();
            } else if (type == Long.TYPE) {
                return number.longValue();
            } else if (type == Float.TYPE) {
                return number.floatValue();
            } else if (type == Double.TYPE) {
                return number.doubleValue();
            }
        }
        return value;
    }

    private BasicConverter() {
    }

    public Object basicConvert(Construct construct) {
        if (construct instanceof CBoolean) {
            return ((CBoolean) construct).getBoolean();
        } else if (construct instanceof CByteArray) {
            return ((CByteArray) construct).asByteArrayCopy();
        } else if (construct instanceof CDouble) {
            return ((CDouble) construct).getDouble();
        } else if (construct instanceof CInt) {
            return ((CInt) construct).getInt();
        } else if (construct instanceof CNull) {
            return null;
        } else if (construct instanceof CString) {
            return construct.val();
        }
        return null;
    }

    @Override
    public Object convert(ConstructConverter ctx, Construct construct, Class to, Type generic, Target t) {
        return smartCasting(basicConvert(construct), to);
    }

    @Override
    public Construct convert(ObjectConverter ctx, Object object, Target target) {
        if (object == null) {
            return CNull.NULL;
        } else if (object instanceof String) {
            return new CString(object.toString(), target);
        } else if (object instanceof Number) {
            Number number = ((Number) object);
            return object instanceof Double || object instanceof Float
                    ? new CDouble(number.doubleValue(), target)
                    : new CInt(number.longValue(), target);
        } else if (object instanceof byte[]) {
            return CByteArray.wrap((byte[]) object, target);
        }
        return null;
    }
}
