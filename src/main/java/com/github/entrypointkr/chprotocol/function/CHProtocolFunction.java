package com.github.entrypointkr.chprotocol.function;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JunHyeong on 2018-09-20
 */
public abstract class CHProtocolFunction extends AbstractFunction {
    @Override
    public boolean isRestricted() {
        return false;
    }

    @Override
    public Boolean runAsync() {
        return Boolean.FALSE;
    }

    @Override
    public Version since() {
        return MSVersion.V3_3_2;
    }

    @Override
    public final Construct exec(Target target, Environment environment, Mixed... mixed) throws ConfigRuntimeException {
        return exec(target, environment, new LinkedList<>(Arrays.asList(mixed)));
    }

    public abstract Construct exec(Target t, Environment env, List<Mixed> args) throws ConfigRuntimeException;
}
