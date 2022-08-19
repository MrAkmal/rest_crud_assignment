package com.example.rest_crud.config;


import com.example.rest_crud.exceptionhandler.AccessDeniedException;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Aspect
@Component
public class AppAspect {

    private final RestTemplate restTemplate;

    @Autowired
    public AppAspect(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Pointcut("execution(* com.example.rest_crud.controller.RestDemoController.*(..))")
    public void forControllerPackage() {
    }

    @SneakyThrows
    @Before("forControllerPackage()")
    public void beforeEachRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String header = request.getHeader(AUTHORIZATION);
        if (header != null) {
            String token = header.substring("Bearer ".length());
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            Boolean aBoolean = restTemplate.exchange(RequestEntity.get(new URI("http://localhost:8080/authorize")).headers(headers).build(), Boolean.class).getBody();

            if (Objects.isNull(aBoolean)) throw new AccessDeniedException("Access Denied");
            System.out.println("aBoolean = " + aBoolean);
        } else {
            throw new AccessDeniedException("Access Denied");
        }

    }
}
