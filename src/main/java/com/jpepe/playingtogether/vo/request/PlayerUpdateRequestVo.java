package com.jpepe.playingtogether.vo.request;

import jakarta.validation.constraints.NotEmpty;

public record PlayerUpdateRequestVo(@NotEmpty String name) {}
