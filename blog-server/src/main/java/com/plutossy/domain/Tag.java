package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Tag {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
