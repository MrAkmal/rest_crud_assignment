package com.example.rest_crud.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {

    T data;
    String message;
    int status;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
