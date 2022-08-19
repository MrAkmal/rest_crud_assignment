package com.example.rest_crud.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class DataDTO<T> {

    private T body;

}
