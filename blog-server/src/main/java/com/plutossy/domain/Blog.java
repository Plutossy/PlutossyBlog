package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Blog implements Serializable {
    private Long id;
    private String title;
    @Basic(fetch = FetchType.LAZY)//使用才加载类型，不耗资源
    @Lob//大字段类型
    private String content;
    private String description;
    private String picture;
    private String flag;
    private Boolean recommend;
    private Integer published; // 0 未发布 1 发布 2 草稿
    private Boolean commentabled;
    private Boolean shared;
    private Integer views;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Long typeId;
    private Long userId;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", flag='" + flag + '\'' +
                ", recommend=" + recommend +
                ", published=" + published +
                ", commentabled=" + commentabled +
                ", shared=" + shared +
                ", views=" + views +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", userId=" + userId +
                '}';
    }
}
