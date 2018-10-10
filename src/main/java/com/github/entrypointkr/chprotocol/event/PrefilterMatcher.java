package com.github.entrypointkr.chprotocol.event;

import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-09-20
 */
public interface PrefilterMatcher {
    boolean match(Map<String, Construct> prefilters) throws PrefilterNonMatchException;
}
