package kr.entree.chprotocol.data;

import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Map;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@RequiredArgsConstructor
public class PacketKind {
    private final String protocol;
    private final String side;
    private final String name;

    @Override
    public String toString() {
        return String.format("%s_%s_%s", protocol, side, name);
    }

    public CArray toCArray(Target target) {
        val typeInfo = new CArray(target, 3);
        typeInfo.set("protocol", protocol);
        typeInfo.set("side", side);
        typeInfo.set("name", name);
        return typeInfo;
    }

    public void write(Map<String, Mixed> map, Target target) {
        map.put("protocol", new CString(protocol, target));
        map.put("side", new CString(side, target));
        map.put("name", new CString(name, target));
    }

    public void match(Map<String, Mixed> prefilters) throws PrefilterNonMatchException {
        Prefilters.match(prefilters, "protocol", protocol, Prefilters.PrefilterType.STRING_MATCH);
        Prefilters.match(prefilters, "side", side, Prefilters.PrefilterType.STRING_MATCH);
        Prefilters.match(prefilters, "name", name, Prefilters.PrefilterType.STRING_MATCH);
    }
}
