package com.aasshh.user.Model;

import java.io.Serializable;

public class User  implements Serializable {
    String id  , name  ,email , phone  ,verificationCode ,
    role ,accessToken ,  status , imageStr , updatedAt , createdAt ;

    public User(String id, String name, String email, String phone,
                String verificationCode, String role, String accessToken,
                String status, String imageStr, String updatedAt, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.verificationCode = verificationCode;
        this.role = role;
        this.accessToken = accessToken;
        this.status = status;
        this.imageStr = imageStr;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
