package com.example.client.dto;

import java.util.List;

public class CollectionDTO<T> {

    private String collectionURI;
    private Integer available;
    private List<T> items;

    public String getCollectionURI() {
        return collectionURI;
    }

    public Integer getAvailable() {
        return available;
    }

    public List<T> getItems() {
        return items;
    }
}
