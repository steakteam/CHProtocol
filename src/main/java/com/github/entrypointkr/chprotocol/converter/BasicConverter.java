package com.github.entrypointkr.chprotocol.converter;

import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CByteArray;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.natives.interfaces.Mixed;

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

    public Object basicConvert(Mixed mixed) {
        if (mixed instanceof CBoolean) {
            return ((CBoolean) mixed).getBoolean();
        } else if (mixed instanceof CByteArray) {
            return ((CByteArray) mixed).asByteArrayCopy();
        } else if (mixed instanceof CDouble) {
            return ((CDouble) mixed).getDouble();
        } else if (mixed instanceof CInt) {
            return ((CInt) mixed).getInt();
        } else if (mixed instanceof CNull) {
            return null;
        } else if (mixed instanceof CString) {
            return mixed.val();
        }
        return null;
    }

    @Override
    public Object convert(ConstructConverter ctx, Mixed mixed, Class<?> to, Type generic, Target t) {
        return smartCasting(basicConvert(mixed), to);
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
