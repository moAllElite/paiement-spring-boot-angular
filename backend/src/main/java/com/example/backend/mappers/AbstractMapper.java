package com.example.backend.mappers;

public interface AbstractMapper<T,O> {
    T toEntity(O o);
    O toDto(T t);
}
