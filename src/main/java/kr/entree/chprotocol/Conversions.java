package kr.entree.chprotocol;

import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.laytonsmith.PureUtilities.Common.ReflectionUtils;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.natives.interfaces.Mixed;
import lombok.val;
import org.apache.commons.lang.StringUtils;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Conversions {
    private Conversions() {
        throw new RuntimeException("Util!");
    }

    public static Object convertMixedToObject(Mixed mixed) {
        return Static.getJavaObject(mixed);
    }

    public static Object convertMixedToObject(Mixed mixed, Class<?> type) {
        if (Enum.class.isAssignableFrom(type)) {
            return getEnum(mixed.val(), type);
        } else if (MinecraftReflection.getIChatBaseComponentClass().isAssignableFrom(type)) {
            val contents = mixed.val();
            return contents.startsWith("{") && contents.endsWith("}")
                    ? WrappedChatComponent.fromJson(contents).getHandle()
                    : WrappedChatComponent.fromText(contents).getHandle();
        }
        return convertMixedToObject(mixed);
    }

    public static Object adjustObject(Object object, Class<?> type) {
        return type.equals(Integer.TYPE) && object instanceof Number
                ? ((Number) object).intValue()
                : object;
    }

    public static Mixed convertObjectToMixed(Object object, Target target) {
        if (object instanceof Enum) {
            return new CString(((Enum) object).name(), target);
        }
        return Static.getMSObject(object, target);
    }

    public static Object getEnum(String name, Class enumType) {
        try {
            return Enum.valueOf(enumType, name);
        } catch (IllegalArgumentException ex) {
            val enums = (Enum<?>[]) ReflectionUtils.invokeMethod(enumType, null, "values");
            throw new IllegalArgumentException(String.format("%s has elements [%s]",
                    enumType.getSimpleName(), StringUtils.join(enums, ", ")));
        }
    }
}
