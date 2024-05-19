package com.jpepe.playingtogether.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;

public record PerformanceOverTimeVo(
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC") Instant date, double score)
    implements Comparable<PerformanceOverTimeVo> {
  @Override
  public int compareTo(PerformanceOverTimeVo o) {
    return this.date.compareTo(o.date());
  }
}
