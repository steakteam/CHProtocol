package com.github.entrypointkr.chprotocol.event;

import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-09-20
 */
public interface PrefilterMatcher {
    boolean match(Map<String, Mixed> prefilters) throws PrefilterNonMatchException;
}
