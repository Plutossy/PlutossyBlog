package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 */
@Setter
@Getter
public class Comment implements Serializable {
    /*主键*/
    private Long id;
    /*评论类型*/
    private Boolean type;
    /*评论内容*/
    private String content;
    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /*评论id*/
    private Long parentCommentId;
    /*用户id*/
    private Long userId;
    /*博客id*/
    private Long blogId;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", parentCommentId=" + parentCommentId +
                ", userId=" + userId +
                ", blogId=" + blogId +
                '}';
    }
}
