package com.example.lunchvoting.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * Isolate dependency to Spring Security
 */
@AuthenticationPrincipal
public @interface CurrentUser {}
