package com.jpepe.playingtogether.vo.request;

import java.util.List;

public record PlayerAttemptRequestVo(Long playerId, List<String> attempts) {}
