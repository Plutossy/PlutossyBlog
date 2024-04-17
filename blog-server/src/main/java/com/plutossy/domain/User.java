package com.plutossy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class User implements Serializable {
    private Long id;
    private String nickname;
    private String avatar;
    private String username;
    private String password;
    private Boolean type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String sex;
    private String phone;
    private String email;
    private String github;
    private String csdn;
    private String qq;
    private String wechat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String address;
    private String introduction;

    private String captcha;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", github='" + github + '\'' +
                ", csdn='" + csdn + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", birth=" + birth +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
