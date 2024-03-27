package com.jpepe.playingtogether.vo.request;

import java.util.List;

public record MatchFinishedRequestVo(String name, Integer roundTime, List<RoundRequestVo> rounds) {}
