package com.example.UserDetails.answerClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="answer-client", url="http://10.177.68.63:8080/")
public interface AnswerClient {
    @RequestMapping(method = RequestMethod.GET, path = "/points")
    public Long findPoints(@RequestParam(value="username") String username);
}
