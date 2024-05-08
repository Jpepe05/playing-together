package com.jpepe.playingtogether.entity;

import com.jpepe.playingtogether.enumeration.NeurologicalCondition;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Set;

@Data
@Embeddable
public class ConditionInfo {

  @ElementCollection
  @Enumerated(EnumType.ORDINAL)
  private Set<NeurologicalCondition> conditions;

  private String otherCondition;
  private Boolean hasMedicalReport;
}
