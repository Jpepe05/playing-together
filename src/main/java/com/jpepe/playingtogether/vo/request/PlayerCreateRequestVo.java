package com.jpepe.playingtogether.vo.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record PlayerCreateRequestVo(
    @NotEmpty String id, @NotEmpty String name, boolean isGuardian, @Positive int age) {}
