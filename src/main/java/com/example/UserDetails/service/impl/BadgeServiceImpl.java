package com.example.UserDetails.service.impl;

import com.example.UserDetails.answerClient.AnswerClient;
import com.example.UserDetails.dto.BadgeResponseDto;
import com.example.UserDetails.entity.Badge;
import com.example.UserDetails.repository.BadgeRepository;
import com.example.UserDetails.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private  AnswerClient answerClient;

    @Autowired
    private BadgeRepository badgeRepository;
    @Override
    public BadgeResponseDto findBadge(String username) {

        Long points = getResponseFromAnswerClient(username);
        BadgeResponseDto badgeResponseDto = new BadgeResponseDto();
        String ranking = "";
        if(points>0) {
            ranking = "bronze";
        } else if(points>40) {
            ranking = "silver";
        } else if(points>60) {
            ranking = "gold";
        } else if(points > 100) {
            ranking ="platinum";
        }

        Badge badge = new Badge();
        badge.setRanking(ranking);
        badge.setUsername(username);
        badgeRepository.save(badge);
        badgeResponseDto.setBadgeId(badge.getBadgeId());
        badgeResponseDto.setRanking(ranking);
        return badgeResponseDto;
    }

    public Long getResponseFromAnswerClient(String username) {
        Long points = answerClient.findPoints(username);
        return points;
    }
}
