package com.example.client.dto;


public class ResponseDTO<T> {


    private ResultsDTO<T> data;

    public ResultsDTO<T> getData() {
        return data;
    }

  
}