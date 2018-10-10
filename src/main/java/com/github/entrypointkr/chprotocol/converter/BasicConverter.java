package com.github.entrypointkr.chprotocol.converter;

import com.github.entrypointkr.chprotocol.PacketWrapper;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CByteArray;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;

/**
 * Created by JunHyeong on 2018-10-10
 */
public class BasicConverter implements DuplexConverter {
    public static final BasicConverter INSTANCE = new BasicConverter();

    public static void register() {
        PacketWrapper.registerConstructToObject(Construct.class, INSTANCE);
        PacketWrapper.registerObjectToConstruct(Object.class, INSTANCE);
    }

    public static Object smartCasting(Object value, Class type) {
        if (type == Byte.TYPE) {
            return ((Number) value).byteValue();
        } else if (type == Short.TYPE) {
            return ((Number) value).shortValue();
        } else if (type == Integer.TYPE) {
            return ((Number) value).intValue();
        } else if (type == Long.TYPE) {
            return ((Number) value).longValue();
        } else if (type == Float.TYPE) {
            return ((Number) value).floatValue();
        } else if (type == Double.TYPE) {
            return ((Number) value).doubleValue();
        }
        return value;
    }

    private BasicConverter() {
    }

    @Override
    public Object convert(Construct construct, Class type) {
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
        throw new IllegalArgumentException();
    }

    @Override
    public Construct convert(Object object) {
        Target target = Target.UNKNOWN;
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
        throw new IllegalArgumentException();
    }
}
