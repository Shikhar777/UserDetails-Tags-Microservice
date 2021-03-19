package com.example.UserDetails.service.impl;

import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.User;
import com.example.UserDetails.service.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer implements ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    protected static final String UPDATE_BY_USER_TO_SEARCH_AFTER_UPDATE = "updateByUserToSearchAfterUpdate";

    protected static final String UPDATE_TO_SEARCH_AFTER_UPDATE = "afterUpdate";

    @Override
    public void sendMessageToSearchAfterUpdation(User user) {
        try {
            logger.info(String.format("#### -> Producing Message -> %s", user.toString()));
            ObjectMapper objectMapper = new ObjectMapper();
            String string = "";
            try {
                string = objectMapper.writeValueAsString(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            kafkaTemplate.send(UPDATE_BY_USER_TO_SEARCH_AFTER_UPDATE, string);
            logger.info(String.format("Sent to search microservice"));
        } catch (NullPointerException e) {
            return;
        }
    }

    @Override
    public void afterUpdation(UserUpdateResponseDto userUpdateResponseDto) {
        try {
            logger.info(String.format("#### -> Producing Message -> %s", userUpdateResponseDto.toString()));
            ObjectMapper objectMapper = new ObjectMapper();
            String string = "";
            try {
                string = objectMapper.writeValueAsString(userUpdateResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            kafkaTemplate.send(UPDATE_TO_SEARCH_AFTER_UPDATE, string);
            logger.info(String.format("Sent to search microservice"));
        } catch (NullPointerException e) {
            return;
        }
    }
}
