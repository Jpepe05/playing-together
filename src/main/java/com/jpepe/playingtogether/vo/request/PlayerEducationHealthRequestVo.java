package com.jpepe.playingtogether.vo.request;

import com.jpepe.playingtogether.vo.ConditionInfoVo;
import com.jpepe.playingtogether.vo.EducationInfoVo;

public record PlayerEducationHealthRequestVo(
    int age, EducationInfoVo educationInfo, ConditionInfoVo conditionInfo) {}
