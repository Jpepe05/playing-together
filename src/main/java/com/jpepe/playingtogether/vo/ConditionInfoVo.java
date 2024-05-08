package com.jpepe.playingtogether.vo;

import com.jpepe.playingtogether.enumeration.NeurologicalCondition;

import java.util.Set;

public record ConditionInfoVo(
    Set<NeurologicalCondition> conditions, String otherCondition, boolean hasMedicalReport) {}
