package com.example.demo.User;

import com.example.demo.Role.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String index(){

        return "index page";
    }

    @GetMapping("/listall")
    public List<User> login(){
        return userService.listAll();
    }

    @PostMapping("/register")
    public void register(){
        User user = new User("admin", "admin@gmail.com", "password");
        Role role = new Role("ADMIN");
        userService.addUser(user);
        userService.addRoleToUser(user, role);
    }

    @GetMapping("/kakaoLogin")
    public String kakaoLogin(String code){

        RestTemplate restTemplate = new RestTemplate();

        Code authorizeCode  = new Code(code);

        String result = restTemplate.postForObject(
                "http://localhost:5000/kakao-login",
                authorizeCode,
                String.class
        );

        log.info("result = " + result);

        return result;
    }

}
