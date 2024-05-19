package com.jpepe.playingtogether.vo.response;

import com.jpepe.playingtogether.enumeration.SimilarityType;
import com.jpepe.playingtogether.vo.PerformanceOverTimeVo;
import java.util.List;
import java.util.Map;

public record PlayerStatisticsResponseVo(
    long totalRounds,
    double accuracyRate,
    Map<SimilarityType, Long> commonMistakes,
    List<PerformanceOverTimeVo> performanceOverTime) {}
