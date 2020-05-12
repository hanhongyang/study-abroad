package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Data
public class User {
    private Integer userId;

    private String password;

    private Integer role;

    private Integer countryId;

    private String email;

    private String mobile;

    private Date birthday;

    private String name;

    private String icon;

    private String uuid;

    public User() {
    }

    public User(Integer userId, String password, Integer role, Integer countryId, String email, String mobile, Date birthday, String name, String icon) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.countryId = countryId;
        this.email = email;
        this.mobile = mobile;
        this.birthday = birthday;
        this.name = name;
        this.icon = icon;
    }

    public User(Integer userId, String password, Integer role, Integer countryId, String email, String mobile, Date birthday, String name, String icon, String uuid) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.countryId = countryId;
        this.email = email;
        this.mobile = mobile;
        this.birthday = birthday;
        this.name = name;
        this.icon = icon;
        this.uuid = uuid;

    }

    private Country country;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", countryId=" + countryId +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", uuid='" + uuid + '\'' +
                ", country=" + country +
                '}';
    }
}