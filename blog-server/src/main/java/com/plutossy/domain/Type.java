package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Type {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
