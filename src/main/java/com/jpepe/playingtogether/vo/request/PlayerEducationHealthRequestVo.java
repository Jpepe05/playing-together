package com.jpepe.playingtogether.vo.request;

import com.jpepe.playingtogether.vo.ConditionInfoVo;
import com.jpepe.playingtogether.vo.EducationInfoVo;

public record PlayerEducationHealthRequestVo(
    EducationInfoVo educationInfo, ConditionInfoVo conditionInfo) {}
