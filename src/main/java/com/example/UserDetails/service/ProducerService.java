package com.example.UserDetails.service;

import com.example.UserDetails.dto.UserUpdateResponseDto;
import com.example.UserDetails.entity.User;
import org.apache.kafka.common.protocol.types.Field;

public interface ProducerService {

    void sendMessageToSearchAfterUpdation(User user);
    void afterUpdation(UserUpdateResponseDto userUpdateResponseDto);
}
