package com.jpepe.playingtogether.vo.request;

import java.util.List;

public record PlayerAttemptRequestVo(String playerId, List<String> attempts) {}
