package com.example.rest_crud.controller;

import com.example.rest_crud.dto.DataDTO;
import com.example.rest_crud.dto.LoginDTO;
import com.example.rest_crud.dto.ResetPasswordDTO;
import com.example.rest_crud.dto.SessionDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final RestTemplate restTemplate;

    @Autowired
    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDto, headers);
        DataDTO<?> sessionDTO = restTemplate.exchange("http://localhost:8080/login",
                HttpMethod.POST, entity, DataDTO.class).getBody();

        System.out.println("sessionDTO = " + sessionDTO);

        return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/refresh-token")
    public ResponseEntity<SessionDTO> refreshToken(HttpServletRequest request,
                                                   HttpServletResponse response) {

        String header = request.getHeader(AUTHORIZATION);
        System.out.println("header = " + header);
        String token = header.substring("Bearer ".length());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        SessionDTO sessionDTO = restTemplate.exchange(RequestEntity.get(
                        new URI("http://localhost:8080/refresh-token")).headers(headers).build(),
                SessionDTO.class).getBody();

        return new ResponseEntity<>(new SessionDTO(sessionDTO.getAccessToken(),sessionDTO.getRefreshToken()), HttpStatus.OK);
    }

    @PostMapping("/send-code")
    public ResponseEntity<Void> sendCode(@RequestParam String username) {

        System.out.println("username = " + username);
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(username, headers);
        String string = restTemplate.exchange("http://localhost:8080/send-code",
                HttpMethod.POST, entity, String.class).getBody();

        System.out.println("string = " + string);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO,
                                                 @RequestParam("token") String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<ResetPasswordDTO> entity = new HttpEntity<>(resetPasswordDTO, headers);
        Boolean result = restTemplate.exchange(
                        "http://localhost:8080/reset-password?token=" + token,
                        HttpMethod.POST, entity, Boolean.class)
                .getBody();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
