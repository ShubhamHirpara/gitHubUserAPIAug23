package com.example.githubuserapi.controller;

import com.example.githubuserapi.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/github")
public class GithubController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;

    @GetMapping("/user/{name}")
    public User getUserDetails(@PathVariable("name") String name){
        System.out.println(name);
        return getGithubUser(name);
        //return restTemplate.getForObject("https://api.github.com/users/"+name, User.class);
    }
    private User getGithubUser(String username){
        User user = webClient.get()

                .uri("https://api.github.com/users/{username}",username)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }
}
