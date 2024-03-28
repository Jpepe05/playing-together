package com.jpepe.playingtogether.vo.request;

import java.util.List;

public record RoundRequestVo(
    String category,
    String wordToGuess,
    String image,
    String artistId,
    List<PlayerAttemptRequestVo> players) {}
