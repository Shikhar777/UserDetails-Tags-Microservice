package com.example.UserDetails.service;

import com.example.UserDetails.dto.BadgeResponseDto;

public interface BadgeService {

    BadgeResponseDto findBadge(String username);
}
