package com.jpepe.playingtogether.vo;

import com.jpepe.playingtogether.enumeration.EducationGrade;
import com.jpepe.playingtogether.enumeration.EducationLevel;
import com.jpepe.playingtogether.enumeration.SchoolShift;

public record EducationInfoVo(
    EducationLevel educationLevel, EducationGrade educationGrade, SchoolShift schoolShift) {}
