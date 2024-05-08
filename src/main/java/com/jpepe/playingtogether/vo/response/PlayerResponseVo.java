package com.jpepe.playingtogether.vo.response;

import com.jpepe.playingtogether.vo.ConditionInfoVo;
import com.jpepe.playingtogether.vo.EducationInfoVo;

public record PlayerResponseVo(
    String name,
    boolean isGuardian,
    int age,
    EducationInfoVo educationInfo,
    ConditionInfoVo conditionInfo) {}
