package com.example.UserDetails.controller;

import com.example.UserDetails.dto.BadgeResponseDto;
import com.example.UserDetails.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@RestController
@RequestMapping("/badge")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @GetMapping("/{username}")
    public BadgeResponseDto findBadge(@PathVariable ("username") String username) {
       return badgeService.findBadge(username);
    }
}
