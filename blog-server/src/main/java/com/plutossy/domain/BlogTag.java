package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 博客-标签
 */
@Setter
@Getter
public class BlogTag implements Serializable {
    private Long id; // 主键
    private Long blogId; //博客id
    private Long tagId; //用户id

    @Override
    public String toString() {
        return "BlogTag{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
