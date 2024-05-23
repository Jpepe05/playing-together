package com.jpepe.playingtogether.vo.request;

import com.jpepe.playingtogether.vo.ConditionInfoVo;
import com.jpepe.playingtogether.vo.EducationInfoVo;
import jakarta.validation.constraints.Positive;

public record PlayerEducationHealthRequestVo(
    @Positive int age, EducationInfoVo educationInfo, ConditionInfoVo conditionInfo) {}
