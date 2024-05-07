package com.jpepe.playingtogether.vo.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record PlayerUpdateRequestVo(@NotEmpty String name, @Positive int age) {}
