package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 收藏
 */
@Setter
@Getter
public class BlogTag implements Serializable {
    private Long blogId; //博客id
    private Long tagId; //用户id

    @Override
    public String toString() {
        return "BlogTag{" +
                "blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
