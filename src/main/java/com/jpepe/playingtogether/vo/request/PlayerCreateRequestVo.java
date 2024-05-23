package com.jpepe.playingtogether.vo.request;

import jakarta.validation.constraints.NotEmpty;

public record PlayerCreateRequestVo(
    @NotEmpty String id, @NotEmpty String name, boolean isGuardian) {}
