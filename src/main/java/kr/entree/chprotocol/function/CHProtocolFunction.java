package kr.entree.chprotocol.function;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.functions.AbstractFunction;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public abstract class CHProtocolFunction extends AbstractFunction {
    @Override
    public boolean isRestricted() {
        return false;
    }

    @Override
    public Boolean runAsync() {
        return null;
    }

    @Override
    public Version since() {
        return MSVersion.V3_3_4;
    }
}
