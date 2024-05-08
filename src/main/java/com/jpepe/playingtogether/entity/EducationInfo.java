package com.jpepe.playingtogether.entity;

import com.jpepe.playingtogether.enumeration.EducationGrade;
import com.jpepe.playingtogether.enumeration.EducationLevel;
import com.jpepe.playingtogether.enumeration.SchoolShift;
import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class EducationInfo {

  @Enumerated(EnumType.ORDINAL)
  private EducationLevel educationLevel;

  @Enumerated(EnumType.ORDINAL)
  private EducationGrade educationGrade;

  @Enumerated(EnumType.ORDINAL)
  private SchoolShift schoolShift;
}
