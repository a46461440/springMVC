package com.zxc.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    @NotNull(message = "账号不能为空")
    @Length(min = 10, max = 65535, message = "账号位数不符合规则")
    private String name;

    @NotNull(message = "密码不能为空")
    @Length(min = 5, max = 10, message = "密码位数不符合规则")
    private String password;

    @NotNull(message = "时间不能为空")
    @Future(message = "时间必须选择一个之后的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }
}

