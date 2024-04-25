package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 */
@Setter
@Getter
public class Collect implements Serializable {
    private Long id;     //主键
    private Long blogId; //博客id
    private Long userId; //用户id

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", userId=" + userId +
                '}';
    }
}
